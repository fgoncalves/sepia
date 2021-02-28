package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.models.Puppy
import com.example.androiddevchallenge.ui.components.*
import com.example.androiddevchallenge.ui.shared.*
import com.example.androiddevchallenge.ui.theme.Size

fun fake(id: String) = Puppy(
    id = id,
    name = "Sepia",
    avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJN58dEs9l-pGNpZTd53W__gw0sJtd-o78JQ&usqp=CAU",
    association = "casa del dogo",
    adoptionState = "Adopted",
    bio = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form...",
    weightKg = 23,
    ageMonths = 32,
    breed = "Strong Perro",
    gender = "Male",
    specialNeeds = "Requires a lot of love",
    neutered = true,
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
    item: PuppyDetailsScreenState,
) {
    when (item) {
        is PuppyDetailsHeaderState -> PuppyDetailsHeader(
            state = PuppyDetailsScreenState.Header(
                name = item.name,
                avatar = item.avatar,
                adoptionState = item.adoptionState,
            )
        )

        is PuppyDetailsAssociationState -> PetAssociation(
            name = item.association,
            modifier = Modifier.padding(Size.large),
        )

        is PuppyDetailsBioState -> Bio(
            text = item.bio,
            modifier = Modifier.padding(vertical = Size.small, horizontal = Size.large),
        )

        is PuppyDetailsInfoState -> PuppyInfo(
            modifier = Modifier.padding(vertical = Size.small, horizontal = Size.large),
            contentPadding = PaddingValues(top = Size.micro, bottom = Size.micro),
            state = PuppyInfoState(
                weightInKg = item.weightKg,
                ageInMonths = item.ageMonths,
                breed = item.breed,
                gender = item.gender,
                specialNeeds = item.specialNeeds,
                neutered = item.neutered,
            )
        )

        else -> throw IllegalArgumentException("Cannot adapt model $item. Don't know what composable to pick.")
    }
}

@Composable
private fun NavigateUpIcon(navController: NavHostController) {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_back),
        contentDescription = null,
        modifier = Modifier
            .padding(Size.medium)
            .clip(CircleShape)
            .clickable {
                navController.navigateUp()
            },
        tint = MaterialTheme.colors.primary,
    )
}

@Composable
private fun itemsFor(puppy: Puppy) =
    listOf(
        PuppyDetailsHeaderState(
            name = puppy.name,
            avatar = puppy.avatar,
            adoptionState = puppy.adoptionState,
        ),
        PuppyDetailsAssociationState(
            association = puppy.association,
        ),
        PuppyDetailsBioState(
            bio = puppy.bio,
        ),
        PuppyDetailsInfoState(
            weightKg = puppy.weightKg,
            ageMonths = puppy.ageMonths,
            breed = puppy.breed,
            gender = puppy.gender,
            specialNeeds = puppy.specialNeeds,
            neutered = puppy.neutered,
        )
    )
