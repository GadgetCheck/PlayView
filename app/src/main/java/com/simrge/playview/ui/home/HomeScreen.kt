package com.simrge.playview.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.simrge.playview.data.AppItem
import com.simrge.playview.data.Catalog
import com.simrge.playview.data.Hero
import com.simrge.playview.ui.components.AppIcon
import com.simrge.playview.ui.components.AppShelf

@Composable
fun HomeScreen(
    onAppClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item(key = "title", contentType = "header") {
            Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text("For you", style = MaterialTheme.typography.displaySmall)
                Text(
                    "An offline Play-like catalog. Nothing is installed.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        item(key = "heroes", contentType = "heroes") {
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Catalog.homeHeroes.forEach { (hero, app) ->
                    HeroCard(hero = hero, app = app, onClick = { onAppClick(app.id) })
                }
            }
        }
        items(Catalog.homeShelves, key = { it.first.id }, contentType = { "shelf" }) { (shelf, apps) ->
            AppShelf(
                title = shelf.title,
                subtitle = shelf.subtitle,
                apps = apps,
                onAppClick = onAppClick,
            )
        }
    }
}

@Composable
private fun HeroCard(hero: Hero, app: AppItem, onClick: () -> Unit) {
    val accent = remember(app.accent) { Color(app.accent) }
    val brush = remember(accent) { Brush.linearGradient(listOf(accent, accent.copy(alpha = 0.65f))) }
    Column(
        Modifier
            .width(280.dp)
            .clickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(168.dp)
                .background(brush, RoundedCornerShape(28.dp)),
            contentAlignment = Alignment.BottomStart,
        ) {
            AppIcon(
                name = app.name,
                accent = app.accent,
                size = 56.dp,
                modifier = Modifier.align(Alignment.TopEnd).padding(16.dp),
            )
            Text(
                hero.title,
                color = Color.White,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(hero.caption, style = MaterialTheme.typography.titleMedium, maxLines = 2)
    }
}
