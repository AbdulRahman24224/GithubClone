package com.example.usecases.products

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo

interface GetRemoteReposByStarsUseCase {

    suspend operator  fun invoke(page :Int): DataResult<List<Repo>>
}