package org.emunix.weather.repository


import io.reactivex.Single
import org.emunix.weather.data.Forecast
import org.emunix.weather.data.db.CurrentWeather

interface WeatherRepository {

    fun getCurrentWeather(city: String): Single<CurrentWeather>

    fun getNextDaysForecast(city: String): Single<Forecast>
}