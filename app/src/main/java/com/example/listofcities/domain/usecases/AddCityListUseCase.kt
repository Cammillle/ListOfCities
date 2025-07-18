package com.example.listofcities.domain.usecases

import com.example.listofcities.domain.CityListDto
import com.example.listofcities.domain.repository.CityRepository

class AddCityListUseCase(private val repo: CityRepository) {
    suspend operator fun invoke(list: CityListDto) = repo.addCityList(list)
}