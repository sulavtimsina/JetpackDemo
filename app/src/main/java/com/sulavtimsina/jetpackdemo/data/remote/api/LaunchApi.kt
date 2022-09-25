package com.sulavtimsina.jetpackdemo.data.remote.api

import com.sulavtimsina.jetpackdemo.data.remote.model.Launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface LaunchApi {
    @GET("launches")
    suspend fun getLaunches(): Response<Launch>
}

class RetrofitService {
    companion object {
        var loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        var client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        fun getApiService() = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}