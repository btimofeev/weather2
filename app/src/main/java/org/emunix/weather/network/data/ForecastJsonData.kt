package org.emunix.weather.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastJsonData(
    val city: City,
    val list: List<Weather>
) {
    @JsonClass(generateAdapter = true)
    data class City (
        val id: Int,
        val name: String
    )

    @JsonClass(generateAdapter = true)
    data class Weather(
        val dt_txt: String,
        val main: Main,
        val wind: Wind
    )

    @JsonClass(generateAdapter = true)
    data class Main(
        val temp: Float,
        val pressure: Int,
        val humidity: Int,
        val temp_min: Float,
        val temp_max: Float
    )

    @JsonClass(generateAdapter = true)
    data class Wind(
        val speed: Float,
        val deg: Int
    )
}