package com.example.tentclima.utils

fun airQualityWord(aqi: Int) : String{
    return when (aqi) {
        1 -> "Muito Bom"
        2 -> "Bom"
        3 -> "Médio"
        4 -> "Ruim"
        5 -> "Muito Ruim"
        else -> "Desconhecido"
    }
}