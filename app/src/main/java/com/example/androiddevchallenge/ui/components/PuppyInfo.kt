package com.example.androiddevchallenge.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.Size

data class PuppyInfoState(
    val ageInMonths: Int = 0,
    val weightInKg: Int = 0,
    val breed: String = "",
    val gender: String = "",
    val neutered: Boolean = false,
    val specialNeeds: String? = null,
)

@Preview(showBackground = true)
@Composable
fun PuppyInfo(
    modifier: Modifier = Modifier,
    state: PuppyInfoState = PuppyInfoState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val sectionModifier = Modifier.padding(contentPadding)

    Column(modifier = modifier) {
        SectionHeader(headerTitle = stringResource(id = R.string.details))

        // Age
        PuppyInfoSection(
            modifier = sectionModifier,
            iconRes = R.drawable.ic_date_range,
            text = "${state.ageInMonths} months",
        )

        // Weight
        PuppyInfoSection(
            modifier = sectionModifier,
            iconRes = R.drawable.ic_monitor_weight,
            text = "${state.weightInKg} Kg",
        )

        // Breed
        PuppyInfoSection(
            modifier = sectionModifier,
            iconRes = R.drawable.ic_paw,
            text = state.breed,
        )

        // Gender
        PuppyInfoSection(
            modifier = sectionModifier,
            iconRes = R.drawable.ic_spa,
            text = state.gender,
        )

        // Neutered Info
        PuppyInfoSection(
            modifier = sectionModifier,
            iconRes = R.drawable.ic_healing,
            text = if (state.neutered)
                stringResource(id = R.string.neutered)
            else
                stringResource(id = R.string.not_neutered),
        )

        if (state.specialNeeds != null)
            PuppyInfoSection(
                modifier = sectionModifier,
                iconRes = R.drawable.ic_accessible,
                text = state.specialNeeds,
            )
    }
}

@Composable
private fun PuppyInfoSection(
    modifier: Modifier = Modifier,
    @DrawableRes
    iconRes: Int = 0,
    text: String = "",
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.padding(end = Size.small),
            tint = MaterialTheme.colors.primary,
        )

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
        )
    }
}