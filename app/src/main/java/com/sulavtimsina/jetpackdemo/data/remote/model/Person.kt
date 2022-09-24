package com.sulavtimsina.jetpackdemo.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val age: Int
)
