package com.jms.galleryselector

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.jms.galleryselector.ui.GalleryScreen
import com.jms.galleryselector.ui.rememberGalleryState
import com.jms.galleryselector.ui.theme.GallerySelectorTheme
import com.jms.galleryselector.ui.theme.Purple40

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GallerySelectorTheme(
                darkTheme = false
            ) {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val arrays = if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
                        arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
                    } else
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

                    if (ContextCompat.checkSelfPermission(
                            this,
                            arrays[0]
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val state = rememberGalleryState(
                            max = 3,
                            autoSelectAfterCapture = true
                        )

                        val albums = state.albums.value
                        var selectedAlbum by state.selectedAlbum

                        var expand by remember {
                            mutableStateOf(false)
                        }

                        Column {
                            Text(
                                modifier = Modifier
                                    .height(56.dp)
                                    .clickable {
                                        expand = true
                                    },
                                text = "${selectedAlbum.name} | ${selectedAlbum.count}",
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            DropdownMenu(
                                modifier = Modifier.wrapContentSize(),
                                expanded = expand, onDismissRequest = { /*TODO*/ }) {
                                albums.forEach {
                                    DropdownMenuItem(
                                        text = {
                                            Text(text = it.name)
                                        },
                                        onClick = {
                                            selectedAlbum = it
                                            expand = false
                                        }
                                    )
                                }
                            }

                            GalleryScreen(
                                album = selectedAlbum,
                                state = state,
                                content = {
                                    if (it.selected) {
                                        Box(
                                            modifier = Modifier
                                                .border(width = 3.5.dp, color = Purple40)
                                                .background(color = Gray.copy(0.5f))
                                                .fillMaxSize()
                                        ) {
                                            Text(
                                                modifier = Modifier
                                                    .background(
                                                        color = Purple40,
                                                        shape = CircleShape
                                                    )
                                                    .size(25.dp)
                                                    .align(Alignment.TopEnd),
                                                text = "${it.selectedOrder + 1}",
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}