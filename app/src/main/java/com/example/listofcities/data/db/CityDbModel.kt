package com.example.listofcities.data.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CityDbModel(
    @PrimaryKey
    val name: String,
    val year: String
):Parcelable
