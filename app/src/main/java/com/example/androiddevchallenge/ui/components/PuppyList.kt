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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.Size

@Preview(showBackground = true)
@Composable
fun PuppyList(
    modifier: Modifier = Modifier,
    puppies: List<PuppyCardState> = listOf(),
    onItemClicked: (PuppyCardState) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(Size.medium),
    ) {
        itemsIndexed(puppies) { index, item ->
            PuppyCard(
                modifier = Modifier
                    .padding(
                        top = if (index == 0) 0.dp else Size.xsmall,
                        bottom = if (index == puppies.lastIndex) 0.dp else Size.xsmall
                    )
                    .clickable { onItemClicked(item) },
                puppy = item,
            )
        }
    }
}
