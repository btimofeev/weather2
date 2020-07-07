package org.emunix.weather.ui.forecast

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.emunix.weather.WeatherApp
import org.emunix.weather.data.Forecast
import org.emunix.weather.repository.WeatherRepository
import javax.inject.Inject

class ForecastViewModel(app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var repository: WeatherRepository

    private val forecast = MutableLiveData<Forecast?>()
    fun getForecast(): LiveData<Forecast?> = forecast

    private val messageToUser = MutableLiveData<String>()
    fun getMessageToUser(): LiveData<String> = messageToUser

    private val showProgress = MutableLiveData<Boolean>()
    fun getShowProgress(): LiveData<Boolean> = showProgress

    init {
        (app as WeatherApp).appComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    fun loadForecast(city: String) {
        showProgress.value = true
        forecast.value = null
        repository.getNextDaysForecast(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                forecast.value = result
                showProgress.value = false
                },
                { error ->
                    messageToUser.value = error.message
                    messageToUser.value = ""
                    showProgress.value = false
                })
    }
}