package com.example.listofcities.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.listofcities.data.db.CityEntity

@Dao
interface CityDao {
    @Query("select * from cities")
    suspend fun getAllCities():List<CityEntity>
}