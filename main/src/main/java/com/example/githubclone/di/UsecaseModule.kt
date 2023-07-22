package com.example.githubclone.di

import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import com.example.usecases.products.SaveReposLocallyUseCase
import com.example.usecases_impl.GetLocaleReposByStarsUseCaseImpl
import com.example.usecases_impl.GetRemoteReposByStarsUseCaseImpl
import com.example.usecases_impl.SaveReposLocallyUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetRemoteReposUseCase(
        useCase: GetRemoteReposByStarsUseCaseImpl,
    ): GetRemoteReposByStarsUseCase

    @Binds
    fun bindGetLocaleReposUseCase(
        useCase: GetLocaleReposByStarsUseCaseImpl,
    ): GetLocaleReposByStarsUseCase

    @Binds
    fun bindSaveReposLocallyUseCase(
        useCase: SaveReposLocallyUseCaseImpl,
    ): SaveReposLocallyUseCase

}