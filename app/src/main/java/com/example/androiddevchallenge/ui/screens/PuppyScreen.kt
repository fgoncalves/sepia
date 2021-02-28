package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.models.Puppy
import com.example.androiddevchallenge.ui.components.PuppyDetailsHeader
import com.example.androiddevchallenge.ui.components.TopBar
import com.example.androiddevchallenge.ui.shared.PuppyDetailsHeaderState
import com.example.androiddevchallenge.ui.shared.PuppyDetailsScreenState
import com.example.androiddevchallenge.ui.theme.Size

fun fake(id: String) = Puppy(
    id = id,
    name = "Sepia",
    avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJN58dEs9l-pGNpZTd53W__gw0sJtd-o78JQ&usqp=CAU",
    association = "casa del dogo",
    weightKg = 30,
    ageMonths = 32,
    breed = "Pointer",
    gender = "Female",
    adoptionState = "Adopted"
)


@Composable
fun PuppyScreen(
    navController: NavHostController,

    // Can this be made a state?
    puppyId: String = "",
) {
    Scaffold(
        topBar = {
            TopBar {
                NavigateUpIcon(navController)
            }
        },
    ) {
        val puppy = fake(puppyId)

        val listItems = itemsFor(puppy)

        LazyColumn {
            items(listItems) {
                ComposableFor(it)
            }
        }
    }
}

@Composable
private fun ComposableFor(
    puppy: PuppyDetailsScreenState,
) {
    when (puppy) {
        is PuppyDetailsHeaderState -> PuppyDetailsHeader(
            state = PuppyDetailsScreenState.Header(
                name = puppy.name,
                avatar = puppy.avatar,
                adoptionState = puppy.adoptionState,
            )
        )

        else -> throw IllegalArgumentException("Cannot adapt model $puppy. Don't know what composable to pick.")
    }
}

@Composable
private fun NavigateUpIcon(navController: NavHostController) {
    Image(
        painter = painterResource(id = R.drawable.ic_arrow_back),
        contentDescription = null,
        modifier = Modifier
            .padding(Size.medium)
            .clip(CircleShape)
            .clickable {
                navController.navigateUp()
            },
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun itemsFor(puppy: Puppy) =
    listOf<PuppyDetailsScreenState>(
        PuppyDetailsHeaderState(
            name = puppy.name,
            avatar = puppy.avatar,
            adoptionState = puppy.adoptionState,
        )
    )
