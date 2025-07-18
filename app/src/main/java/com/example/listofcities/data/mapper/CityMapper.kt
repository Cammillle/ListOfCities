package com.example.listofcities.data.mapper

import com.example.listofcities.data.db.entity.CityEntity
import com.example.listofcities.data.db.entity.CityListEntity
import com.example.listofcities.domain.CityDto
import com.example.listofcities.domain.CityListDto

class CityMapper {

    fun mapDtoToDbModelCityList(dto: CityListDto) = CityListEntity(
        id = dto.id,
        fullName = dto.fullName,
        shortName = dto.shortName,
        color = dto.color,
    )

    fun mapDtoToDbModelCity(dto:CityDto) = CityEntity(
        year = dto.year,
        name = dto.name
    )

    fun mapDbModelToDtoCityList(dbModel: CityListEntity) = CityListDto(
        id = dbModel.id,
        fullName = dbModel.fullName,
        shortName = dbModel.shortName,
        color = dbModel.color,
    )

    fun mapDbModelToDtoCity(dbModel: CityEntity) = CityDto(
        year = dbModel.year,
        name = dbModel.name
    )
}