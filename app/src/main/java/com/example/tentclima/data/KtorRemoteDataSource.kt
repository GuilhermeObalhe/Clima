package com.example.tentclima.data

import com.example.tentclima.data.remote.RemoteDataSource
import com.example.tentclima.data.remote.response.WeatherDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class KtorRemoteDataSource @Inject constructor(
    private val httpClient: HttpClient
) : RemoteDataSource {

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
    }

    override suspend fun getWeatherDataResponse(lat: Double, lng: Double): WeatherDataResponse {
        return httpClient
            .get("$BASE_URL/weather?lat=$lat&lon=$lng&appid=6d80b1ffd0c1b175173600e21afdd4d3&units=metric")
            .body()
    }
}