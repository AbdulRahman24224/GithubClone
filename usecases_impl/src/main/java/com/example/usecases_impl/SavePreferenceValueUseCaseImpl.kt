package com.example.usecases_impl

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.repositories.SettingsRepository
import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import com.example.usecases.products.SavePreferenceValueUseCase
import com.example.usecases.products.SaveReposLocallyUseCase
import javax.inject.Inject


class SavePreferenceValueUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : SavePreferenceValueUseCase {


    override fun invoke(key : String , value : String) {
        settingsRepository.savePreferenceValue(key , value)

    }

}