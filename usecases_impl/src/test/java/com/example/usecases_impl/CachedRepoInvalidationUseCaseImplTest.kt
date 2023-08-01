package com.example.usecases_impl

import com.example.app.rules.MainDispatcherRule
import com.example.domain_models.PREFERENCE_KEYS
import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.repositories.SettingsRepository
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CachedRepoInvalidationUseCaseImplTest {

    @get:Rule
    val coroutinesTestRule = MainDispatcherRule()

    @RelaxedMockK
    private lateinit var reposRepository: ReposRepository

    @RelaxedMockK
    private lateinit var settingsRepository: SettingsRepository


    private lateinit var cacheUseCase:CachedRepoInvalidationUseCaseImpl


    @Before
    fun setUp() {

        MockKAnnotations.init(this)

        cacheUseCase = CachedRepoInvalidationUseCaseImpl(reposRepository, settingsRepository)

        coEvery { settingsRepository.getPreferenceValue(PREFERENCE_KEYS.CACHE_INVALIDATION_DATE) } returns "10"

    }

    @Test
    fun `when usecase called when current time is less than cache invalidation time repos not cleared`() {
        runTest {

            cacheUseCase.invoke(false, 9L)

            coVerify(exactly = 0) { reposRepository.clearRepos() }

        }
    }

    @Test
    fun `when usecase called when current time is less than cache invalidation time and forceInvalidate is true repos is cleared`() {
        runTest {

            cacheUseCase.invoke(true, 9L)

            coVerify{ reposRepository.clearRepos() }

        }
    }

    @Test
    fun `when usecase called when current time equal or more than cache invalidation time repos is cleared `() {
        runTest {

            cacheUseCase.invoke(false, 10L)

            coVerify { reposRepository.clearRepos() }

        }
    }
}