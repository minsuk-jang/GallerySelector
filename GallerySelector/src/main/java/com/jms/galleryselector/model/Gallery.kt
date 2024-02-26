package com.jms.galleryselector.model

import android.net.Uri

data class Image(
    val id: Long,
    val title: String,
    val dateAt: Long,
    val data: String,
    val uri: Uri,
    val mimeType: String,
    val album: String
)
