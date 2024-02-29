package com.jms.galleryselector.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine


internal sealed interface PagingEvent<D : Any> {
    data class Update<D : Any>(val data: D) : PagingEvent<D>
}


internal fun <T : Any, D : Any> Flow<PagingData<T>>.combinePagingEvent(
    events: Flow<List<PagingEvent<D>>>,
    update: (PagingData<T>, PagingEvent.Update<D>) -> PagingData<T> = { d, _ -> d }
): Flow<PagingData<T>> {
    return combine(this, events) { data, _events ->
        _events.fold(data) { d, e ->
            applyPagingEvent(pagingData = d, event = e, update = update)
        }
    }
}

private fun <T : Any, D: Any> applyPagingEvent(
    pagingData: PagingData<T>,
    event: PagingEvent<D>,
    update: (PagingData<T>, PagingEvent.Update<D>) -> PagingData<T> = { d, _ -> d }
): PagingData<T> {
    return when (event) {
        is PagingEvent.Update -> {
            update(pagingData, event)
        }
    }
}