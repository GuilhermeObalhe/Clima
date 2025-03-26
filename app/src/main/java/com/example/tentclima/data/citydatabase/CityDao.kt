package com.example.tentclima.data.citydatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Insert
    suspend fun insertCity(city: CityEntity)

    @Query("SELECT * FROM visited_cities WHERE name = :name LIMIT 1")
    suspend fun getCityByName(name: String): CityEntity?

    @Query("SELECT * FROM visited_cities")
    suspend fun getAllCities(): List<CityEntity>

    @Query("DELETE FROM visited_cities WHERE id = :cityId")
    suspend fun deleteCity(cityId: Int)
}