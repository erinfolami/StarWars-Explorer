package com.example.starwarsexplorer.domain.repository

import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle

interface StarWarsRepository {
    suspend fun getStarships(): List<Starship>
    suspend fun getFilms(): List<Film>
    suspend fun getVehicles(): List<Vehicle>
}