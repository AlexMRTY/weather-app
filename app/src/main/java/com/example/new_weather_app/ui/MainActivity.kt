package com.example.new_weather_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.new_weather_app.ui.theme.NewweatherappTheme
import com.example.new_weather_app.vm.WeatherVm
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm: WeatherVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        vm.getWeatherData(37.7749, -122.4194)

        enableEdgeToEdge()
        setContent {
            NewweatherappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherAppScreen(
                        weatherVm = vm,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun WeatherAppScreenPreview() {
//    WeatherAppTheme {
//        WeatherAppScreen(
//            weatherState = null
//
//        )
//    }
//}