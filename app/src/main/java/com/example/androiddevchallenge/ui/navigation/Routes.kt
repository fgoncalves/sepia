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
