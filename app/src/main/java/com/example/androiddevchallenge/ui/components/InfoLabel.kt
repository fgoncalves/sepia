package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.Size

@Preview(showBackground = true)
@Composable
fun InfoLabel(
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
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