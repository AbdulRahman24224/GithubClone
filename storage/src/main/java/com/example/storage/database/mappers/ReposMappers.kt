package com.example.storage.database.mappers

import com.example.domain_models.repos.Repo
import com.example.storage.database.entity.RepoEntity

fun Repo.toEntity() = RepoEntity(
    id = id,
    name = name,
    description = description,
    language = language,
    stars = stars,
    ownerName = ownerName,
    ownerAvatarUrl = ownerAvatarUrl
)

fun RepoEntity.toDomain() = Repo(
    id = id,
    name = name,
    description = description,
    language = language,
    stars = stars,
    ownerName = ownerName,
    ownerAvatarUrl = ownerAvatarUrl
)