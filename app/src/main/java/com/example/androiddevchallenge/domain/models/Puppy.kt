package com.example.androiddevchallenge.domain.models

data class Puppy(
    val id: String = "",
    val name: String = "",
    val avatar: String = "",
    val association: String = "",
    val weightKg: Int = 0,
    val ageMonths: Int = 0,
    val breed: String = "",
    val gender: String = "",
    val specialNeeds: String = "",
    val neutered: Boolean = false,
    val adoptionState: String? = null,
)