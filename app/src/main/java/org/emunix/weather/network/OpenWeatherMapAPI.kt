package org.emunix.weather.network

import org.emunix.weather.BuildConfig
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class OpenWeatherMapAPI : NetworkWeatherAPI {

    override fun getCurrentWeather(city: String): NetworkResult {
        return makeRequest("weather?q=$city")
    }

    override fun getNextDaysForecast(city: String): NetworkResult {
        return makeRequest("forecast?q=$city")
    }

    private fun makeRequest(query: String): NetworkResult {
        val url = URL(API_URL + query + PARAMS + BuildConfig.API_APP_ID)
        val connection = url.openConnection() as HttpURLConnection
        return try {
            connection.inputStream.bufferedReader()
                .use { NetworkResult.Success(data = it.readText()) }
        } catch (e: IOException) {
            NetworkResult.Error(e)
        } finally {
            connection.disconnect()
        }
    }

    companion object {
        private const val API_URL = "https://api.openweathermap.org/data/2.5/"
        private const val PARAMS = "&units=metric&lang=ru&APPID="
    }
}