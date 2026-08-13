package com.simrge.playview.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simrge.playview.data.AppItem
import com.simrge.playview.ui.components.AppListRow

@Composable
fun SearchResults(
    query: String,
    results: List<AppItem>,
    onAppClick: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val trimmed = query.trim()
    when {
        trimmed.length < 2 -> Column(
            modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text("Search PlayView", style = MaterialTheme.typography.titleLarge)
            Text(
                "Try a suggestion, or type at least two letters. This catalog is offline.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("camera", "chess", "notes").forEach { suggestion ->
                    SuggestionChip(
                        onClick = { onQueryChange(suggestion) },
                        label = { Text(suggestion) },
                    )
                }
            }
        }
        results.isEmpty() -> Text(
            "No apps match “$trimmed”",
            modifier = modifier.padding(24.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
        else -> LazyColumn(modifier.fillMaxSize()) {
            items(results, key = { it.id }, contentType = { "search-row" }) { app ->
                AppListRow(app = app, onClick = { onAppClick(app.id) })
            }
        }
    }
}
