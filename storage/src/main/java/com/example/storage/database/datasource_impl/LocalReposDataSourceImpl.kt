package com.example.storage.database.datasource_impl

import com.example.datasources.LocalReposDataSource
import com.example.domain_models.repos.Repo
import com.example.storage.database.dao.ReposDao
import com.example.storage.database.mappers.toDomain
import com.example.storage.database.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalReposDataSourceImpl @Inject constructor(
    private val reposDao: ReposDao,
) : LocalReposDataSource {


    override suspend fun insertRepos(repos: List<Repo> , page: Int) {
        reposDao.insertProducts(repos.map { it.toEntity().copy(page = page) })
    }

    override   fun getLocalRepos(page:Int): List<Repo> {
        return reposDao.getReposByPage(page).map { it.toDomain() }
    }

}
