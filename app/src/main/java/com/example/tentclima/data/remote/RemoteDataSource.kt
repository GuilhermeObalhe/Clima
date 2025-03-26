package com.example.tentclima.data.remote

import com.example.tentclima.data.remote.response.CitySearchResult
import com.example.tentclima.data.remote.response.DailyDataResponse
import com.example.tentclima.data.remote.response.HourlyDataResponse
import com.example.tentclima.data.remote.response.WeatherDataResponse

interface RemoteDataSource {

    suspend fun getWeatherDataResponse(lat: Double, lng: Double): WeatherDataResponse

    // Previsão Horária
    suspend fun getHourlyWeatherDataResponse(lat: Double, lng: Double): HourlyDataResponse

    // Previsão Diária
    suspend fun getDailyWeatherDataResponse(lat: Double, lng: Double): DailyDataResponse

    // Buscar lista de cidades
    suspend fun searchCities(query: String): List<CitySearchResult>
}