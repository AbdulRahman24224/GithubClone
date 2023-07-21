package com.example.network.models.response

import com.google.gson.annotations.SerializedName


data class OwnerJson(
    val login: String?=null,
    val avatar_url: String?=null,
    val type: String?=null,
)

data class RepoJson(
    val owner: OwnerJson?=null,
    val name: String?=null,
    val description: String?=null,
    val language: String?=null,
    val stars: Int?=null,
    val id: Long?=null,
)

data class ReposResponse(

    @SerializedName("items")
    val items: List<RepoJson>
)
