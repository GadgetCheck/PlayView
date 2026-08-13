package com.simrge.playview.ui.charts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simrge.playview.data.AppItem
import com.simrge.playview.data.Catalog
import com.simrge.playview.ui.components.AppListRow

@Composable
fun ChartsScreen(
    onAppClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    title: String = "Top charts",
    subtitle: String = "Ranked by rating",
    apps: List<AppItem>? = null,
) {
    val rows = apps ?: remember { Catalog.charts() }
    Column(modifier.fillMaxSize()) {
        Text(
            title,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp),
        )
        Text(
            "$subtitle · ${rows.size} apps",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        )
        LazyColumn(Modifier.weight(1f), contentPadding = PaddingValues(bottom = 16.dp)) {
            itemsIndexed(rows, key = { _, app -> app.id }, contentType = { _, _ -> "chart-row" }) { index, app ->
                AppListRow(app = app, rank = index + 1, onClick = { onAppClick(app.id) })
            }
        }
    }
}
