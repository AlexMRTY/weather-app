package com.example.new_weather_app.vm

import com.example.new_weather_app.model.Weather

data class WeatherState(
    val weather: Weather? = null,
    val error: String? = "",
    val isLoading: Boolean = false
)
