package com.example.listofcities.domain

data class CityListDto(
    val id: Int,
    val fullName: String,
    val shortName: String,
    val color: Int,
    val cities: List<CityDto>
)
