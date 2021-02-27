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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.androiddevchallenge.R

@Composable
private fun darkColorPalette() = darkColors(
    primary = colorResource(id = R.color.white),
    primaryVariant = colorResource(id = R.color.light_grey),
    secondary = colorResource(id = R.color.sea_green),
    secondaryVariant = colorResource(id = R.color.turquoise),
    background = colorResource(id = R.color.black),
    surface = colorResource(id = R.color.black),
    error = colorResource(id = R.color.firebrick),
    onPrimary = colorResource(id = R.color.light_grey),
    onSecondary = colorResource(id = R.color.black),
    onBackground = colorResource(id = R.color.white),
    onSurface = colorResource(id = R.color.white),
    onError = colorResource(id = R.color.white),
)

@Composable
private fun lightColorPalette() = lightColors(
    primary = colorResource(id = R.color.black),
    primaryVariant = colorResource(id = R.color.dark_grey),
    secondary = colorResource(id = R.color.red),
    secondaryVariant = colorResource(id = R.color.dark_red),
    background = colorResource(id = R.color.white),
    surface = colorResource(id = R.color.white),
    error = colorResource(id = R.color.firebrick),
    onPrimary = colorResource(id = R.color.dark_grey),
    onSecondary = colorResource(id = R.color.white),
    onBackground = colorResource(id = R.color.black),
    onSurface = colorResource(id = R.color.light_grey),
    onError = colorResource(id = R.color.white),
)

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkColorPalette()
    } else {
        lightColorPalette()
    }

    MaterialTheme(
        colors = colors,
        typography = typography(colors),
        shapes = shapes,
        content = content
    )
}
