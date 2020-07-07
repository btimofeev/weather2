package org.emunix.weather.network

import com.squareup.moshi.Moshi
import org.emunix.weather.network.data.CurrentWeatherJsonData
import org.emunix.weather.network.data.ForecastJsonData
import java.io.IOException

class OpenWeatherMapResponseParser(private val moshi: Moshi) : NetworkResponseParser {
    override fun parseCurrentWeather(networkResponse: String): CurrentWeatherJsonData {
        val jsonAdapter = moshi.adapter(CurrentWeatherJsonData::class.java)
        return jsonAdapter.fromJson(networkResponse) ?: throw IOException("failed to parse json")
    }

    override fun parseForecast(networkResponse: String): ForecastJsonData {
        val jsonAdapter = moshi.adapter(ForecastJsonData::class.java)
        return jsonAdapter.fromJson(networkResponse) ?: throw IOException("failed to parse json")
    }
}