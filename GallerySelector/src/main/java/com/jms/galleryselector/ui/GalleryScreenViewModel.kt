package com.jms.galleryselector.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.jms.galleryselector.data.LocalGalleryDataSource
import com.jms.galleryselector.model.Image
import com.jms.galleryselector.model.PagingEvent
import com.jms.galleryselector.model.combinePagingEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class GalleryScreenViewModel constructor(
    private val localGalleryDataSource: LocalGalleryDataSource
) : ViewModel() {

    private val _events = MutableStateFlow<List<PagingEvent<Int>>>(mutableListOf())

    val images: Flow<PagingData<Image>> = localGalleryDataSource.getLocalGalleryImages(page = 1)
        .cachedIn(viewModelScope)
        .combinePagingEvent(events = _events, update = ::update)


    private fun update(
        pagingData: PagingData<Image>,
        event: PagingEvent.Update<Int>
    ): PagingData<Image> {
        return pagingData.map {
            it
        }
    }
}