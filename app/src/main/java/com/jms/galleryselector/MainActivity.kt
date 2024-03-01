package com.jms.galleryselector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jms.galleryselector.ui.theme.GallerySelectorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GallerySelectorTheme {
                // A surface container using the 'background' color from the theme
            }
        }
    }
}