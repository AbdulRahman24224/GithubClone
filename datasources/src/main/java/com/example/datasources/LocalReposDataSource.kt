package com.example.datasources


import com.example.domain_models.repos.Repo
import kotlinx.coroutines.flow.Flow

interface LocalReposDataSource {

    suspend fun insertRepos(products: List<Repo>)

    suspend fun getAllRepos(): Flow<List<Repo>>

}