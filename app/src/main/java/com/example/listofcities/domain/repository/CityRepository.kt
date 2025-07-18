package com.example.listofcities.domain.repository

import androidx.lifecycle.LiveData
import com.example.listofcities.domain.CityDto
import com.example.listofcities.domain.CityListDto

interface CityRepository {
    suspend fun getAllCities(): List<CityDto>

    suspend fun getCityLists(): List<CityListDto>

    suspend fun addCityList(cityList: CityListDto)

    suspend fun insertInitialData()

    suspend fun getCitiesForList(listId: Int): List<CityDto>
}