package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import javax.inject.Inject


class GetVehiclesUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {
    suspend operator fun invoke(): List<Vehicle> {
        return repository.getVehicles()
    }
}