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
package com.example.androiddevchallenge.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument

sealed class Destination {
    abstract val route: String

    object Puppies : Destination() {
        override val route: String = "puppies"
    }

    object Puppy : Destination() {
        private const val BASE_ROUTE = "puppies"
        private const val PUPPY_ID_ARG = "puppy_id"

        override val route: String = "$BASE_ROUTE/{puppy_id}"

        val arguments = listOf(
            navArgument(PUPPY_ID_ARG) {
                nullable = false
                type = NavType.StringType
            }
        )

        // TODO: How can this be done better?
        fun with(puppyId: String) = "$BASE_ROUTE/$puppyId"

        fun from(arguments: Bundle): String =
            arguments.getString(PUPPY_ID_ARG)!!
    }
}
