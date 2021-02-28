package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.shared.PuppyDetailsHeaderState
import com.example.androiddevchallenge.ui.theme.Size
import com.example.androiddevchallenge.ui.utils.DEFAULT_ANIMATION_DURATION


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

        val transitionState = remember {
            MutableTransitionState(state.adoptionState.isNullOrBlank()).apply {
                targetState = !state.adoptionState.isNullOrBlank()
            }
        }
        val transition = updateTransition(transitionState)
        val alpha by transition.animateFloat({ tween(durationMillis = DEFAULT_ANIMATION_DURATION) }) {
            if (state.adoptionState.isNullOrBlank())
                0f
            else
                1f
        }

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
                .background(color = Color.Black)
                .padding(horizontal = Size.medium, vertical = Size.xsmall),
        )

        Text(
            text = stringResource(id = R.string.adoption_requested),
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.secondary,
            ),
            modifier = Modifier
                .alpha(alpha)
                .layoutId(ADOPTION_STATE_LAYOUT_ID)
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
            height = Dimension.value(Size.largeAvatar)
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