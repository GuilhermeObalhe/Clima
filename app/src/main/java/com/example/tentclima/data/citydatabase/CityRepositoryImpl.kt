package com.example.tentclima.data.citydatabase

import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val dao: CityDao) : CityRepository{
    override suspend fun insertCity(city: CityEntity) {
        val existingCity = dao.getCityByName(city.name)
        if (existingCity == null) {
            dao.insertCity(city)
        }
    }

    override suspend fun getAllCities(): List<CityEntity> {
        return dao.getAllCities()
    }

    override suspend fun deleteCity(cityId: Int) {
        dao.deleteCity(cityId)
    }
}