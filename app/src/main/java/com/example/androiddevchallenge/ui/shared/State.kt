/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
