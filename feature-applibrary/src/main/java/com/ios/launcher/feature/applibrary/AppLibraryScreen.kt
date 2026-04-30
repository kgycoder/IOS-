package com.ios.launcher.feature.applibrary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ios.launcher.core.ui.glass.GlassCard

@Composable
fun AppLibraryScreen(apps: List<String>) {
    var query by remember { mutableStateOf("") }
    val filtered = apps.filter { it.contains(query, ignoreCase = true) }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        OutlinedTextField(value = query, onValueChange = { query = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Search App Library") })
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(filtered.groupBy { it.firstOrNull()?.uppercase() ?: "#" }.toSortedMap().toList()) { (category, appItems) ->
                GlassCard(modifier = Modifier.fillMaxWidth(), cornerRadius = 18.dp) {
                    Text(category, color = Color.White)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 8.dp)) {
                        appItems.take(4).forEach { name ->
                            Text(
                                text = name,
                                color = Color.White,
                                modifier = Modifier
                                    .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
