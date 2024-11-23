package com.example.new_weather_app.data.api

import com.example.new_weather_app.dto.WeatherResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m,wind_direction_10m,wind_gusts_10m&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m&daily=weather_code,temperature_2m_max,temperature_2m_min")
    suspend fun getWeatherData(
        @Query("latitude") lat: Float,
        @Query("longitude") long: Float
    ): WeatherResponseDTO
}