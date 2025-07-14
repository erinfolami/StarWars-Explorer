package com.example.starwarsexplorer.data.remote.mapper

import com.example.starwarsexplorer.data.local.entities.FilmEntity
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import com.example.starwarsexplorer.data.local.entities.VehicleEntity
import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RemoteMapperTest {

    private lateinit var mapper: RemoteMapper

    @Before
    fun setup() {
        // Initialize mapper before each test
        mapper = RemoteMapper()
    }

    @Test
    fun `starshipDtoToEntity maps correctly`() {
        val dto = StarshipDto(
            name = "X-Wing",
            model = "T-65B",
            manufacturer = "Incom Corporation",
            costInCredits = "149999",
            length = "12.5",
            maxAtmospheringSpeed = "1050",
            crew = "1",
            passengers = "0",
            cargoCapacity = "110",
            consumables = "1 week",
            hyperdriveRating = "1.0",
            mglt = "100",
            starshipClass = "Starfighter",
            pilots = emptyList(),
            films = listOf("https://swapi.dev/api/films/1/"),
            created = "2023-01-01T00:00:00Z",
            edited = "2023-01-01T00:00:00Z",
            url = "https://swapi.dev/api/starships/12/"
        )

        val expected = StarshipEntity(
            name = "X-Wing",
            model = "T-65B",
            manufacturer = "Incom Corporation",
            costInCredits = "149999",
            length = "12.5",
            crew = "1",
            passengers = "0",
            cargoCapacity = "110"
        )

        val actual = mapper.starshipDtoToEntity(dto)

        assertEquals(expected, actual)
    }

    @Test
    fun `filmDtoToEntity maps correctly`() {
        val dto = FilmDto(
            title = "A New Hope",
            episodeId = 4,
            openingCrawl = "It is a period of civil war...",
            director = "George Lucas",
            producer = "Gary Kurtz",
            releaseDate = "1977-05-25",
            characters = emptyList(),
            planets = emptyList(),
            starships = emptyList(),
            vehicles = emptyList(),
            species = emptyList(),
            created = "2023-01-01T00:00:00Z",
            edited = "2023-01-01T00:00:00Z",
            url = "https://swapi.dev/api/films/1/"
        )

        val expected = FilmEntity(
            title = "A New Hope",
            episodeId = 4,
            director = "George Lucas",
            producer = "Gary Kurtz",
            releaseDate = "1977-05-25",
            openingCrawl = "It is a period of civil war..."
        )

        val actual = mapper.filmDtoToEntity(dto)

        assertEquals(expected, actual)
    }

    @Test
    fun `vehicleDtoToEntity maps correctly`() {
        val dto = VehicleDto(
            name = "Speeder",
            model = "74-Z",
            manufacturer = "Aratech",
            costInCredits = "8000",
            length = "3.4",
            maxAtmospheringSpeed = "360",
            crew = "1",
            passengers = "0",
            cargoCapacity = "4",
            consumables = "1 day",
            vehicleClass = "Speeder",
            pilots = emptyList(),
            films = emptyList(),
            created = "2023-01-01T00:00:00Z",
            edited = "2023-01-01T00:00:00Z",
            url = "https://swapi.dev/api/vehicles/42/"
        )

        val expected = VehicleEntity(
            name = "Speeder",
            model = "74-Z",
            manufacturer = "Aratech",
            costInCredits = "8000",
            length = "3.4",
            crew = "1",
            passengers = "0",
            cargoCapacity = "4"
        )

        val actual = mapper.vehicleDtoToEntity(dto)

        assertEquals(expected, actual)
    }

}
