package com.example.usecases_impl

import com.example.app.rules.MainDispatcherRule
import com.example.common.constants.AppConstants
import com.example.domain_models.PREFERENCE_KEYS
import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.repositories.SettingsRepository
import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import com.example.usecases.products.SaveReposLocallyUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GetRemoteReposByStarsUseCaseImplTest{


    @get:Rule
    val coroutinesTestRule = MainDispatcherRule()

    @RelaxedMockK
    private lateinit var reposRepository: ReposRepository

    @RelaxedMockK
    private lateinit var settingsRepository: SettingsRepository

    private lateinit var getRemoteReposByStarsUseCase: GetRemoteReposByStarsUseCase


    private val mockSuccessResult = listOf<Repo>(
        Repo(1), Repo(2), Repo(3)
    )


    @Before
    fun setUp() {

        MockKAnnotations.init(this)

        getRemoteReposByStarsUseCase = GetRemoteReposByStarsUseCaseImpl(
            reposRepository,
            settingsRepository
        )
        coEvery { reposRepository.getRemoteReposByStars(1)} returns DataResult.Success(mockSuccessResult)
    }

    @Test
    fun `when usecase called with page 1 and result is Success saveReposLocally() is called `() {
        runTest {

            getRemoteReposByStarsUseCase.invoke(1, 10L)

            coVerify { reposRepository.saveReposLocally(mockSuccessResult ,1) }

        }
    }

    @Test
    fun `when usecase called with page 1 and result is Success new cache invalidation time is saved() `() {
        runTest {

            val currentTime = 10L
            getRemoteReposByStarsUseCase.invoke(1, currentTime)


            coVerify {
                settingsRepository.savePreferenceValue(
                    PREFERENCE_KEYS.CACHE_INVALIDATION_DATE, (currentTime + AppConstants.APP_CACHING_TIME).toString()
                )
            }

        }
    }

    @Test
    fun `when usecase called and result is Failure neither new cache value is set nor saveReposLocally() is invoked  `() {

        val currentTime = 10L
        coEvery { reposRepository.getRemoteReposByStars(1) } returns DataResult.Failure(RuntimeException("error"))

        runTest {
            getRemoteReposByStarsUseCase.invoke(1, currentTime)
        }

        coVerify(exactly = 0) { reposRepository.saveReposLocally(mockSuccessResult ,1) }

        coVerify(exactly = 0) {
            settingsRepository.savePreferenceValue(
                PREFERENCE_KEYS.CACHE_INVALIDATION_DATE, (currentTime + AppConstants.APP_CACHING_TIME).toString()
            )
        }

    }

    @Test
    fun `when usecase called with page other than 1 and result is Success neither new cache value is not updated  `() {

        val currentTime = 10L
        coEvery { reposRepository.getRemoteReposByStars(2)} returns DataResult.Success(mockSuccessResult)


        runTest {
            getRemoteReposByStarsUseCase.invoke(2, currentTime)
        }

        coVerify(exactly = 1) { reposRepository.saveReposLocally(mockSuccessResult ,2) }

        coVerify(exactly = 0) {
            settingsRepository.savePreferenceValue(
                PREFERENCE_KEYS.CACHE_INVALIDATION_DATE, (currentTime + AppConstants.APP_CACHING_TIME).toString()
            )
        }

    }
}