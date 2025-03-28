package com.example.tentclima.data.di


import com.example.tentclima.data.KtorRemoteDataSource
import com.example.tentclima.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindRemoteDataSource(remoteDataSource: KtorRemoteDataSource): RemoteDataSource
}

/*
@Module indica que essa classe é um módulo Dagger

@InstallIn(SingletonComponent::class) indica que esse módulo deve ser instalado
no SingletonComponent, que é o componente raiz da aplicação. Especifica que as
dependências fornecidas por esse módulo terão um ciclo de vida singleton, ou seja,
uma única instância será compartilhada em todo o aplicativo.

@Binds define uma ligação entre a interface RemoteDataSource e a sua
implementação KtorRemoteDataSource.

@Singleton garante que apenas uma instância de RemoteDataSource seja criada
e compartilhada em todo o aplicativo.


Organização do Código:
Esses módulos ajudam a separar as responsabilidades, mantendo o código mais organizado e fácil de manter.
Testabilidade:
Ao usar interfaces e injeção de dependência, você pode facilmente substituir implementações reais por mocks ou stubs durante os testes.
Reutilização de Código:
As dependências configuradas nesses módulos podem ser injetadas em qualquer parte do aplicativo, evitando duplicação de código.
Facilidade de Manutenção:
Se você precisar alterar uma implementação (por exemplo, trocar KtorRemoteDataSource por outra biblioteca), basta modificar o módulo correspondente, sem afetar o restante do código.
*/