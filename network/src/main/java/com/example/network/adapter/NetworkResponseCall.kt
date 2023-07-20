package com.example.network.adapter

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.network.models.NetworkResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Request
import okhttp3.ResponseBody
import okio.IOException
import okio.Timeout
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
internal class NetworkResponseCall<S : Any, E : Any> @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResponse<S, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                val etag = response.headers()["etag"] ?: ""

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResponse.Success(
                                    body,
                                    etag
                                )
                            )
                        )
                    } else {
                        // Response is successful but the body is null

                        try {
                            val jsonObject = JSONObject(error?.string() ?: "{}")
                            val errorMessage: String = jsonObject.getString("error")

                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.UnknownError(
                                        RuntimeException(errorMessage)
                                    )
                                )
                            )

                        } catch (e: Exception) {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.UnknownError(
                                        RuntimeException(error?.string() ?: "An Error Occurred !")
                                    )
                                )
                            )

                        }


                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> {
                            try {
                                error.string()
                            } catch (ex: Exception) {
                                null
                            }
                        }
                    }
                    if (errorBody != null) {

                        var errorString = "Unknown error"
                        kotlin.runCatching {
                            errorString = JSONObject(errorBody).getString("error")
                        }

                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResponse.ApiError(
                                    errorString as E,
                                    code,
                                    etag,
                                    errorBody
                                )
                            )
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.NetworkError(IOException("UnknownError")))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {

                val networkResponse = when (throwable) {
                    is UnknownHostException -> {

                        LocalBroadcastManager.getInstance(applicationContext)
                            .sendBroadcast(Intent("NO_CONNECTION"))

                        NetworkResponse.NetworkError(IOException("Internet connection error"))
                    }
                    is IOException -> NetworkResponse.NetworkError(throwable)
                    else -> NetworkResponse.UnknownError(throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(applicationContext, delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}