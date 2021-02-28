package com.example.androiddevchallenge.ui.shared

sealed class PuppyDetailsScreenState {
    data class Header(
        val name: String = "",
        val avatar: String = "",
        val adoptionState: String? = null,
    ) : PuppyDetailsScreenState()

    data class Association(
        val association: String = "",
    ) : PuppyDetailsScreenState()

    data class Bio(
        val bio: String = "",
    ) : PuppyDetailsScreenState()

    data class Info(
        val weightKg: Int = 0,
        val ageMonths: Int = 0,
        val breed: String = "",
        val gender: String = "",
        val specialNeeds: String = "",
        val neutered: Boolean = false,
    ) : PuppyDetailsScreenState()

    data class Rest(
        val puppyId: String = "",
    ) : PuppyDetailsScreenState()
}

typealias PuppyDetailsHeaderState = PuppyDetailsScreenState.Header
typealias PuppyDetailsAssociationState = PuppyDetailsScreenState.Association
typealias PuppyDetailsBioState = PuppyDetailsScreenState.Bio
typealias PuppyDetailsInfoState = PuppyDetailsScreenState.Info