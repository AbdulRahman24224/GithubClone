package com.example.app.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.DateTimeUtils.currentDate
import com.example.domain_models.PREFERENCE_KEYS
import com.example.domain_models.network.DataResult
import com.example.domain_models.network.NetworkException
import com.example.domain_models.repos.Repo
import com.example.usecases.products.ClearCachedReposUseCase
import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetPreferenceValueUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import com.example.usecases.products.SavePreferenceValueUseCase
import com.example.usecases.products.SaveReposLocallyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReposListViewModel @Inject constructor(
    private val getRemoteReposByStarsUseCase: GetRemoteReposByStarsUseCase,
    private val getLocaleReposByStarsUseCase: GetLocaleReposByStarsUseCase,
    private val saveReposLocallyUseCase: SaveReposLocallyUseCase,
    private val getPreferenceValueUseCase: GetPreferenceValueUseCase,
    private val savePreferenceValueUseCase: SavePreferenceValueUseCase,
    private val clearCachedReposUseCase: ClearCachedReposUseCase

) : ViewModel() {

    private val _reposList = MutableStateFlow<List<Repo>>(emptyList())
    val reposList = _reposList.asStateFlow()

    val _viewState = MutableStateFlow<ReposListViewState>(ReposListViewState())
    val viewState = _viewState.asStateFlow()

    init {

        loadRepos()
    }

    fun loadRepos() {

        viewModelScope.launch {

            checkCacheInvalidation()

            if (_viewState.value.hasNoMoreLocaleData) getRemoteRepos()
            else getLocaleRepos()
        }

    }

    private suspend fun checkCacheInvalidation() {

        val cacheInvalidationDate =
            getPreferenceValueUseCase(PREFERENCE_KEYS.CACHE_INVALIDATION_DATE) ?: "0"

        if (currentDate().time >= cacheInvalidationDate.toLong()) {

            withContext(Dispatchers.IO) { clearCachedReposUseCase() }

            _viewState.update { it.copy(page = 1, hasNoMoreLocaleData = true) }
        }
    }

    private fun getLocaleRepos() {

        viewModelScope.launch {

            val localRepos =
                withContext(Dispatchers.IO) { getLocaleReposByStarsUseCase(_viewState.value.page) }

            if (localRepos.isEmpty()) {
                _viewState.update { it.copy(hasNoMoreLocaleData = true) }
                getRemoteRepos()

            } else {
                addPageToList(localRepos)
            }

        }
    }

    private fun getRemoteRepos() {

        viewModelScope.launch {

            if (_viewState.value.hasLoadedAllData) return@launch

            val currentPage = _viewState.value.page
            val nextPage = currentPage.plus(1)

            val result = withContext(Dispatchers.IO) {
                getRemoteReposByStarsUseCase(currentPage)
            }

            when (result) {
                is DataResult.Success -> {

                    val repos = result.data

                    cacheResults(repos, currentPage)

                    addPageToList(repos)

                    // Todo : This logic should be replaced by a flag from BE
                    if (nextPage > availablePages) _viewState.update { it.copy(hasLoadedAllData = true) }

                }

                is DataResult.Failure -> {

                    _viewState.update { it.copy(snackBarMessage = result.throwable.localizedMessage) }
                    when (result.throwable) {
                        is NetworkException -> {/* Todo :  show error or load from Locale*/
                        }

                        else -> {/*  Todo:  Api error show toast */
                        }
                    }
                }
            }
        }

    }

    private suspend fun cacheResults(
        repos: List<Repo>,
        currentPage: Int
    ) {
        withContext(Dispatchers.IO) {

            saveReposLocallyUseCase(repos, currentPage)

            if (currentPage == 1) {
                savePreferenceValueUseCase(
                    PREFERENCE_KEYS.CACHE_INVALIDATION_DATE,
                    (currentDate().time + 120000).toString()
                )
            }
        }
    }

    private fun addPageToList(localRepos: List<Repo>) {

        viewModelScope.launch {
            _reposList.emit(_reposList.value + localRepos)
            _viewState.update {
                val nextPage = it.page.plus(1)

                it.copy(isLoading = false, page = nextPage)
            }
        }

    }

    companion object {
        private const val availablePages = 34
    }


}