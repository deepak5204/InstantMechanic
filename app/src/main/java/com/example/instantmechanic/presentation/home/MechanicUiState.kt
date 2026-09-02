package com.example.instantmechanic.presentation.home

import com.example.instantmechanic.domain.model.Mechanic

sealed class MechanicUiState {
    data object Loading: MechanicUiState()
    data class Success(val mechanics: List<Mechanic>): MechanicUiState()
    data class Error(val message: String): MechanicUiState()
}