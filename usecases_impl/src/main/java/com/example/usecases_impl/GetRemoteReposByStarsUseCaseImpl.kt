package com.example.usecases_impl

import android.util.Log
import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo
import com.example.repositories.ReposRepository
import com.example.usecases.products.GetRemoteReposByStarsUseCase
import javax.inject.Inject


class GetRemoteReposByStarsUseCaseImpl @Inject constructor(
    private val reposRepository: ReposRepository ,
) : GetRemoteReposByStarsUseCase {

    override suspend fun invoke(page : Int): DataResult<List<Repo>> {
        Log.d("request:","request: usesacase")
        return reposRepository.getRemoteReposByStars(page)

    }

}