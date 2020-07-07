package org.emunix.weather.network

sealed class NetworkResult {
    data class Success(val data: String)
        : NetworkResult()
    data class Error(val exception: Exception)
        : NetworkResult()
}