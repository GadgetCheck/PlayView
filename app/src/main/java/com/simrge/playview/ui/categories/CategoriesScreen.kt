package com.simrge.playview.ui.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.simrge.playview.data.Catalog
import com.simrge.playview.data.Category

@Composable
fun CategoriesScreen(
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Column(Modifier.padding(bottom = 8.dp)) {
            Text("Categories", style = MaterialTheme.typography.displaySmall)
            Text(
                "Browse the offline catalog by genre",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Catalog.categories.chunked(2).forEach { pair ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                pair.forEach { category ->
                    CategoryTile(
                        category = category,
                        onClick = { onCategoryClick(category.id) },
                        modifier = Modifier.weight(1f),
                    )
                }
                if (pair.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun CategoryTile(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val color = Color(category.accent)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.15f)
            .clip(RoundedCornerShape(28.dp))
            .background(color.copy(alpha = 0.16f))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(category.name, style = MaterialTheme.typography.titleLarge, color = color)
        Text(
            category.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
        )
    }
}
