package com.example.androiddevchallenge.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.components.PuppyCardState
import com.example.androiddevchallenge.ui.components.PuppyList
import com.example.androiddevchallenge.ui.components.TopBar
import com.example.androiddevchallenge.ui.navigation.Destination

private val demo = PuppyCardState(
    name = "Sepia",
    avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJN58dEs9l-pGNpZTd53W__gw0sJtd-o78JQ&usqp=CAU",
    association = "casa del dogo",
    weightKg = 30,
    ageMonths = 32,
    breed = "Pointer",
    gender = "Female",
)

@Composable
fun PuppyListScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = { TopBar(title = stringResource(id = R.string.app_name)) },
    ) {
        PuppyList(
            puppies = listOf(
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
                demo,
            ),
            onItemClicked = {
                navController.navigate(
                    Destination.Puppy.with("foo")
                )
            }
        )
    }
}