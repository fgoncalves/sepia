package com.example.androiddevchallenge.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun Name(text: String = "Sepia") {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
    )
}