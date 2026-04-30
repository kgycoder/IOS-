package com.ios.launcher.feature.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ios.launcher.core.ui.glass.GlassCard
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SmartWidgetStack() {
    val pages = listOf("Clock", "Calendar", "Weather")
    val pager = rememberPagerState { pages.size }
    HorizontalPager(state = pager, modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) { page ->
        WidgetCard(type = pages[page])
    }
}

@Composable
private fun WidgetCard(type: String) {
    GlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 24.dp) {
        when (type) {
            "Clock" -> {
                val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                Text("$now", color = Color.White, fontSize = 42.sp)
                Text("Local Time", color = Color.White.copy(alpha = 0.7f))
            }
            "Calendar" -> {
                Text("Friday", color = Color.White, fontSize = 30.sp)
                Text("May 1", color = Color.White.copy(alpha = 0.7f), fontSize = 18.sp)
            }
            else -> {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("22°", color = Color.White, fontSize = 38.sp)
                    Text("Mostly Sunny", color = Color.White.copy(alpha = 0.8f), modifier = Modifier.padding(top = 18.dp))
                }
            }
        }
    }
}
