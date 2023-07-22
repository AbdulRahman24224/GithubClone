package com.example.usecases.products

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo

interface SaveReposLocallyUseCase {

    suspend operator  fun invoke(repos:List<Repo> ,page:Int): Unit
}