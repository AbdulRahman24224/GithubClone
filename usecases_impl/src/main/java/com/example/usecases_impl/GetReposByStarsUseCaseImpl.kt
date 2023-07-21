package com.example.usecases_impl

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.usecases.products.GetReposByStarsUseCase
import javax.inject.Inject


class GetReposByStarsUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository
) : GetReposByStarsUseCase {

    override suspend fun invoke(): DataResult<List<Repo>> {
        // todo cache using local data source
        return reposRepository.getReposByStars()
    }

}