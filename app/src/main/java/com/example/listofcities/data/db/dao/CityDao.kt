package com.example.listofcities.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.listofcities.data.db.entity.CityEntity

@Dao
interface CityDao {
    @Query("select * from cities")
    suspend fun getAllCities():List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCities(cities: List<CityEntity>)
}