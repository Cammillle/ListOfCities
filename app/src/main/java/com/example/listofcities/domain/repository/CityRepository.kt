package com.example.listofcities.domain.repository

import androidx.lifecycle.LiveData
import com.example.listofcities.domain.CityListDto

interface CityRepository {

    fun getCityLists():LiveData<List<CityListDto>>

    fun getCityList(id:Int):LiveData<CityListDto>

    suspend fun loadCityList(cityList: CityListDto)
}