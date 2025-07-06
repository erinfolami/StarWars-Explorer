package com.example.starwarsexplorer.data.remote.datasource


import com.example.starwarsexplorer.data.remote.api.ApiService
import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
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