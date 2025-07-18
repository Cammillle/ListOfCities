package com.example.listofcities.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_lists")
data class CityListEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val shortName: String,
    val color: Int
)