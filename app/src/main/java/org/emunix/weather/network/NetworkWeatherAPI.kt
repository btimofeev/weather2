package org.emunix.weather.network

interface NetworkWeatherAPI {

    fun getCurrentWeather(city: String): NetworkResult

    fun getNextDaysForecast(city: String): NetworkResult

}