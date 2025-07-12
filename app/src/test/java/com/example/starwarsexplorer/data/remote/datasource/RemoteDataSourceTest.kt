package com.example.starwarsexplorer.data.remote.datasource

import com.example.starwarsexplorer.data.remote.api.ApiService
import com.example.starwarsexplorer.data.remote.datasource.RemoteDataSource
import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.FilmResponse
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.StarshipResponse
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import com.example.starwarsexplorer.data.remote.model.VehicleResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceTest {

    private lateinit var apiService: ApiService
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        apiService = mockk()
        remoteDataSource = RemoteDataSource(apiService)
    }

    @Test
    fun `getStarships returns expected data when api call is successful`() = runTest {
        // Given
        val expectedDto = StarshipDto(
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

        val response = StarshipResponse(1, null, null, listOf(expectedDto))
        coEvery { apiService.getStarships() } returns Response.success(response)

        // When
        val result = remoteDataSource.getStarships()

        // Then
        assertEquals(1, result.body()?.results?.size)
        assertEquals("X-Wing", result.body()?.results?.first()?.name)
    }

    @Test
    fun `getFilms returns expected data when api call is successful`() = runTest {
        // Given
        val expectedDto = FilmDto(
            title = "A New Hope",
            episodeId = 4,
            openingCrawl = "...",
            director = "George Lucas",
            producer = "Gary Kurtz",
            releaseDate = "1977-05-25",
            characters = emptyList(),
            planets = emptyList(),
            starships = emptyList(),
            vehicles = emptyList(),
            species = emptyList(),
            created = "",
            edited = "",
            url = ""
        )
        val response = FilmResponse(1, null, null, listOf(expectedDto))
        coEvery { apiService.getFilms() } returns Response.success(response)

        // When
        val result = remoteDataSource.getFilms()

        // Then
        assertEquals(1, result.body()?.results?.size)
        assertEquals("A New Hope", result.body()?.results?.first()?.title)
    }

    @Test
    fun `getVehicles returns expected data when api call is successful`() = runTest {
        // Given
        val expectedDto = VehicleDto(
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
            created = "",
            edited = "",
            url = ""
        )
        val response = VehicleResponse(1, null, null, listOf(expectedDto))
        coEvery { apiService.getVehicles() } returns Response.success(response)

        // When
        val result = remoteDataSource.getVehicles()

        // Then
        assertEquals(1, result.body()?.results?.size)
        assertEquals("Speeder", result.body()?.results?.first()?.name)
    }
}
