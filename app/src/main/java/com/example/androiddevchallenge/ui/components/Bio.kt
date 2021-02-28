package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R

@Preview(showBackground = true)
@Composable
fun Bio(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    Column(modifier = modifier) {
        SectionHeader(headerTitle = stringResource(id = R.string.bio))

        Text(
            text = text,
            style = MaterialTheme.typography.body1
        )
    }
}