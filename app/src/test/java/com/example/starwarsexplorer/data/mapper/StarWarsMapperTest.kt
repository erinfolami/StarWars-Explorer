//package com.example.starwarsexplorer.data.mapper
//
//
//import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
//import com.example.starwarsexplorer.data.remote.model.FilmDto
//import com.example.starwarsexplorer.data.remote.model.StarshipDto
//import com.example.starwarsexplorer.data.remote.model.VehicleDto
//import com.example.starwarsexplorer.domain.model.Film
//import com.example.starwarsexplorer.domain.model.Starship
//import com.example.starwarsexplorer.domain.model.Vehicle
//import org.junit.Assert.assertEquals
//import org.junit.Test
//
//class StarWarsMapperTest {
//
//    private val mapper = RemoteMapper()
//
//    @Test
//    fun `mapStarship should map correctly`() {
//        // Given
//        val dto = StarshipDto(
//            name = "X-Wing",
//            model = "T-65B",
//            manufacturer = "Incom Corporation",
//            costInCredits = "149999",
//            length = "12.5",
//            crew = "1",
//            passengers = "0",
//            cargoCapacity = "110"
//        )
//
//        val expected = Starship(
//            name = "X-Wing",
//            model = "T-65B",
//            manufacturer = "Incom Corporation",
//            costInCredits = "149999",
//            length = "12.5",
//            crew = "1",
//            passengers = "0",
//            cargoCapacity = "110"
//        )
//
//        // When
//        val result = mapper.mapStarship(dto)
//
//        // Then
//        assertEquals(expected, result)
//    }
//
//    @Test
//    fun `mapFilm should map correctly`() {
//        // Given
//        val dto = FilmDto(
//            title = "A New Hope",
//            episodeNumber = 4,
//            director = "George Lucas",
//            producer = "Gary Kurtz, Rick McCallum",
//            releaseDate = "1977-05-25",
//            openingCrawl = "It is a period of civil war..."
//        )
//
//        val expected = Film(
//            title = "A New Hope",
//            episodeId = 4,
//            director = "George Lucas",
//            producer = "Gary Kurtz, Rick McCallum",
//            releaseDate = "1977-05-25",
//            openingCrawl = "It is a period of civil war..."
//        )
//
//        // When
//        val result = mapper.mapFilm(dto)
//
//        // Then
//        assertEquals(expected, result)
//    }
//
//    @Test
//    fun `mapVehicle should map correctly`() {
//        // Given
//        val dto = VehicleDto(
//            name = "Sand Crawler",
//            model = "Digger Crawler",
//            manufacturer = "Corellia Mining Corporation",
//            costInCredits = "150000",
//            length = "36.8",
//            crew = "46",
//            passengers = "30",
//            cargoCapacity = "50000"
//        )
//
//        val expected = Vehicle(
//            name = "Sand Crawler",
//            model = "Digger Crawler",
//            manufacturer = "Corellia Mining Corporation",
//            costInCredits = "150000",
//            length = "36.8",
//            crew = "46",
//            passengers = "30",
//            cargoCapacity = "50000"
//        )
//
//        // When
//        val result = mapper.mapVehicle(dto)
//
//        // Then
//        assertEquals(expected, result)
//    }
//}
