package com.example.instantmechanic.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instantmechanic.data.repository.MechanicRepository
import com.example.instantmechanic.presentation.home.MechanicUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MechanicViewModel @Inject constructor(
    private val repository: MechanicRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MechanicUiState>(MechanicUiState.Loading)
    val uiState: StateFlow<MechanicUiState> = _uiState

    init {
        loadMechanics()
    }

    private fun loadMechanics() {
        viewModelScope.launch {
            try {
                val mechanics = repository.getMechanics()

                _uiState.value = MechanicUiState.Success(mechanics)
            } catch (e: Exception) {
                _uiState.value = MechanicUiState.Error("Something went wrong")
            }
        }
    }

    fun retry() {
        loadMechanics()
    }

    fun isValidPhoneNumber(phone: String): Boolean {
        val trimmedPhone = phone.trim()

        // 1. Must be exactly 10 digits
        if (!trimmedPhone.matches("^[0-9]{10}$".toRegex())) {
            return false
        }

        // 2. Must not start with "00"
        if (trimmedPhone.startsWith("00")) {
            return false
        }

        // 3. Must not consist of the exact same repeating digit (e.g., 0000000000, 1111111111)
        if (trimmedPhone.all { it == trimmedPhone[0] }) {
            return false
        }

        return true
    }
}