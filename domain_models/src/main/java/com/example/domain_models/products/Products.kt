package com.example.domain_models.products

data class Owner(
    val login: String?=null,
    val id: Int?=null,
    val avatar_url: String?=null,
    val type: String?=null,
)

data class Repo(
    val owner: Owner?=null,
    val name: String?=null,
    val description: String?=null,
    val language: String?=null,
    val stars: Int?=null,
)

data class SearchResult(
    val items: List<Repo>
)