package com.example.starwarsexplorer.data.remote.api

import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("starships")
    suspend fun getStarships(): Response<List<StarshipDto>>

    @GET("films")
    suspend fun getFilms(): Response<List<FilmDto>>

    @GET("vehicles")
    suspend fun getVehicles(): Response<List<VehicleDto>>
}