package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.VetraCard
import com.flyfishxu.vetraui.core.VetraOutlinedCard
import com.flyfishxu.vetraui.core.VetraSubtleDivider
import com.flyfishxu.vetraui.core.VetraTab
import com.flyfishxu.vetraui.core.VetraTabRow
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Tabs Screen
 *
 * Showcases the Tab component with various configurations
 */
@Preview
@Composable
fun TabsScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.canvas),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Text Tabs
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Text Tabs",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Standard tabs with text labels. The indicator smoothly animates between selections.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )
            }
        }

        item {
            var selectedTab1 by remember { mutableStateOf(0) }
            val tabs1 = listOf("Overview", "Features", "Reviews", "Related")

            Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                VetraTabRow(selectedTabIndex = selectedTab1) {
                    tabs1.forEachIndexed { index, title ->
                        VetraTab(
                            selected = selectedTab1 == index,
                            onClick = { selectedTab1 = index },
                            text = title
                        )
                    }
                }

                // Content for selected tab
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colors.canvasElevated),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Content for ${tabs1[selectedTab1]}",
                        style = typography.bodyLg.copy(color = colors.textPrimary)
                    )
                }
            }
        }

        // Three Tabs
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Three Tabs",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Common three-tab layout for categorizing content.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )
            }
        }

        item {
            var selectedTab2 by remember { mutableStateOf(1) }
            val tabs2 = listOf("Photos", "Videos", "Albums")

            Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                VetraTabRow(selectedTabIndex = selectedTab2) {
                    tabs2.forEachIndexed { index, title ->
                        VetraTab(
                            selected = selectedTab2 == index,
                            onClick = { selectedTab2 = index },
                            text = title
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(colors.canvasElevated),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tabs2[selectedTab2],
                        style = typography.displaySm.copy(color = colors.brand)
                    )
                }
            }
        }

        // Icon Tabs
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Tabs with Icons",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Tabs can include icons alongside text for better visual recognition.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )
            }
        }

        item {
            var selectedTab3 by remember { mutableStateOf(0) }
            val tabs3 = listOf("Home", "Search", "Favorites", "Profile")
            val icons3 = listOf(
                Icons.Outlined.Home,
                Icons.Outlined.Search,
                Icons.Outlined.FavoriteBorder,
                Icons.Outlined.Person
            )
            val selectedIcons3 = listOf(
                Icons.Filled.Home,
                Icons.Filled.Search,
                Icons.Filled.Favorite,
                Icons.Filled.Person
            )

            Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                VetraTabRow(selectedTabIndex = selectedTab3) {
                    tabs3.forEachIndexed { index, title ->
                        VetraTab(
                            selected = selectedTab3 == index,
                            onClick = { selectedTab3 = index },
                            text = title,
                            icon = icons3[index],
                            selectedIcon = selectedIcons3[index]
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(colors.canvasElevated),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tabs3[selectedTab3],
                        style = typography.displaySm.copy(color = colors.accent)
                    )
                }
            }
        }

        // Custom Colors
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Custom Colors",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Customize the container and indicator colors to match your design.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )
            }
        }

        item {
            var selectedTab4 by remember { mutableStateOf(2) }
            val tabs4 = listOf("Details", "Specs", "Reviews")

            Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                VetraTabRow(
                    selectedTabIndex = selectedTab4,
                    containerColor = colors.brandSubtle,
                    indicatorColor = colors.accent
                ) {
                    tabs4.forEachIndexed { index, title ->
                        VetraTab(
                            selected = selectedTab4 == index,
                            onClick = { selectedTab4 = index },
                            text = title
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(colors.brandSubtle),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tabs4[selectedTab4],
                        style = typography.displaySm.copy(color = colors.accent)
                    )
                }
            }
        }
    }
}

/**
 * Guideline Item
 */
@Composable
private fun GuidelineItem(
    title: String,
    description: String
) {
    val typography = VetraTheme.typography
    val colors = VetraTheme.colors

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "• $title",
            style = typography.bodyMd.copy(color = colors.brand)
        )
        Text(
            text = description,
            style = typography.bodySm.copy(color = colors.textSecondary),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

