//package com.example.starwarsexplorer.presentation.ui.results
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.starwarsexplorer.domain.model.SearchResults
//import com.example.starwarsexplorer.presentation.ui.component.ClearResultButton
//import com.example.starwarsexplorer.presentation.ui.component.SearchResultsList
//import com.example.starwarsexplorer.presentation.ui.search.SearchScreen
//
//@Composable
//fun ResultsScreen(results: SearchResults) {
//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp),
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.End // aligns content to the right
//        ) {
//            ClearResultButton {
//                // clear logic
//            }
//        }
//            Spacer(modifier = Modifier.height(8.dp))
//
//            SearchResultsList(results)
//
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ResultsScreenPreview() {
////    ResultsScreen(
////    )
//}