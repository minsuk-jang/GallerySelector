package com.jms.galleryselector.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jms.galleryselector.model.Image


/**
 *
 * Gallery cell
 */
@Composable
internal fun ImageCell(
    modifier: Modifier = Modifier,
    image: Image
) {
    AsyncImage(
        modifier = modifier.aspectRatio(1f),
        model = ImageRequest.Builder(LocalContext.current)
            .crossfade(true)
            .allowHardware(true)
            .data(image.uri)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        filterQuality = FilterQuality.None,
    )
}
