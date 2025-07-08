package com.example.starwarsexplorer.di

import com.example.starwarsexplorer.domain.repository.Repository
import com.example.starwarsexplorer.domain.usecase.GetFilmsUseCase
import com.example.starwarsexplorer.domain.usecase.GetStarshipsUseCase
import com.example.starwarsexplorer.domain.usecase.GetVehiclesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetFilmsUseCase(repository: Repository): GetFilmsUseCase {
        return GetFilmsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetStarshipsUseCase(repository: Repository): GetStarshipsUseCase {
        return GetStarshipsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetVehiclesUseCase(repository: Repository): GetVehiclesUseCase {
        return GetVehiclesUseCase(repository)
    }
}