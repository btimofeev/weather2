package org.emunix.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrentWeather::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun currentWeatherDAO(): CurrentWeatherDAO

}