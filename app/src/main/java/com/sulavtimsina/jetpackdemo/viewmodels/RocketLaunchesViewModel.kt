package com.sulavtimsina.jetpackdemo.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sulav.whichgame.util.NetworkUtil
import com.sulavtimsina.jetpackdemo.data.remote.model.Launch
import com.sulavtimsina.jetpackdemo.data.remote.model.LaunchItem
import com.sulavtimsina.jetpackdemo.repository.RocketLaunchesRepository
import com.sulavtimsina.jetpackdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import java.util.concurrent.CancellationException
import javax.inject.Inject

@HiltViewModel
class RocketLaunchesViewModel @Inject constructor(
    private val launchesRepository: RocketLaunchesRepository

) : ViewModel() {
    val launchList: MutableLiveData<Resource<Launch>> = MutableLiveData()
    val singleLaunchList: MutableLiveData<Resource<LaunchItem>> = MutableLiveData()

    suspend fun getLaunchList(context:Context) {
        launchList.postValue(Resource.Loading())
        try {
            if (NetworkUtil.hasInternetConnection(context)) {

                val response = launchesRepository.getLaunchApi()

                if (response.isSuccessful) {

                    response.body()?.let {
                        launchList.postValue(Resource.Success(it))
                    }

                } else launchList.postValue(Resource.Error(response.message()))
            }

        } catch (ex: Exception) {
            when (ex) {
                is CancellationException -> {}
                is IOException -> launchList.postValue(Resource.Error("Network Failure:${ex.message}"))
                else -> launchList.postValue(Resource.Error(ex.message.toString()))
            }
        }
    }
    suspend fun getSingleLaunchList(context:Context,flightNumber:Int) {
        singleLaunchList.postValue(Resource.Loading())
        try {
            if (NetworkUtil.hasInternetConnection(context)) {

                val response = launchesRepository.getSingleLaunchApi(flightNumber)

                if (response.isSuccessful) {

                    response.body()?.let {
                        singleLaunchList.postValue(Resource.Success(it))
                    }

                } else singleLaunchList.postValue(Resource.Error(response.message()))
            }

        } catch (ex: Exception) {
            when (ex) {
                is CancellationException -> {}
                is IOException -> singleLaunchList.postValue(Resource.Error("Network Failure:${ex.message}"))
                else -> singleLaunchList.postValue(Resource.Error(ex.message.toString()))
            }
        }
    }

}