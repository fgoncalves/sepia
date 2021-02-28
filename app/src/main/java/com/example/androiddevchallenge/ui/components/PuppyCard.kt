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
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.Size
import com.example.androiddevchallenge.ui.utils.formattedAge

data class PuppyCardState(
    val id: String = "",
    val name: String = "",
    val avatar: String = "",
    val association: String = "",
    val weightKg: Int = 0,
    val ageMonths: Int = 0,
    val breed: String = "",
    val gender: String = "",
    val adoptionState: String? = null,
)

@Preview(showBackground = true)
@Composable
fun PuppyCard(
    modifier: Modifier = Modifier,
    puppy: PuppyCardState = PuppyCardState(
        name = "Sepia",
        avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJN58dEs9l-pGNpZTd53W__gw0sJtd-o78JQ&usqp=CAU",
        association = "casa del dogo",
        weightKg = 30,
        ageMonths = 32,
        breed = "Pointer",
        gender = "Female",
        adoptionState = "adoption requested",
    )
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 4.dp,
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(Size.medium),
            constraintSet = constraintSet(),
        ) {
            Avatar(
                url = puppy.avatar,
                modifier = Modifier
                    .layoutId(AVATAR_LAYOUT_ID),
            )

            Name(
                text = puppy.name,
                modifier = Modifier
                    .layoutId(NAME_LAYOUT_ID)
                    .padding(start = Size.small),
            )

            PetAssociation(
                name = puppy.association,
                modifier = Modifier
                    .layoutId(ASSOCIATION_LAYOUT_ID)
                    .padding(start = Size.small),
            )

            InfoLabel(
                crumbs = infoCrumbs(puppy = puppy),
                modifier = Modifier
                    .layoutId(INFO_LABEL_LAYOUT_ID)
                    .padding(start = Size.small, top = Size.medium),
            )

            if (puppy.adoptionState != null)
                Text(
                    text = puppy.adoptionState,
                    modifier = Modifier
                        .layoutId(ADOPTION_STATE_LAYOUT_ID)
                        .padding(start = Size.small, top = Size.small),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary,
                )
        }
    }
}

private const val AVATAR_LAYOUT_ID = "avatar"
private const val NAME_LAYOUT_ID = "name"
private const val ASSOCIATION_LAYOUT_ID = "association"
private const val INFO_LABEL_LAYOUT_ID = "infoLabel"
private const val ADOPTION_STATE_LAYOUT_ID = "adoptionState"

private fun constraintSet() =
    ConstraintSet {
        val avatar = createRefFor(AVATAR_LAYOUT_ID)
        val name = createRefFor(NAME_LAYOUT_ID)
        val association = createRefFor(ASSOCIATION_LAYOUT_ID)
        val infoLabel = createRefFor(INFO_LABEL_LAYOUT_ID)
        val adoptionState = createRefFor(ADOPTION_STATE_LAYOUT_ID)

        constrain(avatar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
        }

        constrain(name) {
            top.linkTo(avatar.top)
            bottom.linkTo(association.top)
            start.linkTo(avatar.end)
            end.linkTo(parent.end)

            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(association) {
            top.linkTo(name.bottom)
            bottom.linkTo(infoLabel.top)
            start.linkTo(avatar.end)
            end.linkTo(parent.end)

            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(infoLabel) {
            top.linkTo(association.bottom)
            bottom.linkTo(adoptionState.top)
            start.linkTo(avatar.end)
            end.linkTo(parent.end)

            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(adoptionState) {
            top.linkTo(infoLabel.bottom)
            bottom.linkTo(avatar.bottom)
            start.linkTo(avatar.end)
            end.linkTo(parent.end)

            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        createVerticalChain(
            name, association, infoLabel, adoptionState,
            chainStyle = ChainStyle.Packed,
        )
    }

@Composable
private fun infoCrumbs(puppy: PuppyCardState) =
    listOf(
        InfoCrumbState(
            icon = painterResource(id = R.drawable.ic_date_range),
            text = formattedAge(ageInMonths = puppy.ageMonths),
        ),
        InfoCrumbState(
            icon = painterResource(id = R.drawable.ic_paw),
            text = puppy.breed,
        ),
        InfoCrumbState(
            icon = painterResource(id = R.drawable.ic_monitor_weight),
            text = stringResource(id = R.string.weight, puppy.weightKg),
        ),
        InfoCrumbState(
            text = puppy.gender,
        ),
    )
