package com.example.domain_models.repos


data class Repo(
    val name: String?=null,
    val description: String?=null,
    val language: String?=null,
    val stars: Int?=null,
    val ownerName:String?=null,
    val ownerAvatarUrl:String?=null,
)
