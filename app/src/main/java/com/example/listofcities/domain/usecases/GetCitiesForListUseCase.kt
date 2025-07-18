package com.example.listofcities.domain.usecases

import com.example.listofcities.domain.repository.CityRepository

class GetCitiesForListUseCase(private val repo: CityRepository) {
    suspend operator fun invoke(listId: Int) = repo.getCitiesForList(listId)
}