package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.model.Film
import com.example.starwarsexplorer.domain.model.Starship
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import javax.inject.Inject


class GetFilmsUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {
    suspend operator fun invoke(): List<Film> {
        return repository.getFilms()
    }
}