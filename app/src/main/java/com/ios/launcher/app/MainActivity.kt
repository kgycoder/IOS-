package com.ios.launcher.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.ios.launcher.core.ui.glass.GlassCard
import com.ios.launcher.core.ui.theme.IOSLauncherTheme
import com.ios.launcher.feature.applibrary.AppLibraryScreen
import com.ios.launcher.feature.controlcenter.ControlCenterPanel
import com.ios.launcher.feature.home.HomeScreen
import com.ios.launcher.feature.player.NowPlayingPanel
import com.ios.launcher.feature.widget.SmartWidgetStack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IOSLauncherTheme {
                LauncherRoot()
            }
        }
    }
}

@Composable
private fun LauncherRoot() {
    val apps = remember {
        listOf(
            "Phone", "Messages", "Safari", "Music", "Calendar", "Camera", "Maps", "Settings",
            "Photos", "Clock", "Weather", "Notes", "Files", "Mail", "App Store", "Health",
            "Wallet", "TV", "Podcasts", "Books", "Home", "Contacts", "Stocks", "Translate"
        )
    }
    val pages = remember { listOf(apps, apps.shuffled()) }
    val dockApps = remember { listOf("Phone", "Safari", "Music", "Messages") }

    var showControlCenter by remember { mutableStateOf(false) }
    var showAppLibrary by remember { mutableStateOf(false) }

    val playerBottom by animateDpAsState(
        targetValue = if (showControlCenter) 420.dp else 24.dp,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "playerBottom"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF5B7DD8), Color(0xFF1D2438))
                )
            )
            .statusBarsPadding()
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    if (dragAmount.y > 35f) showControlCenter = true
                    if (dragAmount.y < -35f) showControlCenter = false
                    if (dragAmount.x < -45f) showAppLibrary = true
                    if (dragAmount.x > 45f) showAppLibrary = false
                }
            }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            StatusStrip()
            SmartWidgetStack()
            Spacer(modifier = Modifier.height(8.dp))
            HomeScreen(pages = pages, dockApps = dockApps, onAppClick = {})
        }

        AnimatedVisibility(
            visible = showAppLibrary,
            enter = fadeIn(animationSpec = androidx.compose.animation.core.tween(300, easing = CubicBezierEasing(0.22f, 1f, 0.36f, 1f))),
            exit = fadeOut(animationSpec = androidx.compose.animation.core.tween(220))
        ) {
            GlassCard(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                cornerRadius = 26.dp
            ) {
                AppLibraryScreen(apps = apps)
            }
        }

        AnimatedVisibility(
            visible = showControlCenter,
            modifier = Modifier.align(Alignment.TopCenter),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                cornerRadius = 30.dp
            ) {
                ControlCenterPanel()
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = playerBottom)) {
            NowPlayingPanel()
        }
    }
}

@Composable
private fun StatusStrip() {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        cornerRadius = 16.dp,
        contentPadding = 10.dp
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("09:41", color = Color.White)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color.White.copy(alpha = 0.35f), RoundedCornerShape(10.dp))
            )
        }
    }
}
