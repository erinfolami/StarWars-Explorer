package com.example.starwarsexplorer.data.remote.model

data class StarshipResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<StarshipDto>
)