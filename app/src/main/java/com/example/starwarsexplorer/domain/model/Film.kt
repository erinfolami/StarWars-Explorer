package com.example.starwarsexplorer.domain.model

data class Film(
    val title: String,
    val episodeId: Int,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val openingCrawl: String
)