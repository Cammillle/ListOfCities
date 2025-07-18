package com.example.listofcities.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listofcities.domain.usecases.AddCityListUseCase
import com.example.listofcities.domain.usecases.GetCityListsUseCase

class MenuViewModelFactory(
    private val getCityListsUseCase: GetCityListsUseCase,
    private val addCityListUseCase: AddCityListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MenuViewModel(getCityListsUseCase, addCityListUseCase) as T
    }
}