package com.example.tentclima.data.citydatabase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCityRepository(impl: CityRepositoryImpl): CityRepository
}

/*
@Module indica que essa classe é um módulo Dagger, responsável por fornecer dependências

@InstallIn(SingletonComponent::class) indica que esse módulo deve ser instalado
no SingletonComponent, que é o componente raiz da aplicação. Isso significa que as
dependências fornecidas por esse módulo terão um ciclo de vida singleton, ou seja,
uma única instância será compartilhada em todo o aplicativo.

@Binds: Define uma ligação entre a interface CityRepository e sua implementação
CityRepositoryImpl. Isso permite que o Dagger Hilt saiba qual implementação usar quando
a interface for injetada.

Esse módulo é muito útil pois estamos fazendo chamadas a API para obter dados de cidades.
*/