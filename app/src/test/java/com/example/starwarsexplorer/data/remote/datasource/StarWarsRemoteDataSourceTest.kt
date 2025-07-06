package com.example.starwarsexplorer.data.remote.datasource


import com.example.starwarsexplorer.data.remote.api.StarWarsApiService
import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class StarWarsRemoteDataSourceTest {

    private lateinit var apiService: StarWarsApiService
    private lateinit var remoteDataSource: StarWarsRemoteDataSource

    @Before
    fun setup() {
        apiService = mockk()
        remoteDataSource = StarWarsRemoteDataSource(apiService)
    }

    @Test
    fun `getStarships should return list of StarshipDto`() = runTest {
        // Given
        val starshipDto = listOf(
            StarshipDto(
                name = "X-Wing",
                model = "T-65B",
                manufacturer = "Incom Corporation",
                costInCredits = "149999",
                length = "12.5",
                crew = "1",
                passengers = "0",
                cargoCapacity = "110"
            )
        )
        coEvery { apiService.getStarships() } returns Response.success(starshipDto)

        // When
        val response = remoteDataSource.getStarships()

        // Then
        assertTrue(response.isSuccessful)
        assertEquals(starshipDto, response.body())
        coVerify(exactly = 1) { apiService.getStarships() }
    }

    @Test
    fun `getFilms should return list of FilmDto`() = runTest {
        // Given
        val filmDto = listOf(
            FilmDto(
                title = "A New Hope",
                episodeNumber = 4,
                director = "George Lucas",
                producer = "Gary Kurtz, Rick McCallum",
                releaseDate = "1977-05-25",
                openingCrawl = "It is a period of civil war..."
            )
        )
        coEvery { apiService.getFilms() } returns Response.success(filmDto)

        // When
        val response = remoteDataSource.getFilms()

        // Then
        assertTrue(response.isSuccessful)
        assertEquals(filmDto, response.body())
        coVerify(exactly = 1) { apiService.getFilms() }
    }

    @Test
    fun `getVehicles should return list of VehicleDto`() = runTest {
        // Given
        val vehicleDto = listOf(
            VehicleDto(
                name = "Sand Crawler",
                model = "Digger Crawler",
                manufacturer = "Corellia Mining Corporation",
                costInCredits = "150000",
                length = "36.8",
                crew = "46",
                passengers = "30",
                cargoCapacity = "50000"
            )
        )
        coEvery { apiService.getVehicles() } returns Response.success(vehicleDto)

        // When
        val response = remoteDataSource.getVehicles()

        // Then
        assertTrue(response.isSuccessful)
        assertEquals(vehicleDto, response.body())
        coVerify(exactly = 1) { apiService.getVehicles() }
    }
}
