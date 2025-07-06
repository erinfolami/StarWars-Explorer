package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.repository.Repository
import com.example.starwarsexplorer.domain.util.Resource
import javax.inject.Inject

class GetStarshipsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Resource<List<Starship>> {
        return repository.getStarships()
    }
}