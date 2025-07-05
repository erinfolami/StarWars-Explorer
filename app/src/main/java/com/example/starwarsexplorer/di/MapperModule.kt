package com.example.starwarsexplorer.di

import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
import dagger.Provides
import javax.inject.Singleton

@Provides
@Singleton
fun provideStarWarsMapper(): RemoteMapper {
    return RemoteMapper()
}