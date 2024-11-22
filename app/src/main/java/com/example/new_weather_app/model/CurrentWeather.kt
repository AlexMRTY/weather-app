package com.example.new_weather_app.model

import java.time.LocalDateTime

data class CurrentWeather(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val humidity: Double,
    val apparentTemperatureCelsius: Double,
    val isDay: Boolean,
    val precipitation: Double,
    val weatherType: WeatherType,
    val windSpeed: Double,
    val windDirection: Int,
    val windGusts: Double
)