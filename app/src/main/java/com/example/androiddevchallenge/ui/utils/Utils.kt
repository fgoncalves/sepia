package com.example.androiddevchallenge.ui.utils

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.androiddevchallenge.R

@Composable
fun quantityStringResource(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String {
    return LocalContext.current.resources.getQuantityString(id, quantity, *formatArgs)
}

@Composable
fun formattedAge(ageInMonths: Int) =
    if (ageInMonths < 12)
        quantityStringResource(R.plurals.age_months, ageInMonths, ageInMonths)
    else {
        val years = ageInMonths / 12
        quantityStringResource(R.plurals.age_years, years, years)
    }
