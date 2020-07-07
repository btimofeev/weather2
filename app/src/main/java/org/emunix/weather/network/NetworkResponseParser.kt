package org.emunix.weather.network

import org.emunix.weather.network.data.CurrentWeatherJsonData
import org.emunix.weather.network.data.ForecastJsonData

interface NetworkResponseParser {
    fun parseCurrentWeather(networkResponse: String): CurrentWeatherJsonData

    fun parseForecast(networkResponse: String): ForecastJsonData
}