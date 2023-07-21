package com.example.usecases_impl

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import javax.inject.Inject


class GetLocaleReposByStarsUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository ,
) : GetLocaleReposByStarsUseCase {

    override suspend fun invoke(): List<Repo>{
        return reposRepository.getLocaleReposByStars()

    }

}