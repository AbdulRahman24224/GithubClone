package com.example.usecases_impl

import com.example.domain_models.PREFERENCE_KEYS
import com.example.repositories.ReposRepository
import com.example.repositories.SettingsRepository
import com.example.usecases.products.CachedReposInvalidationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CachedRepoInvalidationUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository,
    private val settingsRepository: SettingsRepository,
    private val coroutineScope: CoroutineScope
) : CachedReposInvalidationUseCase {

    override suspend fun invoke(forceInvalidate: Boolean, currentTime: Long): Boolean {

        val invalidationTime =
            withContext(Dispatchers.IO) {
                settingsRepository.getPreferenceValue(PREFERENCE_KEYS.CACHE_INVALIDATION_DATE)
                    ?: "0"
            }

        val shouldInvalidate =
        forceInvalidate || currentTime >= (invalidationTime.toLongOrNull() ?: 0)

        if (shouldInvalidate)
            coroutineScope.launch {
                withContext(Dispatchers.IO) { reposRepository.clearRepos() }
            }

        return shouldInvalidate
    }
}