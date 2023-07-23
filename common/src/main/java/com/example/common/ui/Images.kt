package com.example.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CircleImage (
    modifier: Modifier = Modifier,
    path :String ,
    size : Int = 42,
){

    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            model = ImageRequest.Builder(LocalContext.current)
                .data(path)
                .build(),
            /*placeholder = painterResource(id = com.nexta.resources.R.drawable.ic_nexta_n),*/
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
    }
}
