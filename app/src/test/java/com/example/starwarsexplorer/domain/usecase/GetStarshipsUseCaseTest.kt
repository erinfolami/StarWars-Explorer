package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.model.Starship
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
class GetStarshipsUseCaseTest {

    private lateinit var useCase: GetStarshipsUseCase
    private val repository: Repository = mockk()

    @Before
    fun setUp() {
        useCase = GetStarshipsUseCase(repository)
    }

    @Test
    fun `should return list of starships from repository`() = runTest {
        // Given
        val starships = listOf(
            Starship(
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
        coEvery { repository.getStarships() } returns Resource.Success(starships)

        // When
        val result = useCase()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals(starships, data)
        coVerify(exactly = 1) { repository.getStarships() }
    }
}
