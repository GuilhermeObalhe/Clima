package com.example.tentclima.data.citydatabase

interface CityRepository {
    suspend fun insertCity(city: CityEntity)
    suspend fun getAllCities(): List<CityEntity>
    suspend fun deleteCity(cityId: Int)
}