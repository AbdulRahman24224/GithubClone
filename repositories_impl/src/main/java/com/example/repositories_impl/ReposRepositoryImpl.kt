package com.example.repositories_impl

import android.util.Log
import com.example.datasources.LocalReposDataSource
import com.example.datasources.RemoteReposDataSource
import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(
    private val remoteReposDataSource: RemoteReposDataSource,
    private val localReposDataSource: LocalReposDataSource,
) : ReposRepository {


    override suspend fun getRemoteReposByStars(page: Int): DataResult<List<Repo>> {
        Log.d("request:","request: Repository")
        return remoteReposDataSource.getReposByStars(page)
    }

    override suspend fun getLocaleReposByStars(page: Int): List<Repo> {
        return localReposDataSource.getLocalRepos(page)
    }

    override suspend fun saveReposLocally(repos: List<Repo>, page: Int): Unit {
      localReposDataSource.insertRepos(repos, page)
    }


}
