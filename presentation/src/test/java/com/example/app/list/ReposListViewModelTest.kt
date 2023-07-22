package com.example.app.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.app.rules.MainDispatcherRule
import com.example.domain_models.network.DataResult
import com.example.domain_models.network.NetworkException
import com.example.domain_models.repos.Repo
import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import com.example.usecases.products.SaveReposLocallyUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ReposListViewModelTest {

    @get:Rule
    val coroutinesTestRule = MainDispatcherRule()

    @RelaxedMockK
    private lateinit var getRemoteReposByStarsUseCase: GetRemoteReposByStarsUseCase

    @RelaxedMockK
    private lateinit var getLocaleReposByStarsUseCase: GetLocaleReposByStarsUseCase

    @RelaxedMockK
    private lateinit var saveReposLocallyUseCase: SaveReposLocallyUseCase

    private lateinit var reposListViewModel: ReposListViewModel

    private val mockSuccessResult = listOf<Repo>(
        Repo(1), Repo(2), Repo(3)
    )

    @Before
    fun setUp() {

        MockKAnnotations.init(this)

        coEvery { getRemoteReposByStarsUseCase(1) } returns DataResult.Success(mockSuccessResult)
        coEvery { getLocaleReposByStarsUseCase(1) } returns listOf()

        reposListViewModel = ReposListViewModel(
            getRemoteReposByStarsUseCase,
            getLocaleReposByStarsUseCase,
            saveReposLocallyUseCase

        )
    }

    @Test
    fun `loadRepos should call getRemoteRepos when has No Locale Data `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = true, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            coVerify { getRemoteReposByStarsUseCase(1) }

        }
    }

    @Test
    fun `loadRepos should call getLocaleRepos when has LocaleData `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = false, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            coVerify { getLocaleReposByStarsUseCase(1) }

        }
    }

    @Test
    fun `getRemoteRepos() with success result invokes saveReposLocallyUseCase() `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = true, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            coVerify { saveReposLocallyUseCase(mockSuccessResult, 1) }

        }
    }

    @Test
    fun `getRemoteRepos() with failure result toast error message`() {
        runTest {

            coEvery { getRemoteReposByStarsUseCase(1) } returns DataResult.Failure(NetworkException("error"))

            val viewState = ReposListViewState(hasNoMoreLocaleData = true, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            reposListViewModel._viewState.test {
                assert(awaitItem().snackBarMessage == "error")
            }

        }
    }

    @Test
    fun `addPageToList() increases repos list by items size and  `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = true, page = 1)
            reposListViewModel._viewState.value = viewState
            val oldListSize = reposListViewModel.reposList.value.size

            reposListViewModel.loadRepos()

            reposListViewModel.reposList.test {
                assert(awaitItem().size == oldListSize + mockSuccessResult.size)
            }

        }
    }

    @Test
    fun `addPageToList() increases page by 1 `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = true, page = 1)
            reposListViewModel._viewState.value = viewState
         val oldPageSize = reposListViewModel._viewState.value.page

            reposListViewModel.loadRepos()

            reposListViewModel.viewState.test {
                assert(awaitItem().page == oldPageSize + 1)
            }

        }
    }


    // Add more test cases to cover different scenarios
    // - Test success case for getRemoteRepos
    // - Test failure case for getRemoteRepos
    // - Test success case for getLocaleRepos
    // - Test failure case for getLocaleRepos
    // - Test success case for addPageToList
    // - Test that isLoading is updated correctly in the viewState
    // - Test that _reposList is updated correctly
    // - Test that page is incremented correctly in the viewState

}