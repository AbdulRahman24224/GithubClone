package com.example.app.list

import app.cash.turbine.test
import com.example.app.rules.MainDispatcherRule
import com.example.common.utils.datetime.DateTimeUtils
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
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Date

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

    @RelaxedMockK
    private lateinit var getPreferenceValueUseCase: GetPreferenceValueUseCase

    @RelaxedMockK
    private lateinit var savePreferenceValueUseCase: SavePreferenceValueUseCase

    @RelaxedMockK
    private lateinit var clearCachedReposUseCase: ClearCachedReposUseCase

    @RelaxedMockK
    private lateinit var dateTimeUtils: DateTimeUtils

    private val mockSuccessResult = listOf<Repo>(
        Repo(1), Repo(2), Repo(3)
    )


    @Before
    fun setUp() {

        MockKAnnotations.init(this)

        coEvery { getRemoteReposByStarsUseCase(1) } returns DataResult.Success(mockSuccessResult)
        coEvery { getLocaleReposByStarsUseCase(1) } returns listOf()
        coEvery { getPreferenceValueUseCase(PREFERENCE_KEYS.CACHE_INVALIDATION_DATE) } returns "10"
        coEvery { dateTimeUtils.currentDate() } returns Date(9)

        reposListViewModel = ReposListViewModel(
            getRemoteReposByStarsUseCase,
            getLocaleReposByStarsUseCase,
            saveReposLocallyUseCase,
            getPreferenceValueUseCase,
            savePreferenceValueUseCase,
            clearCachedReposUseCase,
            dateTimeUtils

        )
    }

    @Test
    fun `loadRepos() should call getRemoteRepos when has No Locale Data `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = true, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            coVerify { getRemoteReposByStarsUseCase(1) }

        }
    }

    @Test
    fun `loadRepos() should call getLocaleRepos when has LocaleData `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = false, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            coVerify { getLocaleReposByStarsUseCase(1) }

        }
    }

    @Test
    fun `getLocaleRepos() with empty results should change hasNoMoreLocaleData to true  `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = false, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            coVerify { getLocaleReposByStarsUseCase(1) }

            assertEquals(true, reposListViewModel._viewState.value.hasNoMoreLocaleData)

        }
    }

    @Test
    fun `getLocaleRepos() with  results  shouldn't change hasNoMoreLocaleData to true  `() {
        runTest {

            coEvery { getLocaleReposByStarsUseCase(1) } returns listOf(Repo())

            val viewState = ReposListViewState(hasNoMoreLocaleData = false, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            assertEquals(false, reposListViewModel._viewState.value.hasNoMoreLocaleData)

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
    fun `getRemoteRepos() invocation changes isLoading to true then false `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = true, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            assertEquals(true, reposListViewModel._viewState.value.isLoading)
            assertEquals(false, reposListViewModel._viewState.value.isLoading)

        }
    }

    @Test
    fun `getRemoteRepos() with hasLoadedAllData true does not call Remote repos  `() {

        val viewState =
            ReposListViewState(hasLoadedAllData = true, hasNoMoreLocaleData = true, page = 1)
        reposListViewModel._viewState.value = viewState

        reposListViewModel.loadRepos()

        coVerify(exactly = 0) { getRemoteReposByStarsUseCase(1) }
    }

    @Test
    fun `getRemoteRepos() with last available page changes hasLoadedAllData to true   `() {
        runTest {

            coEvery { getRemoteReposByStarsUseCase(34) } returns DataResult.Success(mockSuccessResult)
            val viewState =
                ReposListViewState(hasLoadedAllData = false, hasNoMoreLocaleData = true, page = 34)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            coVerify { getRemoteReposByStarsUseCase(34) }

            assertEquals(true, reposListViewModel._viewState.value.hasLoadedAllData)

        }
    }

    @Test
    fun `getRemoteRepos() with failure result toast error message`() {
        runTest {

            coEvery { getRemoteReposByStarsUseCase(1) } returns DataResult.Failure(
                NetworkException(
                    "error"
                )
            )

            val viewState = ReposListViewState(hasNoMoreLocaleData = true, page = 1)
            reposListViewModel._viewState.value = viewState

            reposListViewModel.loadRepos()

            reposListViewModel._viewState.test {
                assert(awaitItem().snackBarMessage == "error")
            }

        }
    }

    @Test
    fun `checkCacheInvalidation should clear cache when currentDate is past cacheInvalidationDate`() =
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = false, page = 2)
            reposListViewModel._viewState.value = viewState

            coEvery { getPreferenceValueUseCase(PREFERENCE_KEYS.CACHE_INVALIDATION_DATE) } returns "10"
            coEvery { dateTimeUtils.currentDate() } returns Date(10)


            reposListViewModel.checkCacheInvalidation()

            coVerify { clearCachedReposUseCase() }

            assertEquals(1, reposListViewModel._viewState.value.page)
            assertTrue(reposListViewModel._viewState.value.hasNoMoreLocaleData)

        }


    @Test
    fun `checkCacheInvalidation should not clear cache when currentDate is before cacheInvalidationDate`() =
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = false, page = 2)
            reposListViewModel._viewState.value = viewState

            coEvery { getPreferenceValueUseCase(PREFERENCE_KEYS.CACHE_INVALIDATION_DATE) } returns "10"
            coEvery { dateTimeUtils.currentDate() } returns Date(9)


            reposListViewModel.checkCacheInvalidation()

            coVerify(exactly = 0) { clearCachedReposUseCase() }

            assertTrue(reposListViewModel._viewState.value.page != 1)
            assertTrue(reposListViewModel._viewState.value.hasNoMoreLocaleData.not())

        }


    @Test
    fun `addPageToList() increases repos list by items size and  `() {
        runTest {

            val viewState = ReposListViewState(hasNoMoreLocaleData = true, page = 1)
            reposListViewModel._viewState.value = viewState
            val oldListSize = reposListViewModel.reposList.value.size

            reposListViewModel.loadRepos()

            reposListViewModel.reposList.test {
                assertEquals(awaitItem().size, oldListSize + mockSuccessResult.size)
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
                assertEquals(awaitItem().page, oldPageSize + 1)
            }

        }
    }


}