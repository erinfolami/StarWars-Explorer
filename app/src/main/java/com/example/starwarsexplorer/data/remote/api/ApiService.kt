package com.example.starwarsexplorer.data.remote.api

import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.FilmResponse
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.StarshipResponse
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import com.example.starwarsexplorer.data.remote.model.VehicleResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("starships")
    suspend fun getStarships(): Response<StarshipResponse>

    @GET("films")
    suspend fun getFilms(): Response<FilmResponse>

    @GET("vehicles")
    suspend fun getVehicles(): Response<VehicleResponse>
}