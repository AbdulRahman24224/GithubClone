package com.example.network.datasource_impl

import android.accounts.NetworkErrorException
import com.example.datasources.RemoteReposDataSource
import com.example.domain_models.network.DataResult
import com.example.domain_models.network.NetworkException
import com.example.domain_models.repos.Repo
import com.example.network.mapper.toDomain
import com.example.network.models.NetworkResponse
import com.example.network.service.ReposService
import com.example.network.utils.toException
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteReposDataSourceImpl @Inject constructor(
    private val reposService: ReposService,

    ) : RemoteReposDataSource {

    override suspend fun getReposByStars(): DataResult<List<Repo>> {
        return try {
            reposService.getReposByRating().let {
                when (it) {
                    is NetworkResponse.ApiError -> {
                        DataResult.Failure(it.body.toException())
                    }

                    is NetworkResponse.NetworkError -> {
                        DataResult.Failure(NetworkException(it?.error?.localizedMessage ?:""))
                    }

                    is NetworkResponse.Success -> {
                        DataResult.Success(it.body.items.map { repoJson ->  repoJson.toDomain() })
                    }

                    is NetworkResponse.UnknownError -> {
                        DataResult.Failure(NetworkException(it?.error?.localizedMessage ?:""))
                    }
                }
            }
        } catch (e: Throwable) {
            DataResult.Failure(RuntimeException(e))
        }
    }


}


