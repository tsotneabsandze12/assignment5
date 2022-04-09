package com.example.assignment5.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Stat::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun statsDao(): StatDao
}