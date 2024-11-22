package com.example.new_weather_app.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDTO (
    @SerializedName("hourly")
    var hourlyWeatherData: HourlyWeatherDataDTO,

    @SerializedName("current")
    var currentWeatherData: CurrentWeatherDataDTO,

    @SerializedName("daily")
    var dailyWeatherData: DailyWeatherDataDTO
)

