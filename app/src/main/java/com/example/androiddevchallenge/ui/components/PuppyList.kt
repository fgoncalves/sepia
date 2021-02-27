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
                        top = if (index == 0 || index == puppies.lastIndex) 0.dp else Size.small
                    )
                    .clickable { onItemClicked(item) },
                puppy = item,
            )
        }
    }
}