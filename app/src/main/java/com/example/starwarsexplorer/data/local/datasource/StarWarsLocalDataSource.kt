package com.example.starwarsexplorer.data.local.datasource

import com.example.starwarsexplorer.data.local.dao.FilmDao
import com.example.starwarsexplorer.data.local.dao.StarshipDao
import com.example.starwarsexplorer.data.local.dao.VehicleDao
import com.example.starwarsexplorer.data.local.entities.FilmEntity
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import com.example.starwarsexplorer.data.local.entities.VehicleEntity
import javax.inject.Inject

class StarWarsLocalDataSource @Inject constructor(
    private val starshipDao: StarshipDao,
    private val filmDao: FilmDao,
    private val vehicleDao: VehicleDao
) {

    // Starships
    suspend fun getStarships(): List<StarshipEntity> = starshipDao.getAllStarships()
    suspend fun saveStarships(starships: List<StarshipEntity>) =
        starshipDao.insertStarships(starships)

    // Films
    suspend fun getFilms(): List<FilmEntity> = filmDao.getAllFilms()
    suspend fun saveFilms(films: List<FilmEntity>) = filmDao.insertFilms(films)

    // Vehicles
    suspend fun getVehicles(): List<VehicleEntity> = vehicleDao.getAllVehicles()
    suspend fun saveVehicles(vehicles: List<VehicleEntity>) = vehicleDao.insertVehicles(vehicles)

}