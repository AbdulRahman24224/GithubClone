package com.example.githubclone.di

import android.content.Context
import com.example.githubclone.Environments.DEV_BASE_URL
import com.example.githubclone.Environments.PROD_BASE_URL
import com.example.network.adapter.NetworkResponseAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            if (true/*BuildConfig.DEBUG*/) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,

        ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()

        return okHttpBuilder.run {
            connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
            if (true/*BuildConfig.DEBUG*/) addInterceptor(httpLoggingInterceptor)

            build()
        }
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        @ApplicationContext context: Context
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(NetworkResponseAdapterFactory(context))
        .baseUrl(if (true /*BuildConfig.DEBUG*/) DEV_BASE_URL else PROD_BASE_URL)
        .client(okHttpClient)
        .build()

    companion object {
        private const val TIME_OUT = 60L
    }
}
