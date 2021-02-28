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

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.Size
import com.example.androiddevchallenge.ui.utils.DEFAULT_ANIMATION_DURATION

private const val EXPAND_ANIMATION_DURATION = 200

@Preview(showBackground = true)
@Composable
fun Bio(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    var expanded by remember { mutableStateOf(false) }

    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState)
    val maxLines by transition.animateInt({ tween(durationMillis = DEFAULT_ANIMATION_DURATION) }) {
        if (expanded)
            7
        else
            3
    }
    val rotation by transition.animateFloat({ tween(durationMillis = DEFAULT_ANIMATION_DURATION) }) {
        if (expanded)
            180f
        else
            0f
    }

    Column(modifier = modifier) {
        SectionHeader(headerTitle = stringResource(id = R.string.bio))

        ConstraintLayout(
            constraintSet = constraintSet(),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .layoutId(TEXT_LAYOUT_ID)
                    .clickable {
                        expanded = !expanded
                    }
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_expand_more),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .layoutId(CHEVRON_LAYOUT_ID)
                    .clip(CircleShape)
                    .clickable {
                        expanded = !expanded
                    }
                    .rotate(rotation)
            )
        }
    }
}

private const val TEXT_LAYOUT_ID = "text"
private const val CHEVRON_LAYOUT_ID = "chevron"

private fun constraintSet() =
    ConstraintSet {
        val text = createRefFor(TEXT_LAYOUT_ID)
        val chevron = createRefFor(CHEVRON_LAYOUT_ID)

        constrain(text) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            end.linkTo(chevron.start)

            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(chevron) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)

            width = Dimension.value(Size.xxlarge)
            height = Dimension.value(Size.xxlarge)
        }
    }
