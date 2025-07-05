package com.example.starwarsexplorer.data.remote.datasource


import com.example.starwarsexplorer.data.remote.api.StarWarsApiService
import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import retrofit2.Response
import javax.inject.Inject

class StarWarsRemoteDataSource @Inject constructor(
    private val apiService: StarWarsApiService
) {

    // Fetch starships from API
    suspend fun getStarships(): Response<List<StarshipDto>> =
        apiService.getStarships()

    // Fetch films from API
    suspend fun getFilms(): Response<List<FilmDto>> =
        apiService.getFilms()

    // Fetch vehicles from API
    suspend fun getVehicles(): Response<List<VehicleDto>> =
        apiService.getVehicles()
}