package com.example.listofcities.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofcities.domain.CityDto
import com.example.listofcities.domain.usecases.GetCitiesUseCase
import kotlinx.coroutines.launch

//Отображение городов из списка на главном экране
class CityListViewModel(
    private val getCities: GetCitiesUseCase
) : ViewModel() {

    private val _cities = MutableLiveData<List<CityDto>>()
    val cities: LiveData<List<CityDto>> = _cities

    fun loadCities(){
        viewModelScope.launch {
            _cities.value = getCities()
        }
    }

}