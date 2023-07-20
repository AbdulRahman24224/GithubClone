package com.example.githubclone.di


import com.example.repositories.ReposRepository
import com.example.repositories_impl.ReposRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSignUpRepository(
        repositoryImpl: ReposRepositoryImpl
    ): ReposRepository

}