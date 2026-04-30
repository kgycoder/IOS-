package com.ios.launcher.feature.home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ios.launcher.core.ui.glass.GlassCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    pages: List<List<String>>,
    dockApps: List<String>,
    onAppClick: (String) -> Unit
) {
    val pagerState = rememberPagerState { pages.size }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            val apps = pages[page]
            IOSGrid(apps = apps, onAppClick = onAppClick)
        }
        DockBar(apps = dockApps, onAppClick = onAppClick)
    }
}

@Composable
private fun IOSGrid(apps: List<String>, onAppClick: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 28.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        repeat(6) { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                repeat(4) { col ->
                    val index = row * 4 + col
                    val appName = apps.getOrNull(index) ?: ""
                    AppIcon(name = appName, onAppClick = onAppClick, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AppIcon(name: String, onAppClick: (String) -> Unit, modifier: Modifier = Modifier) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 0.9f else 1f, spring(stiffness = Spring.StiffnessMediumLow), label = "appScale")
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        GlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .scale(scale)
                .combinedClickable(
                    onClick = {
                        if (name.isNotBlank()) onAppClick(name)
                    },
                    onLongClick = { pressed = !pressed }
                ),
            cornerRadius = 18.dp
        ) {
            Box(modifier = Modifier.fillMaxSize().background(Color.White.copy(alpha = 0.08f)))
        }
        Text(
            text = name,
            fontSize = 11.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

@Composable
private fun DockBar(apps: List<String>, onAppClick: (String) -> Unit) {
    GlassCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 16.dp), cornerRadius = 28.dp) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            apps.forEach { app ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(52.dp).background(Color.White.copy(alpha = 0.14f), RoundedCornerShape(14.dp)))
                    Text(app, color = Color.White, fontWeight = FontWeight.Medium, fontSize = 10.sp)
                }
            }
        }
    }
}
