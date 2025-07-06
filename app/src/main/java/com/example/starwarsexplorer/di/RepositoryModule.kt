package com.example.starwarsexplorer.di

import com.example.starwarsexplorer.data.local.datasource.LocalDataSource
import com.example.starwarsexplorer.data.local.mapper.LocalMapper
import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
import com.example.starwarsexplorer.data.remote.datasource.RemoteDataSource
import com.example.starwarsexplorer.data.repository.RepositoryImpl
import com.example.starwarsexplorer.domain.repository.Repository
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
        remote: RemoteDataSource,
        local: LocalDataSource,
        remoteMapper: RemoteMapper,
        localMapper: LocalMapper
    ): Repository {
        return RepositoryImpl(remote, local, remoteMapper, localMapper)
    }
}