package com.example.app.list

import androidx.lifecycle.ViewModel
import com.example.domain_models.network.DataResult
import com.example.domain_models.network.NetworkException
import com.example.domain_models.repos.Repo
import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import com.example.usecases.products.SaveReposLocallyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
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
    private val coroutineScope: CoroutineScope

) : ViewModel (){

    private val _reposList = MutableStateFlow<List<Repo>>(emptyList())
    val reposList = _reposList.asStateFlow()

    val _viewState = MutableStateFlow<ReposListViewState>(ReposListViewState())
    val viewState = _viewState.asStateFlow()

    init { loadRepos() }

    fun loadRepos(){

        // todo add cache invalidation check
        if (_viewState.value.hasNoMoreLocaleData) getRemoteRepos()
         else getLocaleRepos()

    }

    private fun getLocaleRepos(){

        coroutineScope.launch {

        val localRepos =   withContext(Dispatchers.IO) { getLocaleReposByStarsUseCase(_viewState.value.page) }

            if (localRepos.isEmpty()) {
                _viewState.update { it.copy(hasNoMoreLocaleData = true) }
                getRemoteRepos()

            }else{
                addPageToList(localRepos)
            }

        }
    }

    private fun getRemoteRepos() {

        coroutineScope.launch {

            val result = withContext(Dispatchers.IO) {
                getRemoteReposByStarsUseCase(_viewState.value.page)
            }

            _viewState.update { it.copy(isLoading = true) }

            when (result) {
                is DataResult.Success -> {

                    // todo :  check if data is empty or null to stop calling api
                    val repos = result.data

                    withContext(Dispatchers.IO) {  saveReposLocallyUseCase(repos, _viewState.value.page) }

                    addPageToList(repos)

                }

                is DataResult.Failure -> {

                    when (result.throwable) {
                        is NetworkException -> {/* Todo :  show error or load from Locale*/}
                        else -> {/*  Todo:  Api error show toast */ }
                    }
                }
            }
        }

    }

    private suspend fun addPageToList(localRepos: List<Repo>) {
        _reposList.emit(_reposList.value + localRepos)
        _viewState.update {
            it.copy(isLoading = false, page = it.page.plus(1))
        }
    }

}