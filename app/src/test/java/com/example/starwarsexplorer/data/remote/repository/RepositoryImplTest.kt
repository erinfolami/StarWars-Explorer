package com.example.starwarsexplorer.data.remote.repository

import com.example.starwarsexplorer.data.local.datasource.LocalDataSource
import com.example.starwarsexplorer.data.local.entities.FilmEntity
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import com.example.starwarsexplorer.data.local.entities.VehicleEntity
import com.example.starwarsexplorer.data.remote.mapper.RemoteMapper
import com.example.starwarsexplorer.data.local.mapper.LocalMapper
import com.example.starwarsexplorer.data.remote.datasource.RemoteDataSource
import com.example.starwarsexplorer.data.remote.model.FilmDto
import com.example.starwarsexplorer.data.remote.model.FilmResponse
import com.example.starwarsexplorer.data.remote.model.StarshipDto
import com.example.starwarsexplorer.data.remote.model.StarshipResponse
import com.example.starwarsexplorer.data.remote.model.VehicleDto
import com.example.starwarsexplorer.data.remote.model.VehicleResponse
import com.example.starwarsexplorer.data.repository.RepositoryImpl
import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.util.Resource
import com.example.starwarsexplorer.domain.repository.Repository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response


@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteMapper: RemoteMapper
    private lateinit var localMapper: LocalMapper
    private lateinit var repository: RepositoryImpl

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        localDataSource = mockk()
        remoteMapper = mockk()
        localMapper = mockk()
        repository = RepositoryImpl(remoteDataSource, localDataSource, remoteMapper, localMapper)
    }

    @Test
    fun `getStarships returns data from remote on success`() = runTest {
        // Given
        val dto = StarshipDto(
            name = "X-Wing", model = "T-65B", manufacturer = "Incom",
            costInCredits = "149999", length = "12.5", maxAtmospheringSpeed = "1050",
            crew = "1", passengers = "0", cargoCapacity = "110", consumables = "1 week",
            hyperdriveRating = "1.0", mglt = "100", starshipClass = "Starfighter",
            pilots = emptyList(), films = listOf(), created = "", edited = "", url = ""
        )
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

        val domain = Starship(
            name = "X-Wing",
            model = "T-65B",
            manufacturer = "Incom Corporation",
            costInCredits = "149999",
            length = "12.5",
            crew = "1",
            passengers = "0",
            cargoCapacity = "110"
        )
        coEvery { remoteDataSource.getStarships() } returns Response.success(
            StarshipResponse(1, null, null, listOf(dto))
        )
        every { remoteMapper.starshipDtoToEntity(dto) } returns entity
        coEvery { localDataSource.saveStarships(listOf(entity)) } just Runs
        every { localMapper.starshipEntityToDomain(entity) } returns domain

        // When
        val result = repository.getStarships()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals(1, data.size)
        assertEquals("X-Wing", result.data.first().name)

        coVerify(exactly = 1) { remoteDataSource.getStarships() }
        coVerify(exactly = 1) { localDataSource.saveStarships(listOf(entity)) }
    }

    @Test
    fun `getFilms returns data from remote on success`() = runTest {
        // Given
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
            created = "",
            edited = "",
            url = ""
        )

        val entity = FilmEntity(
            title = "A New Hope",
            episodeId = 4,
            director = "George Lucas",
            producer = "Gary Kurtz",
            releaseDate = "1977-05-25",
            openingCrawl = "It is a period of civil war..."
        )

        val domain = Film(
            title = "A New Hope",
            episodeId = 4,
            director = "George Lucas",
            producer = "Gary Kurtz",
            releaseDate = "1977-05-25",
            openingCrawl = "It is a period of civil war..."
        )

        coEvery { remoteDataSource.getFilms() } returns Response.success(
            FilmResponse(1, null, null, listOf(dto))
        )
        every { remoteMapper.filmDtoToEntity(dto) } returns entity
        coEvery { localDataSource.saveFilms(listOf(entity)) } just Runs
        every { localMapper.filmEntityToDomain(entity) } returns domain

        // When
        val result = repository.getFilms()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals(1, data.size)
        assertEquals("A New Hope", data.first().title)

        coVerify(exactly = 1) { remoteDataSource.getFilms() }
        coVerify(exactly = 1) { localDataSource.saveFilms(listOf(entity)) }
    }


    @Test
    fun `getVehicles returns data from remote on success`() = runTest {
        // Given
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
            created = "",
            edited = "",
            url = ""
        )

        val entity = VehicleEntity(
            name = "Speeder",
            model = "74-Z",
            manufacturer = "Aratech",
            costInCredits = "8000",
            length = "3.4",
            crew = "1",
            passengers = "0",
            cargoCapacity = "4"
        )

        val domain = Vehicle(
            name = "Speeder",
            model = "74-Z",
            manufacturer = "Aratech",
            costInCredits = "8000",
            length = "3.4",
            crew = "1",
            passengers = "0",
            cargoCapacity = "4"
        )

        coEvery { remoteDataSource.getVehicles() } returns Response.success(
            VehicleResponse(1, null, null, listOf(dto))
        )
        every { remoteMapper.vehicleDtoToEntity(dto) } returns entity
        coEvery { localDataSource.saveVehicles(listOf(entity)) } just Runs
        every { localMapper.vehicleEntityToDomain(entity) } returns domain

        // When
        val result = repository.getVehicles()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals(1, data.size)
        assertEquals("Speeder", data.first().name)

        coVerify(exactly = 1) { remoteDataSource.getVehicles() }
        coVerify(exactly = 1) { localDataSource.saveVehicles(listOf(entity)) }
    }
}


