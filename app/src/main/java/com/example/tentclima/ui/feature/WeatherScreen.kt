package com.example.tentclima.ui.feature

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tentclima.R
import com.example.tentclima.data.remote.response.Clouds
import com.example.tentclima.data.remote.response.DailyData
import com.example.tentclima.data.remote.response.HourlyDataEntry
import com.example.tentclima.data.remote.response.MainData
import com.example.tentclima.data.remote.response.RainData
import com.example.tentclima.data.remote.response.SysData
import com.example.tentclima.data.remote.response.Weather
import com.example.tentclima.data.remote.response.Wind
import com.example.tentclima.model.WeatherInfo
import com.example.tentclima.ui.feature.cards.DailyForecastCard
import com.example.tentclima.ui.feature.cards.HourlyForecastCard
import com.example.tentclima.ui.feature.cards.InformationsSection
import com.example.tentclima.ui.feature.cards.fakeDailyData
import com.example.tentclima.ui.theme.BlueSky
import com.example.tentclima.ui.theme.TentclimaTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun WeatherRoute(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherInfoState by viewModel.weatherInfoState.collectAsStateWithLifecycle()
    WeatherScreen(weatherInfo = weatherInfoState.weatherInfo)
}

private fun filterDailyData(dailyData: List<DailyData>): List<DailyData> {
    val today = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    return dailyData.filter { daily ->
        // Converte o timestamp para LocalDate
        val date = Instant.ofEpochSecond(daily.dt)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        // Verifica se a data é posterior ao dia atual
        date.isAfter(today) || date == today.plusDays(1)
    }
}

// Função para escolher a imagem com base no dia/noite
@Composable
private fun getBackgroundResource(isDay: Boolean): Int {
    return if (isDay) {
        R.drawable.background_01d // Imagem para dia
    } else {
        R.drawable.background_01n // Imagem para noite
    }
}


@SuppressLint("DiscouragedApi", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherScreen(
    context: Context = LocalContext.current,
    weatherInfo: WeatherInfo?
) {

    if (weatherInfo != null) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 24.dp, top = 16.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {/* TODO */ })
                    {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    IconButton(onClick = {/* TODO */ })
                    {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            },
            containerColor = Color.Transparent //if (weatherInfo.isDay) BlueSky else Color.DarkGray
        )
        {
            Box(modifier = Modifier.fillMaxSize()) { // 📌 Adicione um Box como container
                Image( // 📌 Componente correto para imagens de fundo
                    painter = painterResource(id = getBackgroundResource(weatherInfo.isDay)),
                    contentDescription = "Background",
                    modifier = Modifier.fillMaxSize(), // 📌 Preenche toda a área
                    contentScale = ContentScale.Crop // 📌 Ajuste conforme necessário
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = weatherInfo.locationName,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = weatherInfo.dayOfWeek,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val iconDrawableResId: Int = context.resources.getIdentifier(
                        "weather_${weatherInfo.conditionIcon}",
                        "drawable",
                        context.packageName
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(64.dp).padding(end = 8.dp),
                            painter = painterResource(id = iconDrawableResId),
                            contentDescription = null,
                        )

                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "${weatherInfo.temperature}°C",
                            color = Color.White,
                            style = MaterialTheme.typography.displayLarge
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = weatherInfo.condition,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.padding(bottom = 16.dp))
                    {
                        Text(
                            modifier = Modifier.padding(end = 16.dp),
                            text = "Min: ${weatherInfo.dailyData.first().temp.min.roundToInt()}°C",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = "Máx: ${weatherInfo.dailyData.first().temp.max.roundToInt()}°C",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Surface(
                        modifier = Modifier.fillMaxWidth(0.85f).height(110.dp),
                        color = Color.Gray.copy(alpha=0.3f),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(2.dp, Color.Black.copy(alpha = 0.5f)),
                    ) {
                        // Previsão horária
                        HourlyForecastCard(hourlyData = weatherInfo.hourlyData)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Previsão semanal",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )

                    Surface(
                        modifier = Modifier.fillMaxWidth(0.6f).height(220.dp),
                        color = Color.Gray.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(2.dp, Color.Black.copy(alpha = 0.5f)),
                    ) {
                        // Previsão Diária
                        DailyForecastCard(dailyData = filterDailyData(weatherInfo.dailyData))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        modifier = Modifier.height(160.dp)
                    ) {
                        item {
                            InformationsSection(
                                weatherInfo = weatherInfo,
                                modifier = Modifier.padding(vertical = 32.dp)
                            )
                        }
                    }
                }
            }
        }

    }
}

/* Início - Chat-GPT
Prompt: Escreva uma função em kotlin que converta o formato timestamp UNIX em horas e minutos.
*/
fun Long.toFormattedTime(): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault()) // Formato de 24h (Ex: 06:30)
    return sdf.format(Date(this * 1000)) // Multiplica por 1000 para converter segundos para milissegundos
}
/*Fim - Chat-GPT
*/
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
                humidity = 60,
                pressure = 1013,
                feelsLike = 30,
                tempMin = 28,
                tempMax = 34,
                seaLevel = 1000,
                wind = Wind(speed = 10.0, deg = 0, gust = 5.0),
                visibility = 10000,
                sunrise = 321421,
                sunset = 4214213,
                hourlyData = fakeHourlyData,
                dailyData = fakeDailyData
            ),
            context = fakeContext
        )
    }
}

// Fake List
val fakeHourlyData =
    listOf(
    HourlyDataEntry(
        dt = 1680000000,
        mainData = MainData(
            temp = 30.0,
            feelsLike = 28.0,
            tempMin = 28.0,
            tempMax = 32.0,
            pressure = 1013,
            seaLevel = 1000,
            grndLevel = 950,
            humidity = 60,
            tempKf = 4.0
        ),
        weather = listOf(
            Weather(
                id = 800,
                main = "Clear",
                description = "Clear sky",
                icon = "01d"
            )
        ),
        cloudsData = Clouds(all = 0),
        windData = Wind(speed = 10.0, deg = 0, gust = 5.0),
        rain = RainData(oneHour = 0.0),
        sys = SysData(pod = "d"),
        dtTxt = "2023-04-01 12:00:00",
        pop = 0.0,
        visibility = 10000,

    )
)