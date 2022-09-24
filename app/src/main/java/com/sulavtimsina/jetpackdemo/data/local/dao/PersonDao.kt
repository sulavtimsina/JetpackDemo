package com.sulavtimsina.jetpackdemo.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sulavtimsina.jetpackdemo.data.remote.model.Person

@Dao
interface PersonDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertIntoDb(person: Person)

    @Query("SELECT * FROM person_table")
    fun getPersonFromDb(): LiveData<List<Person>>

    @Delete
    suspend fun deletePersonFromDb(person: Person)
}