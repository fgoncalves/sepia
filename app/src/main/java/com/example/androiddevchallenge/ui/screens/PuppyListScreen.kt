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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.getAllPuppies
import com.example.androiddevchallenge.domain.models.Puppy
import com.example.androiddevchallenge.ui.components.PuppyCardState
import com.example.androiddevchallenge.ui.components.PuppyList
import com.example.androiddevchallenge.ui.components.TopBar
import com.example.androiddevchallenge.ui.navigation.Destination
import com.example.androiddevchallenge.ui.theme.Size
import com.example.androiddevchallenge.ui.utils.formattedAge

@Composable
fun PuppyListScreen(
    navController: NavController,
) {
    var searching by remember { mutableStateOf(false) }
    var searchTerm by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            PuppyListTopBar(
                searching = searching,
                onSearchClicked = { searching = true },
                onSearchTermChanged = { searchTerm = it }
            )
        },
    ) {
        PuppyList(
            puppies = getAllPuppies()
                .filter { filterPuppies(it, searchTerm) }
                .map {
                    PuppyCardState(
                        id = it.id,
                        name = it.name,
                        avatar = it.avatar,
                        association = it.association,
                        weightKg = it.weightKg,
                        ageMonths = it.ageMonths,
                        breed = it.breed,
                        gender = it.gender,
                        adoptionState = it.adoptionState,
                    )
                },
            onItemClicked = {
                navController.navigate(
                    Destination.Puppy.with(it.id)
                )
            }
        )
    }
}

@Composable
private fun filterPuppies(puppy: Puppy, searchTerm: String): Boolean {
    if (searchTerm.isBlank()) return true

    // very basic search. Split into tokens and search for all of them
    val terms = searchTerm.split(" ")

    return terms.all {
        val age = formattedAge(ageInMonths = puppy.ageMonths)

        puppy.name.contains(it, ignoreCase = true) ||
            puppy.gender.contains(it, ignoreCase = true) ||
            puppy.breed.contains(it, ignoreCase = true) ||
            age.contains(it, ignoreCase = true)
    }
}

@Composable
private fun PuppyListTopBar(
    searching: Boolean = false,
    onSearchClicked: () -> Unit = {},
    onSearchTermChanged: (String) -> Unit = {},
) {
    var searchTerm by remember { mutableStateOf("") }

    if (searching)
        TextField(
            value = searchTerm,
            onValueChange = {
                searchTerm = it
                onSearchTermChanged(it)
            },
            label = {
                Text(
                    text = stringResource(id = R.string.search_things),
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onPrimary
                )
            },
            modifier = Modifier
                .padding(Size.medium)
                .fillMaxWidth(),
        )
    else
        TopBar(
            title = stringResource(id = R.string.app_name),
            actions = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = Size.medium)
                        .clip(CircleShape)
                        .clickable {
                            onSearchClicked()
                        },
                    tint = MaterialTheme.colors.primary,
                )
            }
        )
}
