package com.example.starwarsexplorer.di

import com.example.starwarsexplorer.data.mapper.StarWarsMapper
import dagger.Provides
import javax.inject.Singleton

@Provides
@Singleton
fun provideStarWarsMapper(): StarWarsMapper {
    return StarWarsMapper()
}