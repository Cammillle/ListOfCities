package com.example.listofcities.domain.usecases

import com.example.listofcities.domain.repository.CityRepository

class GetCityListUseCase(
    private val repository: CityRepository
) {
    suspend operator fun invoke() = repository.getCityList()
}