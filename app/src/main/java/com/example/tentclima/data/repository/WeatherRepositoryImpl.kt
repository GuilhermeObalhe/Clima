package com.example.tentclima.data.repository

import android.annotation.SuppressLint
import com.example.tentclima.data.remote.RemoteDataSource
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
            condition = weather.main,
            temperature = response.main.temp.roundToInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            isDay = weather.icon.last() == 'd'
        )
    }
}