package com.example.listofcities.domain.usecases

import com.example.listofcities.domain.repository.CityRepository

class GetCitiesUseCase(private val repo: CityRepository) {
    suspend operator fun invoke() = repo.getAllCities()
}