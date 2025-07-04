package com.example.starwarsexplorer.data.remote.repository

import com.example.starwarsexplorer.data.mapper.StarWarsMapper
import com.example.starwarsexplorer.data.remote.api.StarWarsApiService
import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import com.example.starwarsexplorer.domain.util.Resource


class StarWarsRepositoryImpl(
    private val apiService: StarWarsApiService,
    private val mapper: StarWarsMapper
) : StarWarsRepository {

    override suspend fun getStarships(): Resource<List<Starship>> {
        return try {
            val response = apiService.getStarships()
            if (response.isSuccessful) {
                val domainList = response.body()?.map { mapper.mapStarship(it) } ?: emptyList()
                Resource.Success(domainList)
            } else {
                Resource.Error("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Exception: ${e.localizedMessage}", e)
        }
    }

    override suspend fun getFilms(): Resource<List<Film>> {
        return try {
            val response = apiService.getFilms()
            if (response.isSuccessful) {
                val domainList = response.body()?.map { mapper.mapFilm(it) } ?: emptyList()
                Resource.Success(domainList)
            } else {
                Resource.Error("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Exception: ${e.localizedMessage}", e)
        }
    }

    override suspend fun getVehicles(): Resource<List<Vehicle>> {
        return try {
            val response = apiService.getVehicles()
            if (response.isSuccessful) {
                val domainList =  response.body()?.map { mapper.mapVehicle(it) } ?: emptyList()
                Resource.Success(domainList)
            } else {
                Resource.Error("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Exception: ${e.localizedMessage}", e)
        }
    }
}
