package com.example.listofcities.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.listofcities.data.db.CitiesDatabase
import com.example.listofcities.data.mapper.CityMapper
import com.example.listofcities.domain.CityDto
import com.example.listofcities.domain.CityListDto
import com.example.listofcities.domain.repository.CityRepository

class CityRepositoryImpl(
    private val application: Application
) : CityRepository {

    private val cityDao = CitiesDatabase.getInstance(application).cityDao()
    private val cityListDao = CitiesDatabase.getInstance(application).cityListDao()
    private val mapper = CityMapper()

    override suspend fun getAllCities(): List<CityDto> {
        return cityDao.getAllCities().map {
            mapper.mapDbModelToDtoCity(it)
        }
    }

    override suspend fun getCityLists(): List<CityListDto> {
        return cityListDao.getAllCityLists().map {
            mapper.mapDbModelToDtoCityList(it)
        }
    }

    override suspend fun addCityList(cityList: CityListDto) {
        cityListDao.insertCityList(
            mapper.mapDtoToDbModelCityList(cityList)
        )
    }


}