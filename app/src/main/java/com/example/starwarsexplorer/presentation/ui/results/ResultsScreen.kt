package com.example.starwarsexplorer.presentation.ui.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwarsexplorer.presentation.shared.SharedViewModel
import com.example.starwarsexplorer.presentation.ui.component.SearchResultsList

//@Composable
//fun ResultsScreen(sharedViewModel: SharedViewModel, navController: NavController) {
@Composable
fun ResultsScreen(sharedViewModel: SharedViewModel, navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        val searchResults = sharedViewModel.searchResults.collectAsState()


//            Spacer(modifier = Modifier.height(8.dp))

            SearchResultsList(searchResults.value)


//        Text(searchResults.value?.films.toString())


    }
}

@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
//    ResultsScreen(
//    )
}