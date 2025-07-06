package com.example.starwarsexplorer.data.remote.repository


import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
import com.example.starwarsexplorer.data.remote.api.StarWarsApiService
import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import com.example.starwarsexplorer.data.repository.StarWarsRepositoryImpl
import com.example.starwarsexplorer.domain.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class StarWarsRepositoryImplTest {

    private val apiService: StarWarsApiService = mockk()
    private val mapper = RemoteMapper()
    private lateinit var repository: StarWarsRepositoryImpl

    @Before
    fun setUp() {
        repository = StarWarsRepositoryImpl(apiService, mapper)
    }

    @Test
    fun `getStarships should return mapped starships from API`() = runTest {
        // Given
        val dtoList = listOf(
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
        coEvery { apiService.getStarships() } returns Response.success(dtoList)

        // When
        val result = repository.getStarships()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals(1, data.size)
        assertEquals("X-Wing", data[0].name)
        coVerify(exactly = 1) { apiService.getStarships() }
    }

    @Test
    fun `getFilms should return mapped films from API`() = runTest {
        // Given
        val dtoList = listOf(
            FilmDto(
                title = "A New Hope",
                episodeNumber = 4,
                director = "George Lucas",
                producer = "Gary Kurtz, Rick McCallum",
                releaseDate = "1977-05-25",
                openingCrawl = "It is a period of civil war..."
            )
        )
        coEvery { apiService.getFilms() } returns Response.success(dtoList)

        // When
        val result = repository.getFilms()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals(1, data.size)
        assertEquals("A New Hope", data[0].title)
        coVerify(exactly = 1) { apiService.getStarships() }
    }

    @Test
    fun `getVehicles should return mapped vehicles from API`() = runTest {
        // Given
        val dtoList = listOf(
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
        coEvery { apiService.getVehicles() } returns Response.success(dtoList)

        // When
        val result = repository.getVehicles()


        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals(1, data.size)
        assertEquals("Sand Crawler", data[0].name)
        coVerify(exactly = 1) { apiService.getStarships() }

    }
}
