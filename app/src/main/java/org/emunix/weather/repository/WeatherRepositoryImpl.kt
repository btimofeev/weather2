package org.emunix.weather.repository

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.emunix.weather.data.Forecast
import org.emunix.weather.data.db.AppDatabase
import org.emunix.weather.data.db.CurrentWeather
import org.emunix.weather.network.NetworkResponseParser
import org.emunix.weather.network.NetworkResult
import org.emunix.weather.network.NetworkWeatherAPI
import org.emunix.weather.utils.toForecast
import org.emunix.weather.utils.toWeather
import java.io.IOException


class WeatherRepositoryImpl(private val db: AppDatabase,
                            private val api: NetworkWeatherAPI,
                            private val responseParser: NetworkResponseParser) : WeatherRepository {

    override fun getCurrentWeather(city: String): Single<CurrentWeather> {
        return getCurrentWeatherFromAPI(city)
    }

    override fun getNextDaysForecast(city: String): Single<Forecast> {
        return getForecastFromAPI(city)
    }

    private fun getCurrentWeatherFromAPI(city: String): Single<CurrentWeather> {
        return Single.create { emitter ->
            when (val response = api.getCurrentWeather(city)){
                is NetworkResult.Success -> {
                    try {
                        val weather = responseParser.parseCurrentWeather(response.data).toWeather()
                        writeToDB(weather)
                        emitter.onSuccess(weather)
                    } catch (e: IOException) {
                        emitter.onError(e)
                    }
                }
                is NetworkResult.Error -> {
                    getCurrentWeatherFromDB(city)
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            emitter.onSuccess(it)
                        }, {
                            emitter.onError((it))
                        }, {
                            emitter.onError(Exception("Интернета нет, в базе не найдено"))
                        })
                }
            }
        }
    }

    private fun getCurrentWeatherFromDB(city: String): Maybe<CurrentWeather> {
        return db.currentWeatherDAO().getWeather(city)
    }

    private fun writeToDB(weather: CurrentWeather) {
        Completable.fromAction { db.currentWeatherDAO().insert(weather) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun getForecastFromAPI(city: String): Single<Forecast> {
        return Single.create { emitter ->
            when (val response = api.getNextDaysForecast(city)) {
                is NetworkResult.Success -> {
                    try {
                        val forecast = responseParser.parseForecast(response.data).toForecast()
                        //writeToDB(forecast)
                        emitter.onSuccess(forecast)
                    } catch (e: IOException) {
                        emitter.onError(e)
                    }
                }
                is NetworkResult.Error -> {
                    emitter.onError(response.exception)
                }
            }
        }
    }
}