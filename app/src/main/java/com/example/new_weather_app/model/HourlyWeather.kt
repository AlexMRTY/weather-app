package com.example.new_weather_app.model

import java.time.LocalDateTime

data class HourlyWeather(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
