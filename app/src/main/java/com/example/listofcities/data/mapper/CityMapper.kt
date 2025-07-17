package com.example.listofcities.data.mapper

import com.example.listofcities.data.db.CityDbModel
import com.example.listofcities.data.db.CityListDbModel
import com.example.listofcities.domain.CityDto
import com.example.listofcities.domain.CityListDto

class CityMapper {

    fun mapDtoToDbModelCityList(dto: CityListDto) = CityListDbModel(
        id = dto.id,
        fullName = dto.fullName,
        shortName = dto.shortName,
        color = dto.color,
        cities = dto.cities.map { mapDtoToDbModelCity(it) }
    )

    fun mapDtoToDbModelCity(dto:CityDto) = CityDbModel(
        year = dto.year,
        name = dto.name
    )

    fun mapDbModelToDtoCityList(dbModel: CityListDbModel) = CityListDto(
        id = dbModel.id,
        fullName = dbModel.fullName,
        shortName = dbModel.shortName,
        color = dbModel.color,
        cities = dbModel.cities.map {mapDbModelToDtoCity(it) }
    )

    fun mapDbModelToDtoCity(dbModel:CityDbModel) = CityDto(
        year = dbModel.year,
        name = dbModel.name
    )
}