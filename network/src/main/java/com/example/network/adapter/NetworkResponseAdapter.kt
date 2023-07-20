package com.example.network.adapter

import android.content.Context
import com.example.network.models.NetworkResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkResponseAdapter<S : Any, E : Any> @Inject constructor(
    @ApplicationContext private val context: Context,
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<NetworkResponse<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
        return NetworkResponseCall(context, call, errorBodyConverter)
    }
}