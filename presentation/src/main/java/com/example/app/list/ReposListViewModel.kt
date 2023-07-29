package com.example.app.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.datetime.DateTimeUtils
import com.example.domain_models.PREFERENCE_KEYS
import com.example.domain_models.network.DataResult
import com.example.domain_models.network.NetworkException
import com.example.domain_models.repos.Repo
import com.example.githubClone.R
import com.example.usecases.products.CachedReposInvalidationUseCase
import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
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
    private val cacheInvalidationUseCase: CachedReposInvalidationUseCase,
    private val dateTimeUtils: DateTimeUtils

) : ViewModel() {

    private val _reposList = MutableStateFlow<List<Repo>>(emptyList())
    val reposList = _reposList.asStateFlow()

    val _viewState = MutableStateFlow<ReposListViewState>(ReposListViewState())
    val viewState = _viewState.asStateFlow()

    val availablePages = 34

    init {
        loadRepos()
    }

    fun loadRepos(invalidateCache: Boolean = false) {

        viewModelScope.launch {

            withContext(Dispatchers.IO) {checkCacheInvalidation(invalidateCache)}

            if (_viewState.value.hasNoMoreLocaleData) getRemoteRepos()
            else getLocaleRepos()
        }

    }

    suspend fun checkCacheInvalidation(invalidateCache: Boolean = false) {

            val isCacheInvalidated = withContext(Dispatchers.IO) {
                cacheInvalidationUseCase(invalidateCache, getCurrentDateInMillis())
            }

            if (isCacheInvalidated) {

                _viewState.update {
                    it.copy(
                        page = 1,
                        hasNoMoreLocaleData = true,
                        hasLoadedAllData = false,
                        snackBarMessage = null
                    )
                }
                _reposList.emit(emptyList())
            }

    }

    private fun getLocaleRepos() {

        viewModelScope.launch {

            if (_viewState.value.isLoadingLocal || _viewState.value.isLoading || _viewState.value.hasLoadedAllData) return@launch

            _viewState.update { it.copy(isLoadingLocal = true) }
            val localRepos =
                withContext(Dispatchers.IO) { getLocaleReposByStarsUseCase(_viewState.value.page) }
            _viewState.update { it.copy(isLoadingLocal = false) }

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

            if (_viewState.value.hasLoadedAllData || _viewState.value.isLoading) return@launch

            val currentPage = _viewState.value.page
            val nextPage = currentPage.plus(1)

            _viewState.update { it.copy(isLoading = true) }
            val result = withContext(Dispatchers.IO) {
                getRemoteReposByStarsUseCase(_viewState.value.page , getCurrentDateInMillis())
            }
            _viewState.update { it.copy(isLoading = false) }

            when (result) {
                is DataResult.Success -> {

                    addPageToList(result.data)

                    _viewState.update { it.copy(isApiUnreachable = false, snackBarMessage = null) }

                    // Todo : This logic should be replaced by a flag from BE
                    if (nextPage > availablePages) _viewState.update {
                        it.copy(
                            hasLoadedAllData = true,
                            snackBarMessage = R.string.you_loaded_all_data_pull_to_refresh_to_load_more
                        )
                    }
                }

                is DataResult.Failure -> {

                    when (result.throwable) {
                        is NetworkException -> {
                            if (currentPage == 1)
                                _viewState.update { it.copy(isApiUnreachable = true) }
                            else
                                _viewState.update { it.copy(snackBarMessage = result.throwable.localizedMessage) }
                        }

                        else -> {
                            _viewState.update { it.copy(snackBarMessage = result.throwable.localizedMessage) }
                        }
                    }
                }
            }
        }

    }

    private fun getCurrentDateInMillis() = dateTimeUtils.currentDate().time

    private fun addPageToList(localRepos: List<Repo>) {

        viewModelScope.launch {
            _reposList.emit(_reposList.value + localRepos)
            _viewState.update {
                val nextPage = it.page.plus(1)

                it.copy(page = nextPage)
            }
        }

    }

}