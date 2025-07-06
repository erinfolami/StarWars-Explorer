package com.example.starwarsexplorer.data.remote.mapper

import com.example.starwarsexplorer.data.local.entities.FilmEntity
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import com.example.starwarsexplorer.data.local.entities.VehicleEntity
import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
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
            crew = "1",
            passengers = "0",
            cargoCapacity = "110"
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
            episodeNumber = 4,
            director = "George Lucas",
            producer = "Gary Kurtz, Rick McCallum",
            releaseDate = "1977-05-25",
            openingCrawl = "It is a period of civil war..."
        )

        val expected = FilmEntity(
            title = "A New Hope",
            episodeId = 4,
            director = "George Lucas",
            producer = "Gary Kurtz, Rick McCallum",
            releaseDate = "1977-05-25",
            openingCrawl = "It is a period of civil war..."
        )

        val actual = mapper.filmDtoToEntity(dto)

        assertEquals(expected, actual)
    }

    @Test
    fun `vehicleDtoToEntity maps correctly`() {
        val dto = VehicleDto(
            name = "Sand Crawler",
            model = "Digger Crawler",
            manufacturer = "Corellia Mining Corporation",
            costInCredits = "150000",
            length = "36.8",
            crew = "46",
            passengers = "30",
            cargoCapacity = "50000"
        )

        val expected = VehicleEntity(
            name = "Sand Crawler",
            model = "Digger Crawler",
            manufacturer = "Corellia Mining Corporation",
            costInCredits = "150000",
            length = "36.8",
            crew = "46",
            passengers = "30",
            cargoCapacity = "50000"
        )

        val actual = mapper.vehicleDtoToEntity(dto)

        assertEquals(expected, actual)
    }
}
