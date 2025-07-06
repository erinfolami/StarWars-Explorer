package com.example.starwarsexplorer.data.remote.repository

import com.example.starwarsexplorer.data.local.datasource.LocalDataSource
import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
import com.example.starwarsexplorer.data.local.mapper.LocalMapper
import com.example.starwarsexplorer.data.remote.datasource.RemoteDataSource
import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import com.example.starwarsexplorer.data.repository.RepositoryImpl
import com.example.starwarsexplorer.domain.util.Resource
import com.example.starwarsexplorer.domain.repository.Repository
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
class RepositoryImplTest {

    private val remoteDataSource: RemoteDataSource = mockk()
    private val localDataSource: LocalDataSource = mockk()
    private val remoteMapper = RemoteMapper()
    private val localMapper = LocalMapper()

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = RepositoryImpl(
            remote = remoteDataSource,
            local = localDataSource,
            remoteMapper = remoteMapper,
            localMapper = localMapper
        )
    }

    @Test
    fun `getStarships returns mapped starships on success`() = runTest {
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
        coEvery { remoteDataSource.getStarships() } returns Response.success(dtoList)
        coEvery { localDataSource.saveStarships(any()) } returns Unit

        // When
        val result = repository.getStarships()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals("X-Wing", data.first().name)
        coVerify { remoteDataSource.getStarships() }
    }

    @Test
    fun `getFilms returns mapped films on success`() = runTest {
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
        coEvery { remoteDataSource.getFilms() } returns Response.success(dtoList)
        coEvery { localDataSource.saveFilms(any()) } returns Unit

        // When
        val result = repository.getFilms()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals("A New Hope", data.first().title)
        coVerify { remoteDataSource.getFilms() }
    }

    @Test
    fun `getVehicles returns mapped vehicles on success`() = runTest {
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
        coEvery { remoteDataSource.getVehicles() } returns Response.success(dtoList)
        coEvery { localDataSource.saveVehicles(any()) } returns Unit

        // When
        val result = repository.getVehicles()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals("Sand Crawler", data.first().name)
        coVerify { remoteDataSource.getVehicles() }
    }
}
