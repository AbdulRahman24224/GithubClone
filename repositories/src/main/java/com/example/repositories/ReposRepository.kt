package com.example.repositories

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo

interface ReposRepository {

    suspend fun getRemoteProducts(): DataResult<List<Repo>>

}