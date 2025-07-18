package com.example.listofcities.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.listofcities.R
import com.example.listofcities.databinding.ActivityMainBinding
import com.example.listofcities.presentation.viewmodels.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNavView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        viewModel.currentCityList_.observe(this){

        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView,ListFragment.newInstance())
            .commit()

    }

    private fun setupBottomNavView(){
        val bottomNav = binding.bottomNavigationView
        bottomNav.itemIconTintList = null

    }


}