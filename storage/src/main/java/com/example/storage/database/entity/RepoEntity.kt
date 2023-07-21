package com.example.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "RepoEntity" )
data class RepoEntity(
    @PrimaryKey
    val id: Long?=null,
    val name: String?=null,
    val description: String?=null,
    val language: String?=null,
    val stars: Int?=null,
    val ownerName:String?=null,
    val ownerAvatarUrl:String?=null,

)
