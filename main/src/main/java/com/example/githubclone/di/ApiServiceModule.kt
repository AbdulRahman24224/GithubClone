package com.example.githubclone.di

import com.example.network.service.ReposService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideProductsService(retrofit: Retrofit): ReposService =
        retrofit.create(ReposService::class.java)
}