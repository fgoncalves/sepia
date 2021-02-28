package com.example.androiddevchallenge.ui.screens

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

    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.details)) {
                NavigateUpIcon(navController)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    puppy = puppy.copy(
                        adoptionState = if (puppy.adoptionState == null)
                            "Adoption Requested"
                        else
                            null
                    )

                    updatePuppy(puppy)
                },
                backgroundColor = MaterialTheme.colors.secondary,
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = Size.medium),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = if (puppy.adoptionState == null)
                            painterResource(id = R.drawable.ic_favorite_border)
                        else
                            painterResource(id = R.drawable.ic_favorite),
                        contentDescription = null,
                        modifier = Modifier.padding(end = Size.small),
                        tint = MaterialTheme.colors.primary,
                    )

                    Text(
                        text = if (puppy.adoptionState == null)
                            stringResource(id = R.string.request_adoption)
                        else
                            stringResource(id = R.string.adoption_requested),
                        style = MaterialTheme.typography.subtitle1.copy(
                            color = MaterialTheme.colors.primary,
                        ),
                    )
                }
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
