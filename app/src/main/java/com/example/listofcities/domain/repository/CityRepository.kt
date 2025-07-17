package com.example.listofcities.domain.repository

import androidx.lifecycle.LiveData
import com.example.listofcities.domain.CityList

interface CityRepository {

    suspend fun getCityList():LiveData<CityList>

    suspend fun loadCityList(cityList: CityList)
}