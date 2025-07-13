package com.example.starwarsexplorer.presentation.shared

import androidx.lifecycle.ViewModel
import com.example.starwarsexplorer.domain.model.SearchResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _searchResults = MutableStateFlow<SearchResults?>(null)
    val searchResults: StateFlow<SearchResults?> = _searchResults

    fun setSearchResults(results: SearchResults) {
        _searchResults.value = results
    }
}