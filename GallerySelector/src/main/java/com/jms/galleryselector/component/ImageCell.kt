package com.jms.galleryselector.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.jms.galleryselector.model.Gallery
import com.jms.galleryselector.model.ImageEntity


/**
 *
 * Gallery cell
 */
@Composable
internal fun ImageCell(
    modifier: Modifier = Modifier,
    image: Gallery.Image
) {
    AsyncImage(
        modifier = modifier.aspectRatio(1f),
        model = ImageRequest.Builder(LocalContext.current)
            .crossfade(true)
            .allowHardware(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .data(image.uri)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        filterQuality = FilterQuality.None,
    )
}
