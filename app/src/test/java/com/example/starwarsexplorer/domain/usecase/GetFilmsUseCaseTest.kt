package com.example.starwarsexplorer.domain.usecase


import com.example.starwarsexplorer.domain.model.Film
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
class GetFilmsUseCaseTest {

    private lateinit var useCase: GetFilmsUseCase
    private val repository: StarWarsRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetFilmsUseCase(repository)
    }

    @Test
    fun `should return list of films from repository`() = runTest {
        // Given
        val films = listOf(
            Film(
                title = "A New Hope",
                episodeId = 4,
                director = "George Lucas",
                producer = "Gary Kurtz, Rick McCallum",
                releaseDate = "1977-05-25",
                openingCrawl = "It is a period of civil war..."
            )
        )
        coEvery { repository.getFilms() } returns films

        // When
        val result = useCase()

        // Then
        assertEquals(films, result)
        coVerify(exactly = 1) { repository.getFilms() }
    }
}
