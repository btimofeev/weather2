package org.emunix.weather.data.db

import androidx.room.*
import io.reactivex.Maybe

@Dao
interface CurrentWeatherDAO {

    @Query("SELECT * FROM current_weather WHERE city = :city")
    fun getWeather(city: String): Maybe<CurrentWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: CurrentWeather)

    @Delete
    fun delete(weather: CurrentWeather)
}