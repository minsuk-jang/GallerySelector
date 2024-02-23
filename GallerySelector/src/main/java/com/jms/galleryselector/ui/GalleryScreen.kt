package com.jms.galleryselector.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.jms.galleryselector.data.GalleryPagingStream
import com.jms.galleryselector.data.LocalGalleryDataSource
import com.jms.galleryselector.model.Gallery
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
    images: LazyPagingItems<Gallery>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ){

    }
}