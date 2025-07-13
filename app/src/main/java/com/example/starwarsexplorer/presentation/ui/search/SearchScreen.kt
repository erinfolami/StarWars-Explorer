package com.example.starwarsexplorer.presentation.ui.search

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.starwarsexplorer.domain.model.SearchResults
import com.example.starwarsexplorer.presentation.shared.SharedViewModel
import com.example.starwarsexplorer.presentation.ui.component.SearchButton
import com.example.starwarsexplorer.presentation.ui.component.SearchInputField
import com.example.starwarsexplorer.presentation.ui.component.SearchResultsList

@Composable
fun SearchScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    onNavigateToResultsScreen: (SearchResults) -> Unit,
) {

    val viewModel: SearchViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            SearchInputField(
                searchQuery = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = {
                    focusManager.clearFocus()
                    viewModel.search(searchQuery)
                },
                modifier = Modifier
                    .weight(1f)
            )

            SearchButton(onClick = {
                focusManager.clearFocus()
                viewModel.search(searchQuery)
            }, modifier = Modifier)
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is SearchUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .testTag("LoadingIndicator")
                )
            }

            is SearchUiState.Success -> {

                val results = (uiState as SearchUiState.Success).data

                LaunchedEffect(Unit) {
                    sharedViewModel.setSearchResults(results)
                    onNavigateToResultsScreen(results)
                }
            }

            is SearchUiState.Error -> {
                Text(
                    text = (uiState as SearchUiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }

            SearchUiState.Empty -> {
                Text(
                    text = "Please enter a search query.",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
//    SearchScreen(
//        sharedViewModel = {},
//        onViewLastResults = { /* Preview: do nothing */ },
//        onNavigateToResultsScreen = { /* Preview: do nothing */ }
//    )
}