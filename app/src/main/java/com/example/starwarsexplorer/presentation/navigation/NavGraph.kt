package com.example.starwarsexplorer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.starwarsexplorer.presentation.ui.results.ResultsScreen
import com.example.starwarsexplorer.presentation.ui.search.SearchScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Search.route, modifier = Modifier) {

        composable(route = Screen.Search.route) {
            SearchScreen(
                onSearchClick = { navController.navigate(Screen.Results.route) },
                onViewLastResults = { navController.navigate(Screen.Results.route) }
            )
        }

        composable(route = Screen.Results.route) {
            ResultsScreen()
        }
    }
}
