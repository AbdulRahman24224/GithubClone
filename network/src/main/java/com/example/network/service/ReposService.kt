package com.example.network.service

import com.example.network.models.NetworkResponse
import com.example.network.models.response.ReposResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ReposService {

    @GET("search/repositories?q=language=+sort:stars")
    suspend fun getReposByRating(
        @Query("page") page: String,
    ): NetworkResponse< ReposResponse , String>


}