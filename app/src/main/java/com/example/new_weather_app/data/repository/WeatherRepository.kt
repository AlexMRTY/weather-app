package com.example.new_weather_app.data.repository

import android.util.Log
import com.example.new_weather_app.data.api.WeatherApi
import com.example.new_weather_app.data.localStore.LocalStore
import com.example.new_weather_app.dto.WeatherResponseDTO
import com.example.new_weather_app.interfaces.IWeatherRepository
import com.example.new_weather_app.mapper.toWeather
import com.example.new_weather_app.model.Weather
import com.example.new_weather_app.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Time
import java.time.Duration
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject
import kotlin.time.TimeSource

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val localStore: LocalStore
) : IWeatherRepository {
    private val timeSource = TimeSource.Monotonic

    override suspend fun getWeatherData(lat: Float, long: Float): Resource<Weather> {
        return withContext(Dispatchers.IO) {
            try {
                val currentTime = timeSource.markNow()
                val cachedData = localStore.getData()
                if (cachedData != null) {
                    try {
                        val dateCreatedTime = LocalDateTime.parse(cachedData.createdTime)

                        if (
                            cachedData.lat == lat &&
                            cachedData.long == long &&
                            Duration.between(dateCreatedTime, LocalDateTime.now()).toMinutes() < 60
                        ) {
                            Log.d("WeatherRepository", "Time taken to get data from cache: ${currentTime.elapsedNow()}")
                            return@withContext Resource.Success(cachedData.weatherData.toWeather())
                        }
                    } catch (e: Exception) {
                        Log.e("WeatherRepository", "Error parsing createdTime", e)
                    }
                }

                Log.d("WeatherRepository", "Fetching data from API")
                val response = weatherApi.getWeatherData(lat, long)
                val weatherDataStore = WeatherDataStore(
                    response,
                    LocalDateTime.now().toString(),
                    lat,
                    long
                )
                localStore.saveData(weatherDataStore)
                Log.d("WeatherRepository", "Time taken to get data from API: ${currentTime.elapsedNow()}")
                Resource.Success(response.toWeather())
            } catch (e: Exception) {
                Log.e("WeatherRepository", "Error fetching weather data", e)
                Resource.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    data class WeatherDataStore(
        val weatherData: WeatherResponseDTO,
        val createdTime: String,
        val lat: Float,
        val long: Float
    )


//    override suspend fun getWeatherData(lat: Float, long: Float): Resource<Weather> {
//        return withContext(Dispatchers.IO) {
//            try {
//                val response = weatherApi.getWeatherData(lat, long)
//                Resource.Success(response.toWeather())
//            } catch (e: Exception) {
//                Resource.Error(e.message ?: "An unknown error occurred")
//            }
//        }
//    }
}