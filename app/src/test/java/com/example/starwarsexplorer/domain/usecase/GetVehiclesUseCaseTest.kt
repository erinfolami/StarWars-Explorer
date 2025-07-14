package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.repository.Repository
import com.example.starwarsexplorer.domain.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetVehiclesUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var useCase: GetVehiclesUseCase

    @Before
    fun setUp() {
        repository = mockk()
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
        coEvery { repository.getVehicles() } returns Resource.Success(vehicles)

        // When
        val result = useCase()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals(vehicles, data)
        coVerify(exactly = 1) { repository.getVehicles() }
    }
}
