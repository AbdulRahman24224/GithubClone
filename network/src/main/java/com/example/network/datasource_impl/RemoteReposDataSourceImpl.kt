package com.example.network.datasource_impl

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.datasources.RemoteReposDataSource
import com.example.domain_models.network.DataResult
import com.example.domain_models.network.NetworkException
import com.example.domain_models.repos.Repo
import com.example.network.mapper.toDomain
import com.example.network.models.NetworkResponse
import com.example.network.service.ReposService
import com.example.network.utils.toException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteReposDataSourceImpl @Inject constructor(
    private val reposService: ReposService,

    ) : RemoteReposDataSource {

    override suspend fun getReposByStars(page:Int): DataResult<List<Repo>> {
        return try {

            reposService.getReposByRating(page.toString()).let {
                when (it) {
                    is NetworkResponse.ApiError -> {
                        Timber.d("request: ApiError: ${it.toString()}")
                        DataResult.Failure(it.body.toException())

                    }

                    is NetworkResponse.NetworkError -> {
                        Timber.d("request: NetworkError: ${it.error.localizedMessage}")
                        DataResult.Failure(NetworkException(it?.error?.localizedMessage ?:""))
                    }

                    is NetworkResponse.Success -> {
                        Timber.d("request: Success: ${it.body.items.map { repoJson ->  repoJson.toDomain() }}")
                        DataResult.Success(it.body.items.map { repoJson ->  repoJson.toDomain() })
                    }

                    is NetworkResponse.UnknownError -> {
                        Timber.d("request: UnknownError: ${it.error.localizedMessage}")
                        DataResult.Failure(NetworkException(it?.error?.localizedMessage ?:""))
                    }
                }
            }
        } catch (e: Throwable) {
            DataResult.Failure(RuntimeException(e))
        }
    }


}


