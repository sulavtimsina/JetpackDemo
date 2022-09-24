package com.sulavtimsina.jetpackdemo.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sulavtimsina.jetpackdemo.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "personDatabase")
            .fallbackToDestructiveMigration()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .addCallback(object : RoomDatabase.Callback() {})
            .build()
    }
}