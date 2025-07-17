package com.example.listofcities.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityListDbModel::class], version = 1, exportSchema = false)
abstract class CitiesDatabase : RoomDatabase() {
    companion object {
        private var db: CitiesDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): CitiesDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        CitiesDatabase::class.java,
                        DB_NAME
                    )
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun cityDao(): CityDao

}