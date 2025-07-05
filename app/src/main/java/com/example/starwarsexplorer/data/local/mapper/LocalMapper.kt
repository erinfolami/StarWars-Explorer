package com.example.starwarsexplorer.data.local.mapper

import com.example.starwarsexplorer.data.local.entities.FilmEntity
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import com.example.starwarsexplorer.data.local.entities.VehicleEntity
import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle


class LocalMapper {
    fun starshipEntityToDomain(entity: StarshipEntity): Starship {
        return Starship(
            name = entity.name,
            model = entity.model,
            manufacturer = entity.manufacturer,
            costInCredits = entity.costInCredits,
            length = entity.length,
            crew = entity.crew,
            passengers = entity.passengers,
            cargoCapacity = entity.cargoCapacity
        )
    }

    fun filmEntityToDomain(entity: FilmEntity): Film {
        return Film(
            title = entity.title,
            episodeId = entity.episodeId,
            director = entity.director,
            producer = entity.producer,
            releaseDate = entity.releaseDate,
            openingCrawl = entity.openingCrawl
        )
    }

    fun vehicleEntityToDomain(entity: VehicleEntity): Vehicle {
        return Vehicle(
            name = entity.name,
            model = entity.model,
            manufacturer = entity.manufacturer,
            costInCredits = entity.costInCredits,
            length = entity.length,
            crew = entity.crew,
            passengers = entity.passengers,
            cargoCapacity = entity.cargoCapacity
        )
    }
}