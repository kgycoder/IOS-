package com.ios.launcher.feature.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ios.launcher.core.ui.glass.GlassCard

@Composable
fun NowPlayingPanel() {
    val expanded = remember { mutableStateOf(false) }
    GlassCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), cornerRadius = 24.dp) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Now Playing", color = Color.White)
            Button(onClick = { expanded.value = !expanded.value }) { Text(if (expanded.value) "Mini" else "Expand") }
        }
        AnimatedVisibility(
            visible = expanded.value,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column(modifier = Modifier.padding(top = 10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(18.dp))
                )
                Text("Album - Artist", color = Color.White, fontSize = 20.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(onClick = {}) { Text("Prev") }
                    Button(onClick = {}) { Text("Play/Pause") }
                    Button(onClick = {}) { Text("Next") }
                }
            }
        }
    }
}
