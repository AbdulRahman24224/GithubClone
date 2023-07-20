package com.example.githubclone.di

import com.example.datasources.RemoteReposDataSource
import com.example.network.datasource_impl.RemoteReposDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourcesModule {

    @Binds
    fun bindRemoteProductDataSource(
        userDataSourceImpl: RemoteReposDataSourceImpl
    ): RemoteReposDataSource

}