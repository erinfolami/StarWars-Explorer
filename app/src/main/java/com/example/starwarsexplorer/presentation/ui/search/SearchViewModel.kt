package com.example.starwarsexplorer.presentation.ui.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsexplorer.domain.model.SearchResults
import com.example.starwarsexplorer.domain.usecase.GetFilmsUseCase
import com.example.starwarsexplorer.domain.usecase.GetStarshipsUseCase
import com.example.starwarsexplorer.domain.usecase.GetVehiclesUseCase
import com.example.starwarsexplorer.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

            when {
                query.contains("film", ignoreCase = true) -> {
                    when (val result = getFilmsUseCase()) {
                        is Resource.Success -> {
                            val films = result.data ?: emptyList()
                            if (films.isEmpty()) {
                                _uiState.value = SearchUiState.Error("No films found")
                            } else {
                                _uiState.value = SearchUiState.Success(SearchResults(films = films))
                            }
                        }
                        is Resource.Error -> {
                            _uiState.value = SearchUiState.Error(result.message ?: "Error fetching films")
                        }
                        else -> {
                            _uiState.value = SearchUiState.Error("Unexpected error")
                        }
                    }
                }
                query.contains("starship", ignoreCase = true) -> {
                    when (val result = getStarshipsUseCase()) {
                        is Resource.Success -> {
                            val starships = result.data ?: emptyList()
                            if (starships.isEmpty()) {
                                _uiState.value = SearchUiState.Error("No starships found")
                            } else {
                                _uiState.value = SearchUiState.Success(SearchResults(starships = starships))
                            }
                        }
                        is Resource.Error -> {
                            _uiState.value = SearchUiState.Error(result.message ?: "Error fetching starships")
                        }
                        else -> {
                            _uiState.value = SearchUiState.Error("Unexpected error")
                        }
                    }
                }
                query.contains("vehicle", ignoreCase = true) -> {
                    when (val result = getVehiclesUseCase()) {
                        is Resource.Success -> {
                            val vehicles = result.data ?: emptyList()
                            if (vehicles.isEmpty()) {
                                _uiState.value = SearchUiState.Error("No vehicles found")
                            } else {
                                _uiState.value = SearchUiState.Success(SearchResults(vehicles = vehicles))
                            }
                        }
                        is Resource.Error -> {
                            _uiState.value = SearchUiState.Error(result.message ?: "Error fetching vehicles")
                        }
                        else -> {
                            _uiState.value = SearchUiState.Error("Unexpected error")
                        }
                    }
                }
                else -> {
                    _uiState.value = SearchUiState.Error("Query does not match films, starships, or vehicles")
                }
            }
        }
    }
}