package com.example.new_weather_app.vm

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.new_weather_app.interfaces.IWeatherRepository
import com.example.new_weather_app.util.Resource
import com.example.new_weather_app.util.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherVm @Inject constructor(
    private val weatherRepository: IWeatherRepository,
    private val context: Context
) : ViewModel() {

    var weatherState by mutableStateOf(WeatherState())
        private set

    var isOnline by mutableStateOf(true)
        private set

    fun getWeatherData(lat: Float, long: Float) {
        viewModelScope.launch {
            weatherState = weatherState.copy(
                isLoading = true,
                error = null
            )

            if (!isInternetAvailable(context)) {
                weatherState = weatherState.copy(
                    isLoading = false,
                    error = "No internet connection"
                )
                isOnline = false
//                return@launch
            } else {
                isOnline = true
            }

            val response = weatherRepository.getWeatherData(lat, long)

            if (response is Resource.Success) {
                weatherState = weatherState.copy(
                    isLoading = false,
                    weather = response.data,
                    error = "No internet connection, showing cached data"
                )
            } else {
                weatherState = weatherState.copy(
                    isLoading = false,
                    error = response.message
                )
            }
//            weatherState = when (response) {
//                is Resource.Success -> WeatherState(weather = response.data)
//                is Resource.Error -> WeatherState(error = response.message)
//            }
        }
    }

    fun resetIsOnline() {
        isOnline = true
    }
}

