package com.example.new_weather_app.data.repository

import com.example.new_weather_app.data.api.WeatherApi
import com.example.new_weather_app.interfaces.IWeatherRepository
import com.example.new_weather_app.mapper.toWeather
import com.example.new_weather_app.model.Weather
import com.example.new_weather_app.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
) : IWeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<Weather> {
        return withContext(Dispatchers.IO) {
            try {
                val response = weatherApi.getWeatherData(lat, long)
                Resource.Success(response.toWeather())
            } catch (e: Exception) {
                Resource.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}