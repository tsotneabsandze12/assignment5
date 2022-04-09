package com.example.assignment5.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "statistics")
data class Stat(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "distance_ran")
    val distanceRan: Double,

    @ColumnInfo(name = "distance_swam")
    val distanceSwam: Double,

    @ColumnInfo(name = "calories_consumed")
    val caloriesConsumed: Double,

)