package com.jms.galleryselector.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.jms.galleryselector.component.ImageCell
import com.jms.galleryselector.data.GalleryPagingStream
import com.jms.galleryselector.data.LocalGalleryDataSource
import com.jms.galleryselector.model.Gallery
import com.jms.galleryselector.model.ImageEntity
import kotlinx.coroutines.Dispatchers

@Composable
fun GalleryScreen(
    selectFrame: @Composable () -> Unit
) {
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
        selectFrame = selectFrame,
        onClick = {
            viewModel.select(image = it)
        }
    )
}


@Composable
private fun GalleryScreen(
    selectFrame: @Composable () -> Unit,
    images: LazyPagingItems<Gallery.Image>,
    onClick: (Gallery.Image) -> Unit
) {
    when {
        images.itemCount > 0 -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(images.itemCount, key = { it }) {
                    images[it]?.let {
                        Box(
                            modifier = Modifier.clickable {
                                onClick(it)
                            }
                        ) {
                            ImageCell(image = it)
                        }

                        if (it.isSelected)
                            selectFrame()
                    }
                }
            }
        }
    }
}
}
}