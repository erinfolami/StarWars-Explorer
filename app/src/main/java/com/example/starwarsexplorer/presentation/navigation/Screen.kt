package com.example.starwarsexplorer.presentation.navigation

sealed class Screen(val route: String) {
    object Search : Screen("search")
    object Results : Screen("results")
}