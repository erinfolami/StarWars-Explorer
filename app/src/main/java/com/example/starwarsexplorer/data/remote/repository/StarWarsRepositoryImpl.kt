package com.example.starwarsexplorer.data.remote.repository

import com.example.starwarsexplorer.data.mapper.StarWarsMapper
import com.example.starwarsexplorer.data.remote.api.StarWarsApiService
import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val api: StarWarsApiService,
    private val mapper: StarWarsMapper
) : StarWarsRepository {

    override suspend fun getStarships(): List<Starship> {
        val response = api.getStarships()
        return response.body()?.map { mapper.mapStarship(it) } ?: emptyList()
    }

    override suspend fun getFilms(): List<Film> {
        val response = api.getFilms()
        return response.body()?.map { mapper.mapFilm(it) } ?: emptyList()
    }

    override suspend fun getVehicles(): List<Vehicle> {
        val response = api.getVehicles()
        return response.body()?.map { mapper.mapVehicle(it) } ?: emptyList()
    }
}

