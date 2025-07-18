package com.example.listofcities.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listofcities.R
import com.example.listofcities.domain.CityDto
import java.util.Collections

class CityAdapter: ListAdapter<CityDto, CityAdapter.CityViewHolder>(CityItemDiffCallback()),
    ItemTouchHelperAdapter {

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName = itemView.findViewById<TextView>(R.id.tvCityName)
        val cityYear = itemView.findViewById<TextView>(R.id.tvCityYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_list_item, parent, false)
        return CityViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val cityItem = getItem(position)
        holder.cityName.text = cityItem.name
        holder.cityYear.text = cityItem.year

    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val currentList = currentList.toMutableList()
        Collections.swap(currentList,fromPosition,toPosition)
        submitList(currentList)
        return true
    }


}