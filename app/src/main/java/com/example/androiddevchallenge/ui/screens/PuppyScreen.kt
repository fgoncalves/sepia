package com.example.androiddevchallenge.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.components.topBar

@Composable
fun PuppyScreen(
    puppyId: String = "",
) {
    Scaffold(
        topBar = { topBar() },
    ) {
        Text(text = "Hello from puppy details")
    }
}