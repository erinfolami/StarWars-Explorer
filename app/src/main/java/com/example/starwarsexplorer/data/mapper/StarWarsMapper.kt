package com.example.starwarsexplorer.data.mapper

import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle


class StarWarsMapper {

    fun mapStarship(dto: StarshipDto): Starship {
        return Starship(
            name = dto.name,
            model = dto.model,
            manufacturer = dto.manufacturer,
            costInCredits = dto.costInCredits,
            length = dto.length,
            crew = dto.crew,
            passengers = dto.passengers,
            cargoCapacity = dto.cargoCapacity
        )
    }

    fun mapVehicle(dto: VehicleDto): Vehicle {
        return Vehicle(
            name = dto.name,
            model = dto.model,
            manufacturer = dto.manufacturer,
            costInCredits = dto.costInCredits,
            length = dto.length,
            crew = dto.crew,
            passengers = dto.passengers,
            cargoCapacity = dto.cargoCapacity
        )
    }

    fun mapFilm(dto: FilmDto): Film {
        return Film(
            title = dto.title,
            episodeId = dto.episodeNumber,
            director = dto.director,
            producer = dto.producer,
            releaseDate = dto.releaseDate,
            openingCrawl = dto.openingCrawl
        )
    }
}
