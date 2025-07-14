package com.example.starwarsexplorer.presentation.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsexplorer.domain.model.SearchResults
import com.example.starwarsexplorer.domain.usecase.ClearAllLocalDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val clearAllLocalDataUseCase: ClearAllLocalDataUseCase) :
    ViewModel() {
    private val _searchResults = MutableStateFlow<SearchResults?>(null)
    val searchResults: StateFlow<SearchResults?> = _searchResults

    fun setSearchResults(results: SearchResults) {
        _searchResults.value = results
    }


    fun clearSearchResults() {
        viewModelScope.launch {
            clearAllLocalDataUseCase()
            _searchResults.value = SearchResults(emptyList())
        }
    }
}