package com.example.tentclima.ui.feature

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tentclima.model.WeatherInfo
import com.example.tentclima.ui.theme.BlueSky
import com.example.tentclima.ui.theme.TentclimaTheme

@Composable
fun WeatherRoute(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherInfoState by viewModel.weatherInfoState.collectAsStateWithLifecycle()
    WeatherScreen(weatherInfo = weatherInfoState.weatherInfo)
}

@SuppressLint("DiscouragedApi")
@Composable
fun WeatherScreen(
    context: Context = LocalContext.current,
    weatherInfo: WeatherInfo?
) {
    weatherInfo?.let {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if (weatherInfo.isDay) {
                BlueSky
            } else Color.DarkGray
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = weatherInfo.locationName,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = weatherInfo.dayOfWeek,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(32.dp))

                val iconDrawableResId: Int = context.resources.getIdentifier(
                    "weather_${weatherInfo.conditionIcon}",
                    "drawable",
                    context.packageName
                )

                Image(
                    painter = painterResource(id = iconDrawableResId),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = weatherInfo.condition,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "${weatherInfo.temperature}°",
                    color = Color.White,
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = false)
@Composable
fun WeatherScreenPreview() {
    val fakeContext = LocalContext.current
    TentclimaTheme(darkTheme = false, dynamicColor = false){
        WeatherScreen(
            weatherInfo = WeatherInfo(
                locationName = "Belo Horizonte",
                conditionIcon = "01d",
                condition = "Cloudy",
                temperature = 32,
                dayOfWeek = "Saturday",
                isDay = true,
            ),
            context = fakeContext
        )
    }
}