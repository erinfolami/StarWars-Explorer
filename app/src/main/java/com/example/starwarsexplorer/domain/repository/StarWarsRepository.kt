package com.example.starwarsexplorer.domain.repository

import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.util.Resource

interface StarWarsRepository {
    suspend fun getStarships(): Resource<List<Starship>>
    suspend fun getFilms(): Resource<List<Film>>
    suspend fun getVehicles(): Resource<List<Vehicle>>
}