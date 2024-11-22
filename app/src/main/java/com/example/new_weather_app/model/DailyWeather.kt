package com.example.new_weather_app.model

import java.time.LocalDate
import java.time.LocalDateTime

data class DailyWeather(
    val time: LocalDate,
    val maxTemperatureCelsius: Double,
    val minTemperatureCelsius: Double,
    val weatherType: WeatherType
)