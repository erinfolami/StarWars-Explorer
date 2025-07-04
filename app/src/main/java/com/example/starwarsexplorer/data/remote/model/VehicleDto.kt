package com.example.starwarsexplorer.data.remote.model

import com.google.gson.annotations.SerializedName

data class VehicleDto(
    val name: String,
    val model: String,
    val manufacturer: String,
    @SerializedName("cost_in_credits")
    val costInCredits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    @SerializedName("cargo_capacity")
    val cargoCapacity: String
)