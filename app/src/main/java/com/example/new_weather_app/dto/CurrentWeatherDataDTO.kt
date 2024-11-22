package com.example.new_weather_app.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDataDTO(
    val time: String,

    @SerializedName("temperature_2m")
    val temperature: Double,

    @SerializedName("relative_humidity_2m")
    val humidity: Double,

    @SerializedName("apparent_temperature")
    val apparentTemperature: Double,

    @SerializedName("is_day")
    val isDay: Int,

    val precipitation: Double,

    @SerializedName("weather_code")
    val weatherCode: Int,

    @SerializedName("wind_speed_10m")
    val windSpeed: Double,

    @SerializedName("wind_direction_10m")
    val windDirection: Int,

    @SerializedName("wind_gusts_10m")
    val windGusts: Double
)