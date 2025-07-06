package com.example.starwarsexplorer.data.repository

import com.example.starwarsexplorer.data.local.datasource.StarWarsLocalDataSource
import com.example.starwarsexplorer.data.local.mapper.LocalMapper
import com.example.starwarsexplorer.data.remote.datasource.StarWarsRemoteDataSource
import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import com.example.starwarsexplorer.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val remote: StarWarsRemoteDataSource,
    private val local: StarWarsLocalDataSource,
    private val remoteMapper: RemoteMapper,
    private val localMapper: LocalMapper
) : StarWarsRepository {

    override suspend fun getStarships(): Resource<List<Starship>> = withContext(Dispatchers.IO) {
        try {
            // Fetch from remote
            val response = remote.getStarships()
            if (response.isSuccessful) {
                val dtoList = response.body() ?: emptyList()
                // Map remote DTOs to local entities for caching
                val entityList = dtoList.map { remoteMapper.starshipDtoToEntity(it) }
                // Save entities locally
                local.saveStarships(entityList)
                // Map entities to domain models for returning
                val domainList = entityList.map { localMapper.starshipEntityToDomain(it) }
                Resource.Success(domainList)
            } else {
                // On failure, try loading from local cache
                val cached = local.getStarships().map { localMapper.starshipEntityToDomain(it) }
                if (cached.isNotEmpty()) Resource.Success(cached)
                else Resource.Error("Failed to fetch starships: ${response.code()}")
            }
        } catch (e: Exception) {
            // On exception, fallback to local cache if available
            val cached = local.getStarships().map { localMapper.starshipEntityToDomain(it) }
            if (cached.isNotEmpty()) Resource.Success(cached)
            else Resource.Error("Exception: ${e.message}")
        }
    }

    override suspend fun getFilms(): Resource<List<Film>> = withContext(Dispatchers.IO) {
        try {
            val response = remote.getFilms()
            if (response.isSuccessful) {
                val dtoList = response.body() ?: emptyList()
                val entityList = dtoList.map { remoteMapper.filmDtoToEntity(it) }
                local.saveFilms(entityList)
                val domainList = entityList.map { localMapper.filmEntityToDomain(it) }
                Resource.Success(domainList)
            } else {
                val cached = local.getFilms().map { localMapper.filmEntityToDomain(it) }
                if (cached.isNotEmpty()) Resource.Success(cached)
                else Resource.Error("Failed to fetch films: ${response.code()}")
            }
        } catch (e: Exception) {
            val cached = local.getFilms().map { localMapper.filmEntityToDomain(it) }
            if (cached.isNotEmpty()) Resource.Success(cached)
            else Resource.Error("Exception: ${e.message}")
        }
    }

    override suspend fun getVehicles(): Resource<List<Vehicle>> = withContext(Dispatchers.IO) {
        try {
            val response = remote.getVehicles()
            if (response.isSuccessful) {
                val dtoList = response.body() ?: emptyList()
                val entityList = dtoList.map { remoteMapper.vehicleDtoToEntity(it) }
                local.saveVehicles(entityList)
                val domainList = entityList.map { localMapper.vehicleEntityToDomain(it) }
                Resource.Success(domainList)
            } else {
                val cached = local.getVehicles().map { localMapper.vehicleEntityToDomain(it) }
                if (cached.isNotEmpty()) Resource.Success(cached)
                else Resource.Error("Failed to fetch vehicles: ${response.code()}")
            }
        } catch (e: Exception) {
            val cached = local.getVehicles().map { localMapper.vehicleEntityToDomain(it) }
            if (cached.isNotEmpty()) Resource.Success(cached)
            else Resource.Error("Exception: ${e.message}")
        }
    }
}