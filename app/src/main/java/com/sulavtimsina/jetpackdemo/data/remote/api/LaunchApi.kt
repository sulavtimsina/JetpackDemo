package com.sulavtimsina.jetpackdemo.data.remote.api

import com.sulavtimsina.jetpackdemo.data.remote.model.Launch
import com.sulavtimsina.jetpackdemo.data.remote.model.LaunchItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface LaunchApi {
    @GET("launches")
    suspend fun getLaunches(): Response<Launch>

    @GET("launches/{flight_number}")
    suspend fun getSingleLaunch(
        @Path("flight_number") flight_number: Int): Response<LaunchItem>
}
