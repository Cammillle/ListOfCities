package com.example.listofcities.data.repository

import android.app.Application
import android.graphics.Color
import com.example.listofcities.data.db.CitiesDatabase
import com.example.listofcities.data.db.dao.CityDao
import com.example.listofcities.data.db.dao.CityListDao
import com.example.listofcities.data.db.entity.CityEntity
import com.example.listofcities.data.db.entity.CityListCrossRef
import com.example.listofcities.data.db.entity.CityListEntity
import com.example.listofcities.data.db.entity.CityListWithCities
import com.example.listofcities.data.mapper.CityMapper
import com.example.listofcities.domain.CityDto
import com.example.listofcities.domain.CityListDto
import com.example.listofcities.domain.repository.CityRepository

class CityRepositoryImpl(
    private val cityDao: CityDao,
    private val cityListDao: CityListDao
) : CityRepository {

    private val mapper = CityMapper()

    override suspend fun getAllCities(): List<CityDto> {
        return cityDao.getAllCities().map {
            mapper.mapDbModelToDtoCity(it)
        }
    }

    override suspend fun getCityLists(): List<CityListDto> {
        return cityListDao.getAllCityLists().map {
            mapper.mapDbModelToDtoCityList(it.cityList)
        }
    }

    override suspend fun getCityListsWithCities(): List<CityListWithCities> {
        TODO("Not yet implemented")
    }

    override suspend fun addCityList(cityList: CityListDto) {
        cityListDao.insertCityList(
            mapper.mapDtoToDbModelCityList(cityList)
        )
        //  добавление выбранных городов через CrossRef
    }

    override suspend fun getCitiesForList(listId: Int): List<CityDto> {
        return cityListDao.getAllCityLists().firstOrNull {
            it.cityList.id == listId
        }?.cities
            ?.map {
                mapper.mapDbModelToDtoCity(it)
            } ?: emptyList()
    }

    override suspend fun insertInitialData() {
        val cities = listOf(
            CityEntity(1, "Париж", "III век до н. э."),
            CityEntity(2, "Вена", "1147 год"),
            CityEntity(3, "Берлин", "1237 год"),
            CityEntity(4, "Варшава", "1321 год"),
            CityEntity(5, "Милан", "1899 год")
        )
        cityDao.insertCities(cities)

        val europeList = CityListEntity(
            fullName = "Список городов в Европе",
            shortName = "Европа",
            color = Color.BLUE
        )
        val listId = cityListDao.insertCityList(europeList)
        val crossRefs = cities.map { CityListCrossRef(listId.toInt(), it.id) }
        cityListDao.insertCrossRef(crossRefs)
    }


}