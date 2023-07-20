package com.example.network.service

import com.example.network.models.NetworkResponse
import com.example.network.models.response.ReposResponse
import retrofit2.Call
import retrofit2.http.GET


interface ReposService {

    @GET("search/repositories?q=language=+sort:stars")
    suspend fun getReposByRating(): NetworkResponse< ReposResponse , String>


}