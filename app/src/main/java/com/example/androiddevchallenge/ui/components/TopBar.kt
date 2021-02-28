package com.example.androiddevchallenge.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TopBar(
    title: String = "",
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Text(
                title,
                style = MaterialTheme.typography.h5
            )
        },
        navigationIcon = navigationIcon
    )
}