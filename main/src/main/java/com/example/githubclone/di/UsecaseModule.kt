package com.example.githubclone.di

import com.example.usecases.products.GetReposByStarsUseCase
import com.example.usecases_impl.GetReposByStarsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetProductsUseCase(
        useCase: GetReposByStarsUseCaseImpl,
    ): GetReposByStarsUseCase

}