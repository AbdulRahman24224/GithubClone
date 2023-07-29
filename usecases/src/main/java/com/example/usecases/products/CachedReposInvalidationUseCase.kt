package com.example.usecases.products

interface CachedReposInvalidationUseCase {

    suspend operator fun invoke( forceInvalidate :Boolean , currentTime : Long): Boolean
}