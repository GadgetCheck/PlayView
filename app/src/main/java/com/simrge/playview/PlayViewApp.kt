package com.simrge.playview

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.simrge.playview.data.Catalog
import com.simrge.playview.ui.categories.CategoriesScreen
import com.simrge.playview.ui.charts.ChartsScreen
import com.simrge.playview.ui.detail.AppDetailScreen
import com.simrge.playview.ui.home.HomeScreen
import com.simrge.playview.ui.search.SearchResults
import kotlinx.coroutines.launch

private enum class Tab(val label: String, val icon: ImageVector) {
    Home("Home", Icons.Filled.Home),
    Charts("Charts", Icons.Filled.Star),
    Categories("Categories", Icons.AutoMirrored.Filled.List),
}

@Composable
fun PlayViewApp() {
    var tab by rememberSaveable { mutableStateOf(Tab.Home) }
    var appId by rememberSaveable { mutableStateOf<String?>(null) }
    var categoryId by rememberSaveable { mutableStateOf<String?>(null) }
    var query by rememberSaveable { mutableStateOf("") }
    var searching by rememberSaveable { mutableStateOf(false) }
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    fun closeSearch() {
        searching = false
        query = ""
    }

    BackHandler(enabled = appId != null || searching || categoryId != null) {
        when {
            appId != null -> appId = null
            searching -> closeSearch()
            else -> categoryId = null
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 28.dp, bottom = 24.dp),
    ) {
        Column(Modifier.fillMaxSize()) {
            if (appId == null) {
                if (searching) {
                    SearchField(
                        query = query,
                        onQueryChange = { query = it },
                        onClose = { closeSearch() },
                    )
                } else {
                    SearchPill(onClick = { searching = true })
                }
            }
            Box(Modifier.weight(1f).fillMaxWidth()) {
                when (tab) {
                    Tab.Home -> HomeScreen(onAppClick = { appId = it })
                    Tab.Charts -> ChartsScreen(onAppClick = { appId = it })
                    Tab.Categories -> {
                        val selectedCategory = categoryId
                        val category = selectedCategory?.let(Catalog::category)
                        if (category == null) {
                            CategoriesScreen(onCategoryClick = { categoryId = it })
                        } else {
                            ChartsScreen(
                                onAppClick = { appId = it },
                                title = category.name,
                                subtitle = category.description,
                                apps = remember(selectedCategory) { Catalog.appsIn(selectedCategory) },
                            )
                        }
                    }
                }
                if (searching) {
                    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        SearchResults(
                            query = query,
                            results = remember(query) { Catalog.search(query) },
                            onQueryChange = { query = it },
                            onAppClick = { id ->
                                searching = false
                                appId = id
                            },
                        )
                    }
                }
                val selected = appId
                if (selected != null) {
                    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        AppDetailScreen(
                            appId = selected,
                            onBack = { appId = null },
                            onAppClick = { appId = it },
                            onInstall = {
                                scope.launch { snackbar.showSnackbar("Demo only — nothing was installed") }
                            },
                        )
                    }
                }
            }
            if (appId == null && !searching) {
                NavigationBar(windowInsets = WindowInsets(0)) {
                    Tab.entries.forEach { dest ->
                        NavigationBarItem(
                            selected = tab == dest,
                            onClick = {
                                tab = dest
                                categoryId = null
                            },
                            icon = { Icon(dest.icon, contentDescription = dest.label) },
                            label = { Text(dest.label) },
                        )
                    }
                }
            }
        }
        SnackbarHost(snackbar, Modifier.align(Alignment.BottomCenter).padding(16.dp))
    }
}

@Composable
private fun SearchPill(onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(28.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(56.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.Outlined.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.width(12.dp))
            Text("Search apps", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onClose: () -> Unit,
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text("Search apps") },
        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
        trailingIcon = {
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = "Close search")
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(28.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
    )
}
