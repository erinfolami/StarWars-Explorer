package com.example.starwarsexplorer.domain.usecase


import com.example.starwarsexplorer.domain.model.Film
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
class GetFilmsUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var useCase: GetFilmsUseCase


    @Before
    fun setUp() {
        repository = mockk()
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
        coEvery { repository.getFilms() } returns Resource.Success(films)

        // When
        val result = useCase()

        // Then
        assertTrue(result is Resource.Success)
        val data = (result as Resource.Success).data
        assertEquals(films, data)
        coVerify(exactly = 1) { repository.getFilms() }
    }
}
