package com.sulavtimsina.jetpackdemo.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sulavtimsina.jetpackdemo.R
import com.sulavtimsina.jetpackdemo.data.local.db.AppDatabase
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
        insertIntoDb()
    }

    private fun insertIntoDb() {
        this.lifecycleScope.launch {
            val dao = appDatabase.getPersonDao()
            dao.insertIntoDb(Person(name = "Sulav", age = 32))
        }
    }
}