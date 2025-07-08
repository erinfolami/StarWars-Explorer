package com.example.starwarsexplorer.domain.model

data class SearchResults(
    val films: List<Film> = emptyList(),
    val starships: List<Starship> = emptyList(),
    val vehicles: List<Vehicle> = emptyList()
)
