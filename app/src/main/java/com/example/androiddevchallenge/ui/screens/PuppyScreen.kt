package com.example.androiddevchallenge.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.getPuppy
import com.example.androiddevchallenge.data.updatePuppy
import com.example.androiddevchallenge.domain.models.Puppy
import com.example.androiddevchallenge.ui.components.*
import com.example.androiddevchallenge.ui.shared.*
import com.example.androiddevchallenge.ui.theme.Size


@Composable
fun PuppyScreen(
    navController: NavHostController,
    puppyId: String = "",
) {
    var puppy by remember { mutableStateOf(getPuppy(puppyId)!!) }

    val adoptionString = stringResource(id = R.string.adoption_requested)

    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.details)) {
                NavigateUpIcon(navController)
            }
        },
        floatingActionButton = {
            AdoptionFab(!puppy.adoptionState.isNullOrBlank()) {
                puppy = puppy.copy(
                    adoptionState = if (puppy.adoptionState == null)
                        adoptionString
                    else
                        null
                )

                updatePuppy(puppy)
            }
        }
    ) {
        val listItems = itemsFor(puppy)

        LazyColumn(
            contentPadding = PaddingValues(bottom = 100.dp),
        ) {
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

        is PuppyDetailsBioState ->
            Bio(
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

@Composable
fun AdoptionFab(
    isAdopted: Boolean = false,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.secondary,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = Size.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = if (isAdopted)
                    painterResource(id = R.drawable.ic_favorite)
                else
                    painterResource(id = R.drawable.ic_favorite_border),
                contentDescription = null,
                modifier = Modifier.padding(end = Size.small),
                tint = Color.White,
            )

            Text(
                text = if (isAdopted)
                    stringResource(id = R.string.adoption_requested)
                else
                    stringResource(id = R.string.request_adoption),
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.White,
                ),
                modifier = Modifier.animateContentSize()
            )
        }
    }
}