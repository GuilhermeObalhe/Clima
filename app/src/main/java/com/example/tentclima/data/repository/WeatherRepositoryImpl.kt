package com.example.tentclima.data.repository

import android.annotation.SuppressLint
import com.example.tentclima.data.remote.RemoteDataSource
import com.example.tentclima.data.remote.response.DailyData
import com.example.tentclima.data.remote.response.HourlyDataEntry
import com.example.tentclima.model.WeatherInfo
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt


class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : WeatherRepository {

    @SuppressLint("NewApi")
    override suspend fun getWeatherData(lat: Double, lng: Double): WeatherInfo {
        val response = remoteDataSource.getWeatherDataResponse(lat, lng)
        val weather = response.weather[0]

        return WeatherInfo(
            locationName = response.name,
            conditionIcon = weather.icon,
            condition = weather.description,
            temperature = response.main.temp.roundToInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            isDay = weather.icon.last() == 'd',
            humidity = response.main.humidity,
            pressure = response.main.pressure,
            feelsLike = response.main.feelsLike.roundToInt(),
            tempMin = response.main.tempMin.roundToInt(),
            tempMax = response.main.tempMax.roundToInt(),
            seaLevel = response.main.seaLevel,
            wind = response.wind,
            visibility = response.visibility,
            sunrise = response.sys.sunrise,
            sunset = response.sys.sunset,
            hourlyData = getHourlyWeatherData(lat, lng),
            dailyData = getDailyWeatherData(lat, lng),
            clouds = response.clouds.all,
            airQualityData = getAirQualityData(lat, lng)
        )
    }

    // Previsão horária
    override suspend fun getHourlyWeatherData(lat: Double, lng: Double): List<HourlyDataEntry> {
        val response = remoteDataSource.getHourlyWeatherDataResponse(lat, lng)
        return response.list
    }

    // Previsão diária
    override suspend fun getDailyWeatherData(lat: Double, lng: Double): List<DailyData> {
        val response = remoteDataSource.getDailyWeatherDataResponse(lat, lng)
        return response.list
    }

    // Busca lista de cidades
    override suspend fun searchCities(query: String) =
        remoteDataSource.searchCities(query)

    // Busca dados da qualidade do ar
    override suspend fun getAirQualityData(lat: Double, lng: Double) =
        remoteDataSource.getAirQualityDataResponse(lat, lng)
}