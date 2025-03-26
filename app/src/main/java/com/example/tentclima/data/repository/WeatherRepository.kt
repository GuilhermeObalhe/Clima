package com.example.tentclima.data.repository


import com.example.tentclima.data.remote.response.CitySearchResult
import com.example.tentclima.data.remote.response.DailyData
import com.example.tentclima.data.remote.response.HourlyDataEntry
import com.example.tentclima.model.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, lng: Double): WeatherInfo

    // Previsão horária
    suspend fun getHourlyWeatherData(lat: Double, lng: Double): List<HourlyDataEntry>

    // Previsão diária
    suspend fun getDailyWeatherData(lat: Double, lng: Double): List<DailyData>

    // Busca lista de cidades
    suspend fun searchCities(query: String): List<CitySearchResult>

}