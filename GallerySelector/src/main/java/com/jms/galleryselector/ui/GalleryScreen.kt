package com.jms.galleryselector.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.jms.galleryselector.component.ImageCell
import com.jms.galleryselector.data.GalleryPagingStream
import com.jms.galleryselector.data.LocalGalleryDataSource
import com.jms.galleryselector.model.Image
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
        images = images,
    )
}


@Composable
private fun GalleryScreen(
    images: LazyPagingItems<Image>
) {
    when {
        images.itemCount > 0 -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(images.itemCount, key = { it }) {
                    images[it]?.let {
                        Box {
                            ImageCell(image = it)
                        }
                    }
                }
            }
        }
    }
}