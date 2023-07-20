package com.example.repositories_impl

import com.example.datasources.RemoteReposDataSource
import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(
    private val remoteProductsDataSource: RemoteReposDataSource,
) : ReposRepository {


    override suspend fun getRemoteProducts(): DataResult<List<Repo>> {
        return remoteProductsDataSource.getReposByRating()
    }


}
