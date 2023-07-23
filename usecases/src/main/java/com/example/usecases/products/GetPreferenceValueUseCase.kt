package com.example.usecases.products

import com.example.domain_models.network.DataResult
import com.example.domain_models.repos.Repo

interface GetPreferenceValueUseCase {

     operator  fun invoke(key:String): String?
}