package com.sulavtimsina.jetpackdemo.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sulavtimsina.jetpackdemo.R
import com.sulavtimsina.jetpackdemo.data.local.db.AppDatabase
import com.sulavtimsina.jetpackdemo.data.remote.api.LaunchApi
import com.sulavtimsina.jetpackdemo.data.remote.api.RetrofitService
import com.sulavtimsina.jetpackdemo.data.remote.model.Person
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchFromApi()
    }

    private fun fetchFromApi() {
        lifecycleScope.launch {
            var a =RetrofitService.getApiService().create(LaunchApi::class.java)
            val b = a.getLaunches()
            Log.d("TAG", "fetchFromApi: sdf")
        }
    }

    private fun insertIntoDb() {
        this.lifecycleScope.launch {
            var dao = appDatabase.getPersonDao()
            dao.insertIntoDb(Person(name = "Sulav", age = 32))
        }
    }
}
