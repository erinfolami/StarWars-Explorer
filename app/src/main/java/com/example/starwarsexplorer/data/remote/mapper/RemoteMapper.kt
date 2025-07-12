package com.example.starwarsexplorer.data.remote.mapper

import com.example.starwarsexplorer.data.local.entities.FilmEntity
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import com.example.starwarsexplorer.data.local.entities.VehicleEntity
import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle


class RemoteMapper {

    fun starshipDtoToEntity(dto: StarshipDto): StarshipEntity {
        return StarshipEntity(
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

    fun filmDtoToEntity(dto: FilmDto): FilmEntity {
        return FilmEntity(
            title = dto.title,
            episodeId = dto.episodeId,
            director = dto.director,
            producer = dto.producer,
            releaseDate = dto.releaseDate,
            openingCrawl = dto.openingCrawl
        )
    }

    fun vehicleDtoToEntity(dto: VehicleDto): VehicleEntity {
        return VehicleEntity(
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
}
