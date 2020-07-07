package org.emunix.weather.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherJsonData (
    val id: Int,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val sys: Sys,
    @Json(name = "name") val city: String,
    @Json(name = "dt") val timestamp: Int
) {

    @JsonClass(generateAdapter = true)
    data class Main(
        val temp: Float,
        val pressure: Int,
        val humidity: Int,
        val temp_min: Float,
        val temp_max: Float
    )

    @JsonClass(generateAdapter = true)
    data class Weather(
        val id :Int,
        val main: String,
        val description : String
    )

    @JsonClass(generateAdapter = true)
    data class Wind(
        val speed: Float,
        val deg: Int
    )

    @JsonClass(generateAdapter = true)
    data class Sys(
        val country: String
    )
}
