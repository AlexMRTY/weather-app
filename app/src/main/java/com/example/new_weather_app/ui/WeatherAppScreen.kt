package com.example.new_weather_app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.new_weather_app.vm.WeatherState

@Composable
fun WeatherAppScreen(
    weatherState: WeatherState?,
    modifier: Modifier = Modifier
) {
    if (
        weatherState == null ||
        weatherState.weather == null ||
        weatherState.weather.currentWeatherData == null
    ) {
        return
    }

    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        WeatherCard(
            temperature = weatherState.weather.currentWeatherData.temperatureCelsius,
            weatherType = weatherState.weather.currentWeatherData.weatherType,
            humidity = weatherState.weather.currentWeatherData.humidity,
            windSpeed = weatherState.weather.currentWeatherData.windSpeed,
            time = weatherState.weather.currentWeatherData.time
        )
        WeatherCardLazyRow(
            hourlyWeatherList = List(24) { index ->
                HourlyWeather(
                    temperature = 20.0 + index,
                    weatherCode = index % 100,
                    time = String.format("%02d:00", index)
                )
            }
        )
        Text(
            text = "Next 7 days",
            style = MaterialTheme.typography.headlineSmall
        )
        WeatherCardScroll(
            dailyWeatherList = List(7) { index ->
                DailyWeather(
                    temperature = 20.0 + index,
                    weatherCode = index % 100,
                    time = "Day ${index + 1}"
                )
            }
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = latitude,
                onValueChange = { newValue ->
                    if (newValue.toFloatOrNull() != null || newValue.isEmpty()) {
                        latitude = newValue
                    }
                },
                label = { Text("Latitude") },
                modifier = Modifier.fillMaxWidth().height(25.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = longitude,
                onValueChange = { newValue ->
                    if (newValue.toFloatOrNull() != null || newValue.isEmpty()) {
                        longitude = newValue
                    }
                },
                label = { Text("Longitude") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Handle submit action
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WeatherAppScreenPreview() {
    WeatherAppScreen(
        weatherState = WeatherState()
    )
}

