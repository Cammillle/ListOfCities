package com.example.listofcities.domain.usecases

import com.example.listofcities.domain.CityList
import com.example.listofcities.domain.repository.CityRepository

class LoadCityListUseCase(
    private val repository: CityRepository
) {
    suspend operator fun invoke(cityList: CityList) = repository.loadCityList(cityList)
}