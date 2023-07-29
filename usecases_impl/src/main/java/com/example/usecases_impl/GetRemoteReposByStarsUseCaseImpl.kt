package com.example.usecases_impl

import com.example.common.constants.AppConstants.APP_CACHING_TIME
import com.example.domain_models.PREFERENCE_KEYS
import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.repositories.SettingsRepository
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetRemoteReposByStarsUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository,
    private val settingsRepository: SettingsRepository
) : GetRemoteReposByStarsUseCase {

    override suspend fun invoke(page: Int, currentTime: Long): DataResult<List<Repo>> {

        val result = reposRepository.getRemoteReposByStars(page)

        withContext(Dispatchers.IO) {

            if (result is DataResult.Success) {
                reposRepository.saveReposLocally(result.data, page)

                if (page == 1) {
                    settingsRepository.savePreferenceValue(
                        PREFERENCE_KEYS.CACHE_INVALIDATION_DATE,
                        (currentTime + APP_CACHING_TIME).toString()
                    )
                }
            }

        }
        return result

    }

}