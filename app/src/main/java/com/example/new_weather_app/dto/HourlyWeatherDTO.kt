package com.example.new_weather_app.dto

import com.google.gson.annotations.SerializedName

data class HourlyWeatherDTO (
    @SerializedName("hourly")
    var hourlyWeatherData: WeatherDataDTO
)