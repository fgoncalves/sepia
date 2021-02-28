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
package com.example.androiddevchallenge.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.androiddevchallenge.ui.components.Bio
import com.example.androiddevchallenge.ui.components.PetAssociation
import com.example.androiddevchallenge.ui.components.PuppyDetailsHeader
import com.example.androiddevchallenge.ui.components.PuppyInfo
import com.example.androiddevchallenge.ui.components.PuppyInfoState
import com.example.androiddevchallenge.ui.components.TopBar
import com.example.androiddevchallenge.ui.shared.PuppyDetailsAssociationState
import com.example.androiddevchallenge.ui.shared.PuppyDetailsBioState
import com.example.androiddevchallenge.ui.shared.PuppyDetailsHeaderState
import com.example.androiddevchallenge.ui.shared.PuppyDetailsInfoState
import com.example.androiddevchallenge.ui.shared.PuppyDetailsScreenState
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
            TopBar(
                title = stringResource(id = R.string.details),
                navigationIcon = { NavigateUpIcon(navController) },
            )
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
