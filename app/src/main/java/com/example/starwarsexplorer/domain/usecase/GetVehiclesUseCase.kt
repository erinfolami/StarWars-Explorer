package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.model.Vehicle
import com.example.starwarsexplorer.domain.repository.Repository
import com.example.starwarsexplorer.domain.util.Resource
import javax.inject.Inject


class GetVehiclesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Resource<List<Vehicle>> {
        return repository.getVehicles()
    }
}