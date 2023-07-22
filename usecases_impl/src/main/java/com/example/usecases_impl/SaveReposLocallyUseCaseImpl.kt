package com.example.usecases_impl

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.usecases.products.GetLocaleReposByStarsUseCase
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import com.example.usecases.products.SaveReposLocallyUseCase
import javax.inject.Inject


class SaveReposLocallyUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository ,
) : SaveReposLocallyUseCase {


    override suspend fun invoke(repos: List<Repo>, page: Int) {
     reposRepository.saveReposLocally(repos,page)
    }


}