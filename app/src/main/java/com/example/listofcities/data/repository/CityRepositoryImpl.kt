package com.example.listofcities.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.listofcities.data.db.CitiesDatabase
import com.example.listofcities.data.mapper.CityMapper
import com.example.listofcities.domain.CityListDto
import com.example.listofcities.domain.repository.CityRepository

class CityRepositoryImpl(
    private val application: Application
) : CityRepository {

    private val dao = CitiesDatabase.getInstance(application).cityDao()
    private val mapper = CityMapper()

    override fun getCityList(): LiveData<List<CityListDto>> {
        return dao.getCityList().map { it ->
            it.map {
                mapper.mapDbModelToDtoCityList(it)
            }
        }
    }

    override suspend fun loadCityList(cityList: CityListDto) {
        dao.loadCityList(mapper.mapDtoToDbModelCityList(cityList))
    }
}