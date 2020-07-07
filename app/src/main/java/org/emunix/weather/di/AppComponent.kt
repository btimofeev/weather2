package org.emunix.weather.di


import dagger.Component
import org.emunix.weather.ui.forecast.ForecastViewModel
import org.emunix.weather.ui.weather.CurrentWeatherViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class, NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(currentWeatherViewModel: CurrentWeatherViewModel)
    fun inject(forecastViewModel: ForecastViewModel)
}