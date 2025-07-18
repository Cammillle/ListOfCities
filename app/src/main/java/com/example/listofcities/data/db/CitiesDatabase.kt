package com.example.listofcities.data.db

import android.content.Context
import android.graphics.Color
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.listofcities.data.db.dao.CityDao
import com.example.listofcities.data.db.dao.CityListDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [CityListEntity::class, CityEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CitiesDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun cityListDao(): CityListDao

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
                CityEntity(name = "Париж", year = "III век до н.э."),
                CityEntity(name = "Вена", year = "1147 год"),
                CityEntity(name = "Берлин", year = "1237 год"),
                CityEntity(name = "Варшава", year = "1321 год"),
                CityEntity(name = "Милан", year = "1899 год"),
                CityEntity(name = "Лондон", year = "43 год"),
                CityEntity(name = "Мадрид", year = "852 год"),
                CityEntity(name = "Рим", year = "753 год до н.э."),
                CityEntity(name = "Прага", year = "870 год"),
                CityEntity(name = "Будапешт", year = "1873 год")
            )
            val cityList = CityListEntity(
                id = 1,
                fullName = "Список городов в Европе",
                shortName = "Европа",
                color = Color.BLUE,
                cities = allCities
            )
            db.cityDao().loadCityList(cityList)
        }
    }



}