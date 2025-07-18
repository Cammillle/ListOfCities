package com.example.listofcities.data.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CityListWithCities(
    @Embedded val cityList: CityListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(CityListCrossRef::class)
    )
    val cities: List<CityEntity>
)
