package com.example.usecases_impl

import com.example.repositories.ReposRepository
import com.example.usecases.products.ClearCachedReposUseCase
import javax.inject.Inject


class ClearCachedReposUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository,
) : ClearCachedReposUseCase {

    override suspend fun invoke(): Int = reposRepository.clearRepos()
}