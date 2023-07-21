package com.example.usecases_impl

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import javax.inject.Inject


class GetRemoteReposByStarsUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository ,
) : GetRemoteReposByStarsUseCase {

    override suspend fun invoke(): DataResult<List<Repo>> {
        return reposRepository.getRemoteReposByStars()

    }

}