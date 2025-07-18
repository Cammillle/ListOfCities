package com.example.listofcities.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * FROM city_list")
    fun getCityLists(): LiveData<List<CityListDbModel>>

    @Query("SELECT * FROM city_list where id == :id limit 1")
    fun getCityList(id: Int): LiveData<CityListDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun loadCityList(cityList: CityListDbModel)
}