package com.example.listofcities.data.db.entity

import androidx.room.Entity

@Entity(primaryKeys = ["listId", "cityId"])
data class CityListCrossRef(
    val listId: Int,
    val cityId: Int
)
