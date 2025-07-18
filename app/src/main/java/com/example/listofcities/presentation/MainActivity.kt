package com.example.listofcities.presentation
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.listofcities.R
import com.example.listofcities.data.db.CitiesDatabase
import com.example.listofcities.data.repository.CityRepositoryImpl
import com.example.listofcities.databinding.ActivityMainBinding
import com.example.listofcities.domain.usecases.AddCityListUseCase
import com.example.listofcities.domain.usecases.GetCitiesForListUseCase
import com.example.listofcities.domain.usecases.GetCityListsUseCase
import com.example.listofcities.domain.usecases.InsertInitialDataUseCase
import com.example.listofcities.presentation.viewmodels.CityListViewModel
import com.example.listofcities.presentation.viewmodels.CityListViewModelFactory
import com.example.listofcities.presentation.viewmodels.MenuViewModel
import com.example.listofcities.presentation.viewmodels.MenuViewModelFactory
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var menuViewModel: MenuViewModel
    private lateinit var cityListViewModel: CityListViewModel
    private lateinit var repository: CityRepositoryImpl
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = CitiesDatabase.getInstance(this)
        repository = CityRepositoryImpl(db.cityDao(),db.cityListDao())

        initViewModels()

        lifecycleScope.launch {
            InsertInitialDataUseCase(repository)
            menuViewModel.loadLists()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, CityListFragment())
            .commit()

        val navView = binding.bottomNavigationView
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_city_list -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, CityListFragment())
                        .commit()
                    true
                }
                R.id.nav_menu -> {
//                    MenuFragment().show(supportFragmentManager, "menu")
                    true
                }
                else -> false
            }
        }

        menuViewModel.activeList.observe(this) { list ->
            cityListViewModel.loadCitiesForList(list.id)
            val menuItem = navView.menu.findItem(R.id.nav_menu)
            menuItem.title = list.shortName
            try {
                menuItem.icon?.setTint(list.color)
            } catch (_: Exception) {}
        }

    }

    private fun initViewModels(){
        val menuFactory = MenuViewModelFactory(
            GetCityListsUseCase(repository),
            AddCityListUseCase(repository)
        )
        val listFactory = CityListViewModelFactory(
            GetCitiesForListUseCase(repository)
        )
        menuViewModel = ViewModelProvider(this,menuFactory)[MenuViewModel::class.java]
        cityListViewModel = ViewModelProvider(this, listFactory)[CityListViewModel::class.java]

    }
}