package com.example.new_weather_app.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.new_weather_app.interfaces.IWeatherRepository
import com.example.new_weather_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherVm @Inject constructor(
    private val weatherRepository: IWeatherRepository
) : ViewModel() {

//    // Secondary constructor for testing purposes
//    constructor() : this(weatherRepository = DummyWeatherRepository())

    var weatherState by mutableStateOf(WeatherState())
        private set

    fun getWeatherData(lat: Double, long: Double) {
        viewModelScope.launch {
            weatherState = weatherState.copy(
                isLoading = true,
                error = null
            )
            val response = weatherRepository.getWeatherData(lat, long)
            weatherState = when (response) {
                is Resource.Success -> WeatherState(weather = response.data)
                is Resource.Error -> WeatherState(error = response.message)
            }
        }
    }
}

