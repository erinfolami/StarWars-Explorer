package com.example.starwarsexplorer.di

import com.example.starwarsexplorer.data.local.mapper.LocalMapper
import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideRemoteMapper(): RemoteMapper {
        return RemoteMapper()
    }

    @Provides
    @Singleton
    fun provideLocalMapper(): LocalMapper {
        return LocalMapper()
    }
}