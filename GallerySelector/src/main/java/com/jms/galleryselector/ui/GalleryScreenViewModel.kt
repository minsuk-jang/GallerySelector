package com.jms.galleryselector.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.jms.galleryselector.data.LocalGalleryDataSource
import com.jms.galleryselector.model.Gallery
import com.jms.galleryselector.model.ImageEntity
import com.jms.galleryselector.model.PagingEvent
import com.jms.galleryselector.model.combinePagingEvent
import com.jms.galleryselector.model.toImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

internal class GalleryScreenViewModel constructor(
    private val localGalleryDataSource: LocalGalleryDataSource
) : ViewModel() {

    private val _events = MutableStateFlow<List<PagingEvent<Long>>>(mutableListOf())

    val images: Flow<PagingData<Gallery.Image>> =
        localGalleryDataSource.getLocalGalleryImages(page = 1)
            .map {
                it.map {
                    it.toImage()
                }
            }
            .cachedIn(viewModelScope)
            .combinePagingEvent(events = _events, update = ::update)

    fun select(image: Gallery.Image) {
        _events.update {
            it.toMutableList().apply {
                add(PagingEvent.Update(data = image.id))
            }
        }
    }

    private fun update(
        pagingData: PagingData<Gallery.Image>,
        event: PagingEvent.Update<Long>
    ): PagingData<Gallery.Image> {
        return pagingData.map {
            it.copy(
                isSelected = it.id == event.data
            )
        }
    }
}