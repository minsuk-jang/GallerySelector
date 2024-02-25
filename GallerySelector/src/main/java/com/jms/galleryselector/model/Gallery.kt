package com.jms.galleryselector.model

import android.net.Uri

data class GalleryImage(
    val id: Long,
    val title: String,
    val dateAt: Long,
    val data: String,
    val uri: Uri
)
