package org.emunix.weather.data

data class Forecast(
    val city: String,
    val forecastData: List<ForecastData>
) {

    data class ForecastData(
        val date: String,
        val temp: Float,
        val pressure: Int,
        val humidity: Int,
        val temp_min: Float,
        val temp_max: Float,
        val wind_speed: Float,
        val wind_deg: Int
    )
}