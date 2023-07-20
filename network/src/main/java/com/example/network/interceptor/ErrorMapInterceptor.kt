package com.example.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * This interceptor help to map error response with response code 200 to error response.
 * This need for correct working of [NetworkResponseCall]
 *
 * @see [ErrorJson]
 */
class ErrorMapInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).run {
            try {
                // chek just start of response body
                if (peekBody(10).string() == "{\"errors\":" && code >= 200 && code <= 299) {
                    this.newBuilder().code(418).build()
                } else {
                    this
                }
            } catch (e: Throwable) {
                this
            }
        }
    }
}