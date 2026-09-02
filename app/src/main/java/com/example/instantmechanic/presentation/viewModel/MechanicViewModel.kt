package com.example.instantmechanic.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.instantmechanic.data.repository.MechanicRepository
import com.example.instantmechanic.domain.model.Mechanic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MechanicViewModel : ViewModel() {
    private val repository = MechanicRepository()

    private val _mechanics = MutableStateFlow<List<Mechanic>>(emptyList())
    val mechanics: StateFlow<List<Mechanic>> = _mechanics

    init {
        loadMechanics()
    }

    private fun loadMechanics() {
        _mechanics.value = repository.getMechanics()
    }
}