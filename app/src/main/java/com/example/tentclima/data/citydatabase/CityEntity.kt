package com.example.tentclima.data.citydatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visited_cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)
