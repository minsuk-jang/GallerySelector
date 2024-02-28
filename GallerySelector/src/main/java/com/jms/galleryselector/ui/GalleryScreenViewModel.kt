package com.jms.galleryselector.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jms.galleryselector.data.LocalGalleryDataSource

internal class GalleryScreenViewModel constructor(
    private val localGalleryDataSource: LocalGalleryDataSource
) : ViewModel() {

    val images = localGalleryDataSource.getLocalGalleryImages(page = 1)
        .cachedIn(viewModelScope)
}