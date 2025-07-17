package com.example.listofcities.domain

data class CityListDto(
    val fullName: String,
    val shortName: String,
    val color: Int,
    val cities: List<CityDto>,
    val id: Int = UNDEFINED_ID
) {
    companion object {

        const val UNDEFINED_ID = 0
    }
}

