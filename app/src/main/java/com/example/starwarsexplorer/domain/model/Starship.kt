package com.example.starwarsexplorer.domain.model

data class Starship(
    val name: String,
    val model: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val cargoCapacity: String
)