package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.SegmentData
import com.flyfishxu.vetraui.core.VetraFlatCard
import com.flyfishxu.vetraui.core.VetraSegmentedButton
import com.flyfishxu.vetraui.core.VetraSegmentedButtonOutlined
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SegmentedButtonsScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.canvas),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Standard Segmented Buttons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Segmented Buttons",
                    style = typography.headingLg.copy(color = colors.textPrimary)
                )
                Text(
                    "Mutually exclusive selections in a single control.",
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )
            }
        }

        // Two Segments
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Two Segments",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Perfect for binary choices like view modes.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )

                var twoSegmentIndex by remember { mutableStateOf(0) }
                VetraSegmentedButton(
                    segments = listOf(
                        SegmentData("Grid"),
                        SegmentData("List")
                    ),
                    selectedIndex = twoSegmentIndex,
                    onSegmentSelected = { twoSegmentIndex = it }
                )
            }
        }

        // Three Segments
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Three Segments",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Common for size or level selections.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )

                var threeSegmentIndex by remember { mutableStateOf(1) }
                VetraSegmentedButton(
                    segments = listOf(
                        SegmentData("Small"),
                        SegmentData("Medium"),
                        SegmentData("Large")
                    ),
                    selectedIndex = threeSegmentIndex,
                    onSegmentSelected = { threeSegmentIndex = it }
                )
            }
        }

        // Time Range (5 Segments)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Time Range Selection",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Up to 5 segments for optimal UX.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )

                var timeRangeIndex by remember { mutableStateOf(1) }
                VetraSegmentedButton(
                    segments = listOf(
                        SegmentData("1H"),
                        SegmentData("1D"),
                        SegmentData("1W"),
                        SegmentData("1M"),
                        SegmentData("1Y")
                    ),
                    selectedIndex = timeRangeIndex,
                    onSegmentSelected = { timeRangeIndex = it }
                )
            }
        }

        // Outlined Variant
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Outlined Variant",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Border-only style for subtle emphasis.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )

                var outlinedIndex by remember { mutableStateOf(0) }
                VetraSegmentedButtonOutlined(
                    segments = listOf(
                        SegmentData("All"),
                        SegmentData("Active"),
                        SegmentData("Completed")
                    ),
                    selectedIndex = outlinedIndex,
                    onSegmentSelected = { outlinedIndex = it }
                )
            }
        }

        // With Icons
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "With Icons",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Icons improve clarity and recognition.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )

                var iconTextIndex by remember { mutableStateOf(0) }
                VetraSegmentedButton(
                    segments = listOf(
                        SegmentData("Approve", Icons.Default.Check),
                        SegmentData("Reject", Icons.Default.Close),
                        SegmentData("Favorite", Icons.Default.Favorite)
                    ),
                    selectedIndex = iconTextIndex,
                    onSegmentSelected = { iconTextIndex = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                var iconOnlyIndex by remember { mutableStateOf(1) }
                VetraSegmentedButton(
                    segments = listOf(
                        SegmentData("Check", Icons.Default.Check),
                        SegmentData("Star", Icons.Default.Star),
                        SegmentData("Heart", Icons.Default.Favorite),
                        SegmentData("List", Icons.AutoMirrored.Filled.List)
                    ),
                    selectedIndex = iconOnlyIndex,
                    onSegmentSelected = { iconOnlyIndex = it }
                )
            }
        }

        // Disabled Segments
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Disabled Segments",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Individual segments can be disabled.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )

                var disabledIndex by remember { mutableStateOf(0) }
                VetraSegmentedButton(
                    segments = listOf(
                        SegmentData("Available"),
                        SegmentData("Unavailable", enabled = false),
                        SegmentData("Available")
                    ),
                    selectedIndex = disabledIndex,
                    onSegmentSelected = { disabledIndex = it }
                )
            }
        }

        // Full Width
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Full Width",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Expand to fill available width.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )

                var fullWidthIndex by remember { mutableStateOf(0) }
                VetraSegmentedButton(
                    segments = listOf(
                        SegmentData("Overview"),
                        SegmentData("Details")
                    ),
                    selectedIndex = fullWidthIndex,
                    onSegmentSelected = { fullWidthIndex = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Practical Example
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    "Practical Example",
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    "Filter content based on selection.",
                    style = typography.bodySm.copy(color = colors.textSecondary)
                )

                var contentFilter by remember { mutableStateOf(0) }
                val filterOptions = listOf("All", "Images", "Videos", "Documents")

                VetraSegmentedButton(
                    segments = filterOptions.map { SegmentData(it) },
                    selectedIndex = contentFilter,
                    onSegmentSelected = { contentFilter = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                VetraFlatCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = when (contentFilter) {
                            0 -> "Showing all types of content"
                            1 -> "Showing only images (JPG, PNG, GIF, etc.)"
                            2 -> "Showing only videos (MP4, AVI, MOV, etc.)"
                            3 -> "Showing only documents (PDF, DOC, TXT, etc.)"
                            else -> ""
                        },
                        style = typography.bodyMd.copy(
                            color = colors.textSecondary
                        )
                    )
                }
            }
        }
    }
}
