package com.example.starwarsexplorer.data.remote.model

data class VehicleResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<VehicleDto>
)