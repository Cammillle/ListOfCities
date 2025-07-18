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
import com.example.listofcities.data.db.entity.CityEntity
import com.example.listofcities.data.db.entity.CityListCrossRef
import com.example.listofcities.data.db.entity.CityListEntity
import com.example.listofcities.data.repository.CityRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [CityListEntity::class, CityEntity::class, CityListCrossRef::class],
    version = 1,
    exportSchema = false
)
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
                        context.applicationContext,
                        CitiesDatabase::class.java,
                        DB_NAME
                    ).addCallback(object :Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                getInstance(context).let {
                                    it.cityDao()
                                    it.cityListDao()
                                    CityRepositoryImpl(it.cityDao(),it.cityListDao()).insertInitialData()
                                }
                            }
                        }
                    })
                        .build()
                db = instance
                return instance
            }
        }


    }



}