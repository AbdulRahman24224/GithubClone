package com.example.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "RepoEntity" )
data class RepoEntity(
    @PrimaryKey
    var id: Long?=null,
    var name: String?=null,
    var description: String?=null,
    var language: String?=null,
    var stars: Int?=null,
    var ownerName:String?=null,
    var ownerAvatarUrl:String?=null,
    var page :Int?=null

)
