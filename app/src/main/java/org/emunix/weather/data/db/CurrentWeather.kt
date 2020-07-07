package org.emunix.weather.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeather(
    @PrimaryKey
    var city: String,
    var date: Int,
    var temp: Float,
    var pressure: Int,
    var humidity: Int,
    var tempMin: Float,
    var tempMax: Float,
    var windSpeed: Float,
    var windDeg: Int
    )