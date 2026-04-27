package com.example.travelapp.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Toggles the system status bar icon colour for the lifetime of the calling
 * composable. `lightIcons = true` paints icons WHITE (use over a dark hero);
 * `lightIcons = false` paints icons DARK (use over a white background).
 */
@Composable
fun StatusBarIcons(lightIcons: Boolean) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        DisposableEffect(lightIcons) {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                val controller = WindowCompat.getInsetsController(window, view)
                controller.isAppearanceLightStatusBars = !lightIcons
                controller.isAppearanceLightNavigationBars = true
            }
            onDispose {
                if (window != null) {
                    val controller = WindowCompat.getInsetsController(window, view)
                    controller.isAppearanceLightStatusBars = true
                }
            }
        }
    }
}
