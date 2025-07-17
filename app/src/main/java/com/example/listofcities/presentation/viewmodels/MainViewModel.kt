package com.example.listofcities.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.listofcities.data.db.CitiesDatabase
import com.example.listofcities.data.repository.CityRepositoryImpl
import com.example.listofcities.domain.usecases.GetCityListUseCase
import com.example.listofcities.domain.usecases.LoadCityListUseCase

class MainViewModel(
    application: Application,
    private val getCityListUseCase: GetCityListUseCase,
    private val loadCityListUseCase: LoadCityListUseCase
) : AndroidViewModel(application) {

    //LiveData<List<CityListDto>>
    val cityList = getCityListUseCase()

    init {
        val db = CitiesDatabase.getInstance(application)
    }


}