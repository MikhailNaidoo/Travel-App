package com.example.travelapp

import android.graphics.Color as AndroidColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.travelapp.ui.AppScaffold
import com.example.travelapp.ui.theme.TravelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Default to a light system bar (dark icons) — per-screen overrides can
        // flip to light icons over dark hero areas via StatusBarIcons.
        val lightScrim = AndroidColor.argb(0xE6, 0xFF, 0xFF, 0xFF)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(lightScrim, lightScrim)
        )
        setContent {
            TravelAppTheme {
                AppScaffold()
            }
        }
    }
}
