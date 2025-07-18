package com.example.listofcities.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.listofcities.domain.CityDto
import com.example.listofcities.domain.usecases.GetCitiesForListUseCase
import kotlinx.coroutines.launch

//Отображение городов из списка на главном экране
class CityListViewModel(
    private val getCitiesForList: GetCitiesForListUseCase
) : ViewModel() {

    private val _cities = MutableLiveData<List<CityDto>>()
    val cities: LiveData<List<CityDto>> = _cities

    init {
        viewModelScope.launch {
            val lists = getCitiesForList()
        }
    }

    fun loadCitiesForList(listId:Int){
        viewModelScope.launch {
            _cities.value = getCitiesForList(listId)
        }
    }

}