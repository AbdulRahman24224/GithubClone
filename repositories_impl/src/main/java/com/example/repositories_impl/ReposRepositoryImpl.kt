package com.example.repositories_impl

import com.example.datasources.LocalReposDataSource
import com.example.datasources.RemoteReposDataSource
import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(
    private val remoteReposDataSource: RemoteReposDataSource,
    private val localReposDataSource: LocalReposDataSource,
) : ReposRepository {


    override suspend fun getRemoteReposByStars(): DataResult<List<Repo>> {
        return remoteReposDataSource.getReposByStars()
    }

    override suspend fun getLocaleReposByStars(): List<Repo> {
        return localReposDataSource.getAllRepos()
    }


}
