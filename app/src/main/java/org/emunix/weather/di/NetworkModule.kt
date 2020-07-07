package org.emunix.weather.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import org.emunix.weather.network.NetworkResponseParser
import org.emunix.weather.network.NetworkWeatherAPI
import org.emunix.weather.network.OpenWeatherMapAPI
import org.emunix.weather.network.OpenWeatherMapResponseParser
import javax.inject.Singleton

@Module
class NetworkModule() {

    @Provides
    @Singleton
    fun provideAPI(): NetworkWeatherAPI = OpenWeatherMapAPI()

    @Provides
    @Singleton
    fun provideResponseParser(moshi: Moshi): NetworkResponseParser = OpenWeatherMapResponseParser(moshi)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}