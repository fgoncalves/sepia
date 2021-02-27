package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.Size

@Preview(showBackground = true)
@Composable
fun PetAssociation(
    name: String = "Casa del dog",
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .width(Size.large)
                .height(Size.large),
        )

        Spacer(modifier = Modifier.width(Size.micro))

        Text(
            text = name,
            style = MaterialTheme.typography.subtitle1
        )
    }
}