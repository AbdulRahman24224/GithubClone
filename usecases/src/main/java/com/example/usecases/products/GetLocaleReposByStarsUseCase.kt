package com.example.usecases.products

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import kotlinx.coroutines.flow.Flow

interface GetLocaleReposByStarsUseCase {

    suspend operator  fun invoke(page : Int): List<Repo>
}