package com.example.usecases.products

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo

interface SavePreferenceValueUseCase {

    operator  fun invoke(key:String , value : String): Unit
}