package com.example.tentclima.data.di

import com.example.tentclima.data.repository.WeatherRepository
import com.example.tentclima.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository
}

/* Esse arquivo define um módulo dagger de injeção de dependência para o repositório WeatherRepository.
@Module indica que a classe é um módulo dagger, responsável por fornecer dependências
@InstallIn(ViewModelComponent::class) indica que o módulo será instalado no componente de injeção de dependência
@Binds define uma ligação entre a interface WeatherRepository e a implementação WeatherRepositoryImpl.
Isso permite que o Dagger Hilt saiba qual implementação usar quando a interface for injetada.

O Hilt usa esse módulo para injetar automaticamente instâncias de WeatherRepository onde forem necessárias
(por exemplo, em viewmodels), sem que você precise criar manualmente essas instâncias.
*/