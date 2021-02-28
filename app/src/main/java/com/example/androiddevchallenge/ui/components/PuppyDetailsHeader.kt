package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.ui.shared.PuppyDetailsHeaderState
import com.example.androiddevchallenge.ui.theme.Size


@Preview(showBackground = true)
@Composable
fun PuppyDetailsHeader(
    state: PuppyDetailsHeaderState = PuppyDetailsHeaderState(),
) {
    ConstraintLayout(
        constraintSet = headerConstraintSet(),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {

        RemoteImage(
            modifier = Modifier.layoutId(AVATAR_LAYOUT_ID),
            url = state.avatar,
        )

        Text(
            text = state.name,
            style = MaterialTheme.typography.h5.copy(
                color = Color.White,
            ),
            modifier = Modifier
                .layoutId(NAME_LAYOUT_ID)
                .padding(start = Size.large)
                // How can I pin this to the theme?
                .background(color = Color.Black)
                .padding(horizontal = Size.medium, vertical = Size.xsmall),
        )

        if (state.adoptionState != null)
            Text(
                text = state.adoptionState,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.secondary,
                    background = Color.Black,
                ),
                modifier = Modifier
                    .layoutId(ADOPTION_STATE_LAYOUT_ID)
                    // How can I pin this to the theme?
                    .padding(start = Size.large, bottom = Size.large)
                    .background(color = Color.Black)
                    .padding(horizontal = Size.medium, vertical = Size.xsmall),
            )
    }
}

private const val AVATAR_LAYOUT_ID = "avatar"
private const val NAME_LAYOUT_ID = "name"
private const val ADOPTION_STATE_LAYOUT_ID = "adoptionState"

private fun headerConstraintSet() =
    ConstraintSet {
        val avatar = createRefFor(AVATAR_LAYOUT_ID)
        val name = createRefFor(NAME_LAYOUT_ID)
        val adoptionState = createRefFor(ADOPTION_STATE_LAYOUT_ID)

        constrain(avatar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)

            width = Dimension.fillToConstraints
            height = Dimension.value(300.dp)
        }

        constrain(adoptionState) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
        }

        constrain(name) {
            bottom.linkTo(adoptionState.top)
            start.linkTo(parent.start)
        }
    }