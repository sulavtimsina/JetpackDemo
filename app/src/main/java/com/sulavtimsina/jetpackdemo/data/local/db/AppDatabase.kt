package com.sulavtimsina.jetpackdemo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sulavtimsina.jetpackdemo.data.local.dao.PersonDao
import com.sulavtimsina.jetpackdemo.data.remote.model.Person

@Database(
    entities = [Person::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPersonDao(): PersonDao
}