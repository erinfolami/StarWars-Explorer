package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.model.Starship
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
class GetStarshipsUseCaseTest {

    private lateinit var useCase: GetStarshipsUseCase
    private val repository: StarWarsRepository = mockk()

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
        coEvery { repository.getStarships() } returns starships

        // When
        val result = useCase()

        // Then
        assertEquals(starships, result)
        coVerify(exactly = 1) { repository.getStarships() }
    }
}
