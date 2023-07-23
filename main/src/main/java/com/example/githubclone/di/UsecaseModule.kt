package com.example.githubclone.di

import com.example.usecases.products.ClearCachedReposUseCase
import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetPreferenceValueUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import com.example.usecases.products.SavePreferenceValueUseCase
import com.example.usecases.products.SaveReposLocallyUseCase
import com.example.usecases_impl.ClearCachedReposUseCaseImpl
import com.example.usecases_impl.GetLocaleReposByStarsUseCaseImpl
import com.example.usecases_impl.GetPreferenceValueUseCaseImpl
import com.example.usecases_impl.GetRemoteReposByStarsUseCaseImpl
import com.example.usecases_impl.SavePreferenceValueUseCaseImpl
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

    @Binds
    fun bindSavePreferenceValueUseCase(
        useCase: SavePreferenceValueUseCaseImpl,
    ): SavePreferenceValueUseCase

    @Binds
    fun bindGetPreferenceValueUseCase(
        useCase: GetPreferenceValueUseCaseImpl,
    ): GetPreferenceValueUseCase

    @Binds
    fun bindClearCachedReposUseCasee(
        useCase: ClearCachedReposUseCaseImpl,
    ): ClearCachedReposUseCase

}