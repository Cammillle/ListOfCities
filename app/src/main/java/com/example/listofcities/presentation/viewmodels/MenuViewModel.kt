package com.example.listofcities.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofcities.domain.CityListDto
import com.example.listofcities.domain.usecases.AddCityListUseCase
import com.example.listofcities.domain.usecases.GetCityListsUseCase
import kotlinx.coroutines.launch

//Добавление списка городов в карусель
class MenuViewModel(
    private val getCityLists: GetCityListsUseCase,
    private val addCityList: AddCityListUseCase
) : ViewModel() {

    private val _lists = MutableLiveData<List<CityListDto>>()
    val lists: LiveData<List<CityListDto>> = _lists

    val activeList = MutableLiveData<CityListDto>()

    fun loadLists() {
        viewModelScope.launch {
            val loadedLists = getCityLists()
            _lists.value = loadedLists
            if (activeList.value == null && loadedLists.isNotEmpty()) {
                activeList.value = loadedLists.first()
            }
        }
    }

    fun selectList(list: CityListDto) {
        activeList.value = list
    }

    fun addNewList(list: CityListDto) {
        viewModelScope.launch {
            addCityList(list)
            loadLists()
        }

    }


}