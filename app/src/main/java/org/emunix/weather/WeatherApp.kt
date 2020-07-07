package org.emunix.weather

import android.app.Application
import org.emunix.weather.di.AppModule
import org.emunix.weather.di.DaggerAppComponent
import org.emunix.weather.di.NetworkModule
import org.emunix.weather.di.RoomModule


class WeatherApp: Application() {

    val appComponent = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .roomModule(RoomModule())
        .networkModule(NetworkModule())
        .build()
}