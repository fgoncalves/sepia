package com.example.androiddevchallenge.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.androiddevchallenge.R

@Composable
fun topBar() =
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h5
            )
        },
    )