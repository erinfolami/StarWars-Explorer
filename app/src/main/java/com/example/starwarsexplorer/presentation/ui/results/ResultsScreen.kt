package com.example.starwarsexplorer.presentation.ui.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwarsexplorer.R
import com.example.starwarsexplorer.presentation.shared.SharedViewModel
import com.example.starwarsexplorer.presentation.ui.component.ClearResultButton
import com.example.starwarsexplorer.presentation.ui.component.SearchResultsList


@Composable
fun ResultsScreen(sharedViewModel: SharedViewModel, navController: NavController) {


    Row (
        modifier = Modifier.fillMaxWidth().padding(60.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.clear_results),
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.width(20.dp))

        ClearResultButton(onClearResultClick = {
            sharedViewModel.clearSearchResults() // You'll need to implement this
        })
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {


        val searchResults = sharedViewModel.searchResults.collectAsState()

            SearchResultsList(searchResults.value)

    }
}

@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
//    ResultsScreen(
//    )
}