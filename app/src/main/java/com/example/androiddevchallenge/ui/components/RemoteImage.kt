package com.example.androiddevchallenge.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Preview(showBackground = true)
@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    url: String = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJN58dEs9l-pGNpZTd53W__gw0sJtd-o78JQ&usqp=CAU",
    @DrawableRes
    placeholder: Int = 0,
) {
    val painterState by painterState(placeholder, url)

    Image(
        painter = painterState,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun painterState(
    placeholder: Int,
    url: String
): MutableState<Painter> {
    val painterState = mutableStateOf(painterResource(id = placeholder))

    Glide.with(LocalContext.current)
        .asBitmap()
        .placeholder(placeholder)
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                println("BANANA got resource $resource")

                painterState.value = BitmapPainter(resource.asImageBitmap())
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                if (placeholder != null) {
                    painterState.value = BitmapPainter(placeholder.toBitmap().asImageBitmap())
                }
            }
        })

    return painterState
}