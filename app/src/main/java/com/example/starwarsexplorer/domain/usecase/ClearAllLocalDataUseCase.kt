package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.repository.Repository
import javax.inject.Inject

class ClearAllLocalDataUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() {
        repository.clearAllLocalData()
    }
}
