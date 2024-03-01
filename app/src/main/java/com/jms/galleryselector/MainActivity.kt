package com.jms.galleryselector

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.jms.galleryselector.ui.GalleryScreen
import com.jms.galleryselector.ui.theme.GallerySelectorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GallerySelectorTheme {
                // A surface container using the 'background' color from the theme
                val contracts =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) {
                        if (it.all { it.value }) {

                        }
                    }

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
                        GalleryScreen {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = "${it.selectedOrder}",
                                    modifier = Modifier.align(Alignment.TopEnd),
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}