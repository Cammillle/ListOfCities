package com.example.listofcities.domain.repository

import androidx.lifecycle.LiveData
import com.example.listofcities.domain.CityListDto

interface CityRepository {

    fun getCityList():LiveData<List<CityListDto>>

    suspend fun loadCityList(cityList: CityListDto)
}