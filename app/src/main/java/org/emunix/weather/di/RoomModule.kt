package org.emunix.weather.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.emunix.weather.data.db.AppDatabase
import org.emunix.weather.data.db.CurrentWeatherDAO
import javax.inject.Singleton

@Module
class RoomModule() {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "weather.db").build()

    @Provides
    @Singleton
    fun provideCurrentWeatherDAO(db: AppDatabase): CurrentWeatherDAO = db.currentWeatherDAO()
}