package com.example.starwarsexplorer.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SearchButton(onSearchClick: () -> Unit) {
    Button(onClick = { onSearchClick }) {
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
    results: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(results) { result ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = result,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SearchResultsListPreview() {
    val sampleResults = List(5) { "Result item #${it + 1}" }
    SearchResultsList(results = sampleResults)
}