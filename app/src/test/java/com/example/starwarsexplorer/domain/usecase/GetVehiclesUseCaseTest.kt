package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetVehiclesUseCaseTest {

    private lateinit var useCase: GetVehiclesUseCase
    private val repository: StarWarsRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetVehiclesUseCase(repository)
    }

    @Test
    fun `should return list of vehicles from repository`() = runTest {
        // Given
        val vehicles = listOf(
            Vehicle(
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
        coEvery { repository.getVehicles() } returns vehicles

        // When
        val result = useCase()

        // Then
        assertEquals(vehicles, result)
        coVerify(exactly = 1) { repository.getVehicles() }
    }
}
