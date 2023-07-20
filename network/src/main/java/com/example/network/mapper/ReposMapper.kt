package com.example.network.mapper

import com.example.domain_models.repos.Repo
import com.example.network.models.response.RepoJson


fun RepoJson.toDomain() = Repo(
    name = name,
    description = description,
    language = language,
    stars = stars,
    ownerName = owner?.login,
    ownerAvatarUrl = owner?.avatar_url
)