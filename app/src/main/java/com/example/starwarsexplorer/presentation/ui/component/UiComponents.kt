package com.example.starwarsexplorer.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import com.example.starwarsexplorer.domain.model.SearchResults


@Composable
fun SearchButton(onSearchClick: () -> Unit) {
    Button(onClick = { onSearchClick() }) {
        Text("Search")
    }
}


@Composable
fun ViewLastResultButton(onViewLatResultClick: () -> Unit) {
    Button(onClick = onViewLatResultClick) {
        Text("View Last Results")
    }
}

@Composable
fun ClearResultButton(onClearResultClick: () -> Unit) {
    Button(onClick = { onClearResultClick() }) {
        Text("Clear Results")
    }
}


@Composable
fun SearchResultsList(
    results: SearchResults?,
) {
    LazyColumn {
        items(results?.films ?: emptyList()) { film ->
            Text(
                text = film.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
        }
        items(results?.starships ?: emptyList()) { starship ->
            Text(
                text = starship.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
        }
        items(results?.vehicles ?: emptyList()) { vehicle ->
            Text(
                text = vehicle.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultsListPreview() {
    val sampleResults = List(5) { "Result item #${it + 1}" }
//    SearchResultsList()
}