package com.example.repositories

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo

interface ReposRepository {

    suspend fun getRemoteReposByStars(page: Int): DataResult<List<Repo>>

     suspend fun getLocaleReposByStars(page:Int): List<Repo>

    suspend fun saveReposLocally( repos:List<Repo> , page: Int):Unit

    suspend fun clearRepos():Int




}