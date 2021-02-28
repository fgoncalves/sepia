package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.Size

@Preview(showBackground = true)
@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    headerTitle: String = "",
) {
    Column(modifier = modifier) {
        Text(
            text = headerTitle,
            style = MaterialTheme.typography.subtitle1
        )
        Divider(
            color = MaterialTheme.colors.onPrimary,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = Size.xsmall)
        )
    }
}