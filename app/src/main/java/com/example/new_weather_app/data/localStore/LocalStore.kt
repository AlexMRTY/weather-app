package com.example.new_weather_app.data.localStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.new_weather_app.data.repository.WeatherRepository
import com.example.new_weather_app.dto.WeatherResponseDTO
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val key = stringPreferencesKey("weather_data")

    suspend fun saveData(value: WeatherRepository.WeatherDataStore) {
        val jasonData = Gson().toJson(value)
        dataStore.edit {
            it[key] = jasonData
        }
    }

    suspend fun getData(): WeatherRepository.WeatherDataStore? {
        val preferences = dataStore.data.first()
        val jasonData = preferences[key]
        return Gson().fromJson(jasonData, WeatherRepository.WeatherDataStore::class.java)
    }
}