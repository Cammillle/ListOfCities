package com.example.listofcities.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listofcities.domain.usecases.GetCitiesForListUseCase

class CityListViewModelFactory(
    private val getCitiesForListUseCase: GetCitiesForListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CityListViewModel(getCitiesForListUseCase) as T
    }
}