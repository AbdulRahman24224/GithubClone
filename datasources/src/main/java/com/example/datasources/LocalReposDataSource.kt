package com.example.datasources


import com.example.domain_models.repos.Repo
interface LocalReposDataSource {

      suspend fun insertRepos(repos: List<Repo>, page: Int)

      fun getLocalRepos(page:Int):List<Repo>

      suspend fun clearRepos() :Int

}