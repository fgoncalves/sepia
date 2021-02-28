package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.Size

@Preview(showBackground = true)
@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    url: String = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJN58dEs9l-pGNpZTd53W__gw0sJtd-o78JQ&usqp=CAU"
) {
    val shape = CircleShape

    Column(modifier = modifier) {
        RemoteImage(
            modifier = Modifier
                .size(Size.avatar)
                .clip(shape)
                .border(
                    BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colors.onPrimary,
                    ),
                    shape = shape,
                ),
            url = url,
        )
    }
}