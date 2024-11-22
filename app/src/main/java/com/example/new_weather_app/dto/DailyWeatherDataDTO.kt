package com.example.new_weather_app.dto

import com.google.gson.annotations.SerializedName

data class DailyWeatherDataDTO(
    val time: List<String>,

    @SerializedName("weather_code")
    val weatherCodes: List<Int>,

    @SerializedName("temperature_2m_max")
    val maxTemperatures: List<Double>,

    @SerializedName("temperature_2m_min")
    val minTemperatures: List<Double>
)