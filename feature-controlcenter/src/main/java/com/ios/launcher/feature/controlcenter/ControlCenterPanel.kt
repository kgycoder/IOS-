package com.ios.launcher.feature.controlcenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ios.launcher.core.ui.glass.GlassCard

@Composable
fun ControlCenterPanel() {
    val wifi = remember { mutableStateOf(true) }
    val bt = remember { mutableStateOf(true) }
    val flight = remember { mutableStateOf(false) }
    val brightness = remember { mutableFloatStateOf(0.7f) }
    val volume = remember { mutableFloatStateOf(0.5f) }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ToggleCard("Wi-Fi", wifi.value, Modifier.weight(1f)) { wifi.value = !wifi.value }
            ToggleCard("Bluetooth", bt.value, Modifier.weight(1f)) { bt.value = !bt.value }
            ToggleCard("Flight", flight.value, Modifier.weight(1f)) { flight.value = !flight.value }
        }
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Text("Brightness", color = Color.White, fontSize = 14.sp)
            Slider(value = brightness.floatValue, onValueChange = { brightness.floatValue = it })
            Text("Volume", color = Color.White, fontSize = 14.sp)
            Slider(value = volume.floatValue, onValueChange = { volume.floatValue = it })
        }
    }
}

@Composable
private fun ToggleCard(title: String, enabled: Boolean, modifier: Modifier = Modifier, onToggle: () -> Unit) {
    GlassCard(modifier = modifier, cornerRadius = 18.dp) {
        Text(title, color = Color.White)
        Text(if (enabled) "ON" else "OFF", color = if (enabled) Color(0xFF89B8FF) else Color.White.copy(alpha = 0.7f), modifier = Modifier.padding(top = 6.dp))
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(4.dp))
        androidx.compose.material3.Button(onClick = onToggle) { Text("Toggle") }
    }
}
