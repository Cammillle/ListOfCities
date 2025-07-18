package com.example.listofcities.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listofcities.data.db.CitiesDatabase
import com.example.listofcities.data.repository.CityRepositoryImpl
import com.example.listofcities.domain.CityListDto
import com.example.listofcities.domain.usecases.GetCityListUseCase
import com.example.listofcities.domain.usecases.GetCityListsUseCase
import com.example.listofcities.domain.usecases.LoadCityListUseCase

class MainViewModel(
    application: Application,
    private val getCityListsUseCase: GetCityListsUseCase,
    private val getCityListUseCase: GetCityListUseCase,
    private val loadCityListUseCase: LoadCityListUseCase
) : AndroidViewModel(application) {

    private val currentCityList = MutableLiveData<CityListDto>()
    val currentCityList_: LiveData<CityListDto>
        get() = currentCityList

    //LiveData<List<CityListDto>>
    val cityLists = getCityListsUseCase()

    //LiveData<CityListDto>
    fun getCitiesFromCityList(id: Int) = getCityListUseCase(id)



    init {

    }


}