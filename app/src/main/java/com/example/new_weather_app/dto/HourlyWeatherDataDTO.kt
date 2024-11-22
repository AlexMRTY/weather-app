package com.example.new_weather_app.dto

import com.google.gson.annotations.SerializedName


data class HourlyWeatherDataDTO(
    val time: List<String>,

    @SerializedName("temperature_2m")
    val temperatures: List<Double>,

    @SerializedName("weather_code")
    val weatherCodes: List<Int>,

    @SerializedName("wind_speed_10m")
    val windSpeeds: List<Double>,

    @SerializedName("relative_humidity_2m")
    val humidities: List<Double>
)