package com.example.starwarsexplorer.data.remote.model

data class FilmResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<FilmDto>
)