package com.example.repositories_impl

import android.util.Log
import com.example.datasources.LocalReposDataSource
import com.example.datasources.RemoteReposDataSource
import com.example.datasources.PreferenceDataSource
import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.repositories.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : SettingsRepository {

    override fun savePreferenceValue(key: String, value: String) = preferenceDataSource.saveValue(key, value)

    override fun getPreferenceValue(key: String) = preferenceDataSource.getValue(key)

}
