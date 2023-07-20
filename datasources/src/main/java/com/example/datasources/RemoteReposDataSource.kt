package com.example.datasources


import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo

interface RemoteReposDataSource {

    suspend fun getReposByRating(): DataResult<List<Repo>>


}