package com.sulavtimsina.jetpackdemo.data.remote.api

import com.sulavtimsina.jetpackdemo.data.remote.model.Launch
import retrofit2.Response
import retrofit2.http.GET

interface RocketApi {
    @GET("launches")
    suspend fun getAllLaunches(): Response<Launch>
}