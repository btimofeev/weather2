package org.emunix.weather.di

import dagger.Module
import dagger.Provides
import org.emunix.weather.data.db.AppDatabase
import org.emunix.weather.network.NetworkResponseParser
import org.emunix.weather.network.NetworkWeatherAPI
import org.emunix.weather.repository.WeatherRepository
import org.emunix.weather.repository.WeatherRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule() {

    @Provides
    @Singleton
    fun provideRepository(
        db: AppDatabase,
        api: NetworkWeatherAPI,
        responseParser: NetworkResponseParser
    ): WeatherRepository {
        return WeatherRepositoryImpl(db, api, responseParser)
    }
}