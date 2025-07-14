package com.example.starwarsexplorer.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.starwarsexplorer.domain.model.SearchResults
import com.example.starwarsexplorer.utils.TestTags


@Composable
fun SearchInputField(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .height(56.dp).testTag(TestTags.SearchInputField),
        placeholder = { Text("Search...") },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        )
    )
}


@Composable
fun SearchButton(onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.height(56.dp).testTag(TestTags.SearchButton),
        shape = MaterialTheme.shapes.medium,
        colors = androidx.compose.material.ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
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