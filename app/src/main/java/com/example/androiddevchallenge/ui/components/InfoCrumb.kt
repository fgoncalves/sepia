package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.Size

data class InfoCrumbState(
    val text: String = "",
    val icon: Painter? = null,
)

/**
 * Part of the info label that describes some details about the puppy, i.e,
 * 12 years.
 *
 * @param infoCrumb The info to display in this crumb. Must contain text and an optional icon.
 */
@Preview(showBackground = true)
@Composable
fun InfoCrumb(
    infoCrumb: InfoCrumbState = InfoCrumbState(
        "12 years",
        painterResource(id = R.drawable.ic_date_range),
    ),
) {
    if (infoCrumb.icon != null) {
        InfoCrumb(infoCrumb.icon, infoCrumb.text)
    } else {
        InfoCrumb(infoCrumb.text)
    }
}

@Composable
private fun InfoCrumb(icon: Painter, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .width(Size.icon)
                .height(Size.icon),
        )

        Spacer(modifier = Modifier.width(Size.micro))

        InfoText(text)
    }
}

@Composable
private fun InfoCrumb(text: String) {
    InfoText(text)
}