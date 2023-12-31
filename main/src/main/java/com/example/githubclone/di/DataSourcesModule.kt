package com.example.githubclone.di

import com.example.datasources.LocalReposDataSource
import com.example.datasources.RemoteReposDataSource
import com.example.datasources.PreferenceDataSource
import com.example.network.datasource_impl.RemoteReposDataSourceImpl
import com.example.storage.database.datasource_impl.LocalReposDataSourceImpl
import com.example.storage.preferences.PreferenceDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourcesModule {

    @Binds
    fun bindRemoteReposDataSource(
        userDataSourceImpl: RemoteReposDataSourceImpl
    ): RemoteReposDataSource

    @Binds
    fun binLocalReposDataSource(
        userDataSourceImpl: LocalReposDataSourceImpl
    ): LocalReposDataSource


    @Binds
    fun binSettingsPrefDataSource(
        userDataSourceImpl: PreferenceDataSourceImpl
    ): PreferenceDataSource

}