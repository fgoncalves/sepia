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
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.Size

@Preview(showBackground = true)
@Composable
fun InfoLabel(
    modifier: Modifier = Modifier,
    crumbs: List<InfoCrumbState> = listOf(
        InfoCrumbState(
            "12 years",
            painterResource(id = R.drawable.ic_date_range),
        ),
        InfoCrumbState(
            "12 years",
            painterResource(id = R.drawable.ic_date_range),
        ),
        InfoCrumbState(
            "12 years",
            painterResource(id = R.drawable.ic_date_range),
        ),
    )
) {
    SimpleFlowRow(modifier = modifier) {
        crumbs.forEachIndexed { index, infoCrumbState ->
            InfoCrumb(infoCrumbState)

            if (index != crumbs.lastIndex)
                Separator()
        }
    }
}

@Composable
private fun Separator() {
    InfoText(
        text = "·",
        modifier = Modifier.padding(horizontal = Size.xsmall),
    )
}
