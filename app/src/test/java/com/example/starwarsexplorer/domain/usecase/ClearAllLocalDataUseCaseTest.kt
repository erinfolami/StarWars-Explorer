package com.example.starwarsexplorer.domain.usecase


import com.example.starwarsexplorer.domain.repository.Repository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ClearAllLocalDataUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var useCase: ClearAllLocalDataUseCase

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        useCase = ClearAllLocalDataUseCase(repository)
    }

    @Test
    fun `invoke should call repository clearAllLocalData`() = runTest {
        // When
        useCase()

        // Then
        coVerify(exactly = 1) { repository.clearAllLocalData() }
    }
}
