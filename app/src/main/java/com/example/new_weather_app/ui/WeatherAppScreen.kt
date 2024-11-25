package com.example.new_weather_app.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.new_weather_app.vm.WeatherState
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.new_weather_app.ui.theme.CoordinateInput
import com.example.new_weather_app.vm.WeatherVm
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices



@Composable
fun WeatherAppScreen(
    weatherVm: WeatherVm,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(weatherVm.weatherState.error) {
        if (weatherVm.isOnline.not()) {
            weatherVm.weatherState.error?.let { error ->
                snackbarHostState.showSnackbar(error)
            }
            weatherVm.resetIsOnline()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            CoordinateInput(
                onSearch = { lat, long ->
                    weatherVm.getWeatherData(lat, long)
                }
            )
        }
    ) { innerPadding ->
        if (isPortrait) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                weatherVm.weatherState.weather?.currentWeather?.let { currentWeather ->
                    WeatherCard(
                        data = currentWeather
                    )
                }
                weatherVm.weatherState.weather?.weatherHourlyByDay?.let { hourlyWeatherList ->
                    HourlyWeatherRow(
                        hourlyWeatherList = hourlyWeatherList
                    )
                }
                weatherVm.weatherState.weather?.weatherDaily?.let { dailyWeatherList ->
                    DailyWeather(
                        dailyWeatherList = dailyWeatherList
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    weatherVm.weatherState.weather?.currentWeather?.let { currentWeather ->
                        WeatherCard(
                            data = currentWeather
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    weatherVm.weatherState.weather?.weatherHourlyByDay?.let { hourlyWeatherList ->
                        HourlyWeatherRow(
                            hourlyWeatherList = hourlyWeatherList
                        )
                    }
                    weatherVm.weatherState.weather?.weatherDaily?.let { dailyWeatherList ->
                        DailyWeather(
                            dailyWeatherList = dailyWeatherList
                        )
                    }
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun WeatherAppScreenPreview() {
//    WeatherAppScreen(
//        weatherState = WeatherState()
//    )
//}

