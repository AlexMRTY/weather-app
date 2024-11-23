package com.example.new_weather_app.interfaces

import com.example.new_weather_app.model.Weather
import com.example.new_weather_app.util.Resource

interface IWeatherRepository {
    suspend fun getWeatherData(lat: Float, long: Float): Resource<Weather>
}