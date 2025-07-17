package com.example.listofcities.domain.repository

import androidx.lifecycle.LiveData
import com.example.listofcities.domain.CityListDto

interface CityRepository {

    suspend fun getCityList():LiveData<CityListDto>

    suspend fun loadCityList(cityList: CityListDto)
}