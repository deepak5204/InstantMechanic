package com.example.instantmechanic.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Mechanic(
    val id: Int,
    val garageName: String,
    val rating: Double,
    val ratingCount: String ?= null,
    val distance: String,
    val location: String,
    val services: List<MechanicService>,
    val isOpen: Boolean,
    val address: String,
    val workingHours: String,
    val phoneNumber: String
)

data class MechanicService(
    val name: String,
    val icon: ImageVector
)