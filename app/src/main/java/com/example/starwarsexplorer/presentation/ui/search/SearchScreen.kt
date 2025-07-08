package com.example.starwarsexplorer.presentation.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starwarsexplorer.presentation.ui.component.SearchButton
import com.example.starwarsexplorer.presentation.ui.component.SearchResultsList
import com.example.starwarsexplorer.presentation.ui.component.ViewLastResultButton

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onSearchClick: () -> Unit,
    onViewLastResults: () -> Unit,
    onNavigateToResultsScreen: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Enter search term") },
                modifier = Modifier.wrapContentWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                        viewModel.search(searchQuery.trim())
                    }
                )
            )

            Spacer(modifier = Modifier.width(5.dp))
            SearchButton { onSearchClick() }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is SearchUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is SearchUiState.Success -> {
                val results = (uiState as SearchUiState.Success).data
                Text(
                    text = results.vehicles.first().name,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

//                SearchResultsList(results, onNavigateToResultsScreen(results))
            }

            is SearchUiState.Error -> {
                Text(
                    text = (uiState as SearchUiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            SearchUiState.Empty -> {
                Text(
                    text = "Please enter a search query.",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }

    ViewLastResultButton { onViewLastResults() }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        onSearchClick = { /* Preview: do nothing or show a toast */ },
        onViewLastResults = { /* Preview: do nothing */ },
        onNavigateToResultsScreen = { /* Preview: do nothing */ }
    )
}