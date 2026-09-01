package com.example.instantmechanic.domain.model

data class Mechanic(
    val id: Int,
    val garageName: String,
    val rating: Double,
    val distance: String,
    val location: String,
    val services: List<String>,
    val isOpen: Boolean,
    val address: String,
    val workingHours: String,
    val phoneNumber: String
)