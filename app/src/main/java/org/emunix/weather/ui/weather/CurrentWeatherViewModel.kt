package org.emunix.weather.ui.weather

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.emunix.weather.WeatherApp
import org.emunix.weather.data.db.CurrentWeather
import org.emunix.weather.repository.WeatherRepository
import javax.inject.Inject

class CurrentWeatherViewModel(app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var repository: WeatherRepository

    private val weather = MutableLiveData<CurrentWeather?>()
    fun getWeather(): LiveData<CurrentWeather?> = weather

    private val messageToUser = MutableLiveData<String>()
    fun getMessageToUser(): LiveData<String> = messageToUser

    private val showProgress = MutableLiveData<Boolean>()
    fun getShowProgress(): LiveData<Boolean> = showProgress

    init {
        (app as WeatherApp).appComponent.inject(this)
    }

    fun userSelectCity(city: String) {
        showProgress.value = true
        weather.value = null // hide old values from screen
        loadWeather(city)
    }

    @SuppressLint("CheckResult")
    private fun loadWeather(city: String) {
        repository.getCurrentWeather(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    weather.value = result
                    showProgress.value = false
                },
                { error ->
                    messageToUser.value = error.message
                    messageToUser.value = ""
                    showProgress.value = false
                }
            )
    }

}