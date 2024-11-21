package com.example.new_weather_app.data.api

import com.example.new_weather_app.dto.HourlyWeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
//        @Query("hourly") hourly: String = "temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m"
    ): HourlyWeatherDTO
}