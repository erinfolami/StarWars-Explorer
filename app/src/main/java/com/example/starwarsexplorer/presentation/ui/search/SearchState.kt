package com.example.starwarsexplorer.presentation.ui.search

import com.example.starwarsexplorer.domain.model.SearchResults
import com.example.starwarsexplorer.domain.model.Starship

sealed class SearchUiState {
    object Loading : SearchUiState()
    data class Success(val data: SearchResults) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
    object Empty : SearchUiState()
}