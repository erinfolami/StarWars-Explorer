package com.example.starwarsexplorer.presentation.ui.search

import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.usecase.GetFilmsUseCase
import com.example.starwarsexplorer.domain.usecase.GetStarshipsUseCase
import com.example.starwarsexplorer.domain.usecase.GetVehiclesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.advanceUntilIdle
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.example.starwarsexplorer.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private val getStarshipsUseCase = mockk<GetStarshipsUseCase>()
    private val getFilmsUseCase = mockk<GetFilmsUseCase>()
    private val getVehiclesUseCase = mockk<GetVehiclesUseCase>()

    private lateinit var viewModel: SearchViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        viewModel = SearchViewModel(getStarshipsUseCase, getFilmsUseCase, getVehiclesUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `search with film query returns success`() = runTest {
        val fakeFilms = listOf(Film(
            title = "A New Hope",
            episodeId = 4,
            director = "George Lucas",
            producer = "Gary Kurtz, Rick McCallum",
            releaseDate = "1977-05-25",
            openingCrawl = "It is a period of civil war..."
        ))
        coEvery { getFilmsUseCase() } returns Resource.Success(fakeFilms)

        viewModel.search("film")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is SearchUiState.Success)
        val successState = state as SearchUiState.Success
        assertEquals(fakeFilms, successState.data.films)
    }

    @Test
    fun `search with vehicle query returns success`() = runTest {
        val fakeVehicles = listOf(Vehicle(
            name = "Sand Crawler",
            model = "Digger Crawler",
            manufacturer = "Corellia Mining Corporation",
            costInCredits = "150000",
            length = "36.8",
            crew = "46",
            passengers = "30",
            cargoCapacity = "50000"
        ))
        coEvery { getVehiclesUseCase() } returns Resource.Success(fakeVehicles)

        viewModel.search("vehicle")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is SearchUiState.Success)
        val successState = state as SearchUiState.Success
        assertEquals(fakeVehicles, successState.data.vehicles)
    }

    @Test
    fun `search with starship query returns success`() = runTest {
        val fakeStarships = listOf(Starship(
            name = "X-Wing",
            model = "T-65B",
            manufacturer = "Incom Corporation",
            costInCredits = "149999",
            length = "12.5",
            crew = "1",
            passengers = "0",
            cargoCapacity = "110"
        ))
        coEvery { getStarshipsUseCase() } returns Resource.Success(fakeStarships)

        viewModel.search("starship")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is SearchUiState.Success)
        val successState = state as SearchUiState.Success
        assertEquals(fakeStarships, successState.data.starships)
    }

    @Test
    fun `search with unknown query returns error`() = runTest {
        viewModel.search("unknown")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is SearchUiState.Error)
        val errorState = state as SearchUiState.Error
        assertEquals("Query does not match films, starships, or vehicles", errorState.message)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}