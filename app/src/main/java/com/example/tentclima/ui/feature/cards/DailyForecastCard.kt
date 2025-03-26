package com.example.tentclima.ui.feature.cards

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tentclima.data.remote.response.DailyData
import com.example.tentclima.data.remote.response.FeelsLike
import com.example.tentclima.data.remote.response.Temp
import com.example.tentclima.data.remote.response.Weather
import com.example.tentclima.ui.theme.TentclimaTheme
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun DailyForecastCard(dailyData: List<DailyData>) {
    val weekdays = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Dom")
    val today = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val todayIndex = today.dayOfWeek.value - 1 // 0 para segunda, 1 para terça, etc.
    val startingIndex = (todayIndex + 1) % 7 // Índice do primeiro dia (amanhã)
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        itemsIndexed(dailyData){ index, daily ->
            val dayName = weekdays[(startingIndex + index) % 7]
            DailyForecastItem(dailyData = daily, dayName = dayName)
        }
    }
}

@Composable
fun DailyForecastItem(dailyData: DailyData, dayName: String) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ){
        Text(
            modifier = Modifier.width(55.dp).size(55.dp),
            text = dayName,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        val context = LocalContext.current
        val iconResId = context.resources.getIdentifier(
            "weather_${dailyData.weather.first().icon}",
            "drawable",
            context.packageName
        )

        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(55.dp)
        )

        Text(
            modifier = Modifier.width(55.dp).size(55.dp),
            text = "${dailyData.temp.min.roundToInt()}°C",
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.width(5.dp).size(55.dp),
            text = "-",
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.width(55.dp).size(55.dp),
            text = "${dailyData.temp.max.roundToInt()}°C",
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun DailyForecastCardPreview() {
    TentclimaTheme {
        DailyForecastCard(
            dailyData = fakeDailyData
        )
    }
}

val fakeDailyData = List(7) { index ->
    DailyData(
        dt = System.currentTimeMillis() / 1000 + (index * 86400), // Timestamp futuro
        sunrise = 0,
        sunset = 0,
        temp = Temp(
            day = 25.0 + index * 2.0, // Temperaturas variadas para visualização
            min = 20.0,
            max = 30.0,
            night = 22.0,
            eve = 24.0,
            morn = 18.0
        ),
        feelsLike = FeelsLike(
            day = 25.0,
            night = 20.0,
            eve = 23.0,
            morn = 19.0
        ),
        pressure = 1013,
        humidity = 60,
        weather = listOf(
            Weather(
                id = 800,
                main = "Clear",
                description = "Clear sky",
                icon = "01d" // Ícone válido para o preview
            )
        ),
        speed = 5.0,
        deg = 0,
        gust = 7.0,
        clouds = 20,
        pop = 0.1,
        rain = 0.0
    )
}
