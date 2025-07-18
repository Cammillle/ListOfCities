package com.example.listofcities.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.listofcities.data.db.entity.CityListCrossRef
import com.example.listofcities.data.db.entity.CityListEntity
import com.example.listofcities.data.db.entity.CityListWithCities

@Dao
interface CityListDao {
    @Query("SELECT * FROM city_lists")
    suspend fun getAllCityLists(): List<CityListWithCities>

//    @Query("SELECT * FROM city_list where id == :id limit 1")
//    suspend fun getCityList(id: Int): CityListEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityList(cityList: CityListEntity):Long

    @Insert
    suspend fun insertCrossRef(ref: List<CityListCrossRef>)
}