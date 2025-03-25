package com.example.tentclima.ui.feature.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tentclima.model.WeatherInfo
import com.example.tentclima.ui.feature.toFormattedTime

@Composable
fun InformationsSection(weatherInfo: WeatherInfo, modifier: Modifier = Modifier) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Sensação térmica: ${weatherInfo.feelsLike}°C",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Umidade: ${weatherInfo.humidity}%",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Pressão: ${weatherInfo.pressure}mbar",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Altitude do mar: ${weatherInfo.seaLevel}m",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Vento: ${weatherInfo.wind.speed}m/s ${weatherInfo.wind.deg}° ${weatherInfo.wind.gust}m/s",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Visibilidade: ${weatherInfo.visibility / 1000}km",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Nascer do sol: ${weatherInfo.sunrise.toFormattedTime()}",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Pôr do sol: ${weatherInfo.sunset.toFormattedTime()}",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Probabilidade de chuva: ${weatherInfo.dailyData.first().pop*100}%",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = "Precipitação: ${weatherInfo.dailyData.first().rain}",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}