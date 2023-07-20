package com.example.datasources


import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Repo
import kotlinx.coroutines.flow.Flow

interface RemoteReposDataSource {

    suspend fun getAllRepos(): DataResult<List<Repo>>


}