package com.example.starwarsexplorer.di

import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
import com.example.starwarsexplorer.data.remote.api.StarWarsApiService
import com.example.starwarsexplorer.data.repository.StarWarsRepositoryImpl
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideStarWarsRepository(
        apiService: StarWarsApiService,
        mapper: RemoteMapper
    ): StarWarsRepository {
        return StarWarsRepositoryImpl(apiService, mapper)
    }
}