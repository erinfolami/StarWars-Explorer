package com.example.starwarsexplorer.data.local.mapper

import com.example.starwarsexplorer.data.local.entities.FilmEntity
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import com.example.starwarsexplorer.data.local.entities.VehicleEntity
import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalMapperTest {

    private lateinit var mapper: LocalMapper

    @Before
    fun setUp() {
        mapper = LocalMapper()
    }

    @Test
    fun `starshipEntityToDomain maps correctly`() {
        val entity = StarshipEntity(
            name = "X-Wing",
            model = "T-65B",
            manufacturer = "Incom Corporation",
            costInCredits = "149999",
            length = "12.5",
            crew = "1",
            passengers = "0",
            cargoCapacity = "110"
        )

        val expected = Starship(
            name = "X-Wing",
            model = "T-65B",
            manufacturer = "Incom Corporation",
            costInCredits = "149999",
            length = "12.5",
            crew = "1",
            passengers = "0",
            cargoCapacity = "110"
        )

        val actual = mapper.starshipEntityToDomain(entity)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `filmEntityToDomain maps correctly`() {
        val entity = FilmEntity(
            title = "A New Hope",
            episodeId = 4,
            director = "George Lucas",
            producer = "Gary Kurtz, Rick McCallum",
            releaseDate = "1977-05-25",
            openingCrawl = "It is a period of civil war..."
        )

        val expected = Film(
            title = "A New Hope",
            episodeId = 4,
            director = "George Lucas",
            producer = "Gary Kurtz, Rick McCallum",
            releaseDate = "1977-05-25",
            openingCrawl = "It is a period of civil war..."
        )

        val actual = mapper.filmEntityToDomain(entity)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `vehicleEntityToDomain maps correctly`() {
        val entity = VehicleEntity(
            name = "Sand Crawler",
            model = "Digger Crawler",
            manufacturer = "Corellia Mining Corporation",
            costInCredits = "150000",
            length = "36.8",
            crew = "46",
            passengers = "30",
            cargoCapacity = "50000"
        )

        val expected = Vehicle(
            name = "Sand Crawler",
            model = "Digger Crawler",
            manufacturer = "Corellia Mining Corporation",
            costInCredits = "150000",
            length = "36.8",
            crew = "46",
            passengers = "30",
            cargoCapacity = "50000"
        )

        val actual = mapper.vehicleEntityToDomain(entity)
        Assert.assertEquals(expected, actual)
    }
}