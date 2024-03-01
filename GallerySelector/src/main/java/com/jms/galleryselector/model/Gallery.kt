package com.jms.galleryselector.model

import android.net.Uri
import com.jms.galleryselector.Constants

internal data class ImageEntity(
    val id: Long,
    val title: String,
    val dateAt: Long,
    val data: String,
    val uri: Uri,
    val mimeType: String,
    val album: String
)


sealed interface Gallery {

    data class Image(
        val id: Long,
        val title: String,
        val dateAt: Long,
        val data: String,
        val uri: Uri,
        val mimeType: String,
        val album: String,
        val selectedOrder: Int = Constants.NO_ORDER
    ) : Gallery
}


internal fun ImageEntity.toImage(): Gallery.Image {
    return Gallery.Image(
        id = id,
        title = title,
        dateAt = dateAt,
        data = data,
        uri = uri,
        mimeType = mimeType,
        album = album
    )
}