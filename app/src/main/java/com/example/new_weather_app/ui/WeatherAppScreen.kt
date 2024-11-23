package com.example.new_weather_app.ui

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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp


@Composable
fun WeatherAppScreen(
    weatherState: WeatherState,
    modifier: Modifier = Modifier
) {

    Scaffold(
        bottomBar = {
            CoordinateInput(

            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            weatherState.weather?.currentWeather?.let { currentWeather ->
                WeatherCard(
                    data = currentWeather
                )
            }
            weatherState.weather?.weatherHourlyByDay?.let { hourlyWeatherList ->
                HourlyWeatherRow(
                    hourlyWeatherList = hourlyWeatherList
                )
            }
            weatherState.weather?.weatherDaily?.let { dailyWeatherList ->
                DailyWeather(
                    dailyWeatherList = dailyWeatherList
                )
            }
        }
    }
}

@Composable
fun CoordinateInput() {
    Row(
        modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 30.dp).wrapContentSize().fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Lat") },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(16.dp))
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Long") },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.wrapContentSize().size(70.dp).clip(RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "🔍",
                fontSize = 20.sp
            )
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

