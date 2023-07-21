package com.example.usecases.products

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo

interface GetReposByStarsUseCase {

    suspend operator fun invoke(): DataResult<List<Repo>>
}