package com.example.assignment5.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StatDao {
    @Query("SELECT * FROM statistics")
    fun getAll(): List<Stat>

    @Insert
    fun insertAll(vararg stats: Stat)
}