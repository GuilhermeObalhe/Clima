package com.example.tentclima.data.repository

import com.example.tentclima.model.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, lng: Double): WeatherInfo
}