package com.example.listofcities.data.db

import android.content.Context
import android.graphics.Color
import android.telecom.Call
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [CityListDbModel::class, CityDbModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
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
                    ).addCallback(object :Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                prepopulateData(getInstance(context))
                            }
                        }
                    })
                        .build()
                db = instance
                return instance
            }
        }

        private suspend fun prepopulateData(db:CitiesDatabase){
            //Все доступные города
            val allCities = listOf(
                CityDbModel(name = "Париж", year = "III век до н.э."),
                CityDbModel(name = "Вена", year = "1147 год"),
                CityDbModel(name = "Берлин", year = "1237 год"),
                CityDbModel(name = "Варшава", year = "1321 год"),
                CityDbModel(name = "Милан", year = "1899 год"),
                CityDbModel(name = "Лондон", year = "43 год"),
                CityDbModel(name = "Мадрид", year = "852 год"),
                CityDbModel(name = "Рим", year = "753 год до н.э."),
                CityDbModel(name = "Прага", year = "870 год"),
                CityDbModel(name = "Будапешт", year = "1873 год")
            )
            val cityList = CityListDbModel(
                fullName = "Список городов в Европе",
                shortName = "Европа",
                color = Color.BLUE,
                cities = allCities
            )
            db.cityDao().loadCityList(cityList)
        }
    }

    abstract fun cityDao(): CityDao



}