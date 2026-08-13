package com.simrge.playview.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.simrge.playview.data.Catalog
import com.simrge.playview.ui.components.AppIcon
import com.simrge.playview.ui.components.AppShelf

@Composable
fun AppDetailScreen(
    appId: String,
    onBack: () -> Unit,
    onAppClick: (String) -> Unit,
    onInstall: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val app = Catalog.app(appId) ?: return
    val category = Catalog.category(app.categoryId)
    val similar = remember(app.id) { Catalog.similarTo(app) }
    val shots = remember(app.accent) {
        listOf(0.95f, 0.75f, 0.6f, 0.45f).map { Color(app.accent).copy(alpha = it) }
    }

    Column(modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(app.name, style = MaterialTheme.typography.titleLarge, maxLines = 1)
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                AppIcon(name = app.name, accent = app.accent, size = 88.dp)
                Spacer(Modifier.width(16.dp))
                Column(Modifier.weight(1f)) {
                    Text(app.name, style = MaterialTheme.typography.headlineMedium)
                    Text(app.summary, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        "${category?.name ?: app.categoryId}  ·  ${app.downloads}  ·  ★ ${"%.1f".format(app.rating)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            Button(
                onClick = onInstall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(28.dp),
            ) {
                Text("Install")
            }
            Text("About", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))
            Text(app.blurb, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 16.dp))
            Text("Screenshots", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                shots.forEach { color ->
                    Box(
                        Modifier
                            .width(110.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(color),
                    )
                }
            }
            if (similar.isNotEmpty()) {
                Spacer(Modifier.height(16.dp))
                AppShelf(
                    title = "Similar apps",
                    subtitle = category?.name ?: "More like this",
                    apps = similar,
                    onAppClick = onAppClick,
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}
