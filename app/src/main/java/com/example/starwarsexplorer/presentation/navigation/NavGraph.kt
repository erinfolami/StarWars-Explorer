package com.example.starwarsexplorer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.starwarsexplorer.presentation.shared.SharedViewModel
import com.example.starwarsexplorer.presentation.ui.results.ResultsScreen
import com.example.starwarsexplorer.presentation.ui.search.SearchScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Search.route,
        modifier = Modifier,
        route = "root_graph"
    ) {

        composable(route = Screen.Search.route) { backStackEntry ->
            // Scope ViewModel to NavGraph
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("root_graph")
            }
            val sharedViewModel: SharedViewModel = hiltViewModel(parentEntry)
            SearchScreen(
                sharedViewModel = sharedViewModel,
                onViewLastResults = { navController.navigate(Screen.Results.route) },
                onNavigateToResultsScreen = { navController.navigate(Screen.Results.route) },
            )
        }

        composable(route = Screen.Results.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("root_graph")
            }
            val sharedViewModel: SharedViewModel = hiltViewModel(parentEntry)

            ResultsScreen(sharedViewModel = sharedViewModel,navController = navController)
        }
    }
}
