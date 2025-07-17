package com.example.listofcities.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_list")
data class CityListDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fullName: String,
    val shortName: String,
    val color: Int,
    val cities: List<CityDbModel>
)