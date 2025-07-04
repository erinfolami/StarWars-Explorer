package com.example.starwarsexplorer.presentation.ui.search

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
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwarsexplorer.presentation.ui.component.SearchButton
import com.example.starwarsexplorer.presentation.ui.component.ViewLastResultButton

@Composable
fun SearchScreen(onSearchClick: () -> Unit, onViewLastResults: () -> Unit) {

    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Enter search term") },
                modifier = Modifier.wrapContentWidth()
            )

            Spacer(modifier = Modifier.width(5.dp))
            SearchButton { onSearchClick() }
        }

        ViewLastResultButton { onViewLastResults() }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        onSearchClick = { /* Preview: do nothing or show a toast */ },
        onViewLastResults = { /* Preview: do nothing */ }
    )
}