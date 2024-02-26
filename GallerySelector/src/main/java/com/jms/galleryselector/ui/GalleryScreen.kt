package com.jms.galleryselector.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.jms.galleryselector.data.GalleryPagingStream
import com.jms.galleryselector.data.LocalGalleryDataSource
import com.jms.galleryselector.model.GalleryImage
import kotlinx.coroutines.Dispatchers

@Composable
fun GalleryScreen() {
    val context = LocalContext.current
    val viewModel: GalleryScreenViewModel = viewModel {
        GalleryScreenViewModel(
            localGalleryDataSource = LocalGalleryDataSource(
                context = context,
                galleryStream = GalleryPagingStream()
            )
        )
    }

    val images = viewModel.images.collectAsLazyPagingItems(context = Dispatchers.Default)

    GalleryScreen(
        images = images
    )
}


@Composable
private fun GalleryScreen(
    images: LazyPagingItems<GalleryImage>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(images.itemCount, key = { it }) {
            images[it]?.let {
                AsyncImage(
                    modifier = Modifier.aspectRatio(1f),
                    model = ImageRequest.Builder(LocalContext.current)
                        .crossfade(true)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .data(it.uri)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}