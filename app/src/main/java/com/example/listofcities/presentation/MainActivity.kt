package com.example.listofcities.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listofcities.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bottomNav = binding.bottomNavigationView
        bottomNav.itemIconTintList = null





    }




}