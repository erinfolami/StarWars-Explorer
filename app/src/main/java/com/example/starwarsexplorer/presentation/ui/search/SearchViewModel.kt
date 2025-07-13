package com.example.starwarsexplorer.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsexplorer.domain.model.SearchResults
import com.example.starwarsexplorer.domain.model.SearchType
import com.example.starwarsexplorer.domain.usecase.GetFilmsUseCase
import com.example.starwarsexplorer.domain.usecase.GetStarshipsUseCase
import com.example.starwarsexplorer.domain.usecase.GetVehiclesUseCase
import com.example.starwarsexplorer.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getStarshipsUseCase: GetStarshipsUseCase,
    private val getFilmsUseCase: GetFilmsUseCase,
    private val getVehiclesUseCase: GetVehiclesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Empty)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading

            when (detectSearchType(query)) {
                SearchType.FILM -> fetchFilms()
                SearchType.STARSHIP -> fetchStarships()
                SearchType.VEHICLE -> fetchVehicles()
                null -> _uiState.value = SearchUiState.Error("Query does not match films, starships, or vehicles")
            }
        }
    }

    private fun detectSearchType(query: String): SearchType? = when {
        query.contains("film", ignoreCase = true) -> SearchType.FILM
        query.contains("starship", ignoreCase = true) -> SearchType.STARSHIP
        query.contains("vehicle", ignoreCase = true) -> SearchType.VEHICLE
        else -> null
    }

    private suspend fun fetchFilms() {
        when (val result = getFilmsUseCase()) {
            is Resource.Success -> {
                val films = result.data.orEmpty()
                if (films.isEmpty()) {
                    _uiState.value = SearchUiState.Error("No films found")
                } else {
                    _uiState.value = SearchUiState.Success(SearchResults(films = films))
                }
            }
            is Resource.Error -> _uiState.value = SearchUiState.Error(result.message ?: "Error fetching films")
            else -> _uiState.value = SearchUiState.Error("Unexpected error")
        }
    }

    private suspend fun fetchStarships() {
        when (val result = getStarshipsUseCase()) {
            is Resource.Success -> {
                val starships = result.data.orEmpty()
                if (starships.isEmpty()) {
                    _uiState.value = SearchUiState.Error("No starships found")
                } else {
                    _uiState.value = SearchUiState.Success(SearchResults(starships = starships))
                }
            }
            is Resource.Error -> _uiState.value = SearchUiState.Error(result.message ?: "Error fetching starships")
            else -> _uiState.value = SearchUiState.Error("Unexpected error")
        }
    }

    private suspend fun fetchVehicles() {
        when (val result = getVehiclesUseCase()) {
            is Resource.Success -> {
                val vehicles = result.data.orEmpty()
                if (vehicles.isEmpty()) {
                    _uiState.value = SearchUiState.Error("No vehicles found")
                } else {
                    _uiState.value = SearchUiState.Success(SearchResults(vehicles = vehicles))
                }
            }
            is Resource.Error -> _uiState.value = SearchUiState.Error(result.message ?: "Error fetching vehicles")
            else -> _uiState.value = SearchUiState.Error("Unexpected error")
        }
    }
}

