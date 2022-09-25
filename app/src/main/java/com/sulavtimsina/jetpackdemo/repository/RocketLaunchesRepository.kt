package com.sulavtimsina.jetpackdemo.repository

import com.sulavtimsina.jetpackdemo.data.remote.api.LaunchApi
import javax.inject.Inject

class RocketLaunchesRepository @Inject constructor(
    private val launchApi: LaunchApi
) {
    suspend fun getLaunchApi() = launchApi.getLaunches()
    suspend fun getSingleLaunchApi(flight_number:Int) = launchApi.getSingleLaunch(flight_number)
}