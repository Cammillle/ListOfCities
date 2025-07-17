package com.example.listofcities.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()
    private val type = object : TypeToken<List<CityDbModel>>() {}.type

    @TypeConverter
    fun toString(list: List<CityDbModel>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromString(value: String):List<CityDbModel>{
        return if (value == null) {
            emptyList()
        } else {
            gson.fromJson(value, type)
        }
    }

}