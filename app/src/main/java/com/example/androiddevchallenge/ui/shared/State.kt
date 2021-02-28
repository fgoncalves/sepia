package com.example.androiddevchallenge.ui.shared

sealed class PuppyDetailsScreenState {
    data class Header (
        val name: String = "",
        val avatar: String = "",
        val adoptionState: String? = null,
    ) : PuppyDetailsScreenState()

    data class Rest(
        val puppyId: String = "",
        val association: String = "",
        val weightKg: Int = 0,
        val ageMonths: Int = 0,
        val breed: String = "",
        val gender: String = "",
        val specialNeeds: String = "",
        val neutered: Boolean = false,
    ) : PuppyDetailsScreenState()
}

typealias PuppyDetailsHeaderState = PuppyDetailsScreenState.Header