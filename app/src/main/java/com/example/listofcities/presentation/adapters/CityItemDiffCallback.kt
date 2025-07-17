package com.example.listofcities.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.listofcities.domain.CityDto

class CityItemDiffCallback:DiffUtil.ItemCallback<CityDto>() {
    override fun areItemsTheSame(oldItem: CityDto, newItem: CityDto): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CityDto, newItem: CityDto): Boolean {
        return oldItem == newItem
    }
}