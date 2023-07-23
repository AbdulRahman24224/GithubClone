package com.example.githubclone.di


import com.example.repositories.ReposRepository
import com.example.repositories.SettingsRepository
import com.example.repositories_impl.ReposRepositoryImpl
import com.example.repositories_impl.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindReposRepository(
        repositoryImpl: ReposRepositoryImpl
    ): ReposRepository

    @Binds
    fun bindSettingsRepository(
        repositoryImpl: SettingsRepositoryImpl
    ): SettingsRepository

}