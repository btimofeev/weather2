package org.emunix.weather.utils

import org.emunix.weather.data.Forecast
import org.emunix.weather.data.db.CurrentWeather
import org.emunix.weather.network.data.CurrentWeatherJsonData
import org.emunix.weather.network.data.ForecastJsonData


fun CurrentWeatherJsonData.toWeather(): CurrentWeather {
    return CurrentWeather(city, timestamp, main.temp, main.pressure, main.humidity,
        main.temp_min, main.temp_max, wind.speed, wind.deg)
}

fun ForecastJsonData.toForecast(): Forecast {
    val forecast = arrayListOf<Forecast.ForecastData>()
    for (item in this.list) {
        forecast.add(Forecast.ForecastData(item.dt_txt, item.main.temp, item.main.pressure, item.main.humidity, item.main.temp_min, item.main.temp_max, item.wind.speed, item.wind.deg))
    }
    return Forecast(city.name, forecast)
}
