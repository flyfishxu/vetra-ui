package com.flyfishxu.vetraui.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.indication.vetraPressIndication
import com.flyfishxu.vetraui.core.theme.VetraTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Tab
 *
 * A modern tab component with smooth animations and elegant design.
 * Perfect for organizing content into separate views that users can switch between.
 *
 * Design Features:
 * - Smooth indicator animation
 * - Color transitions for selected/unselected states
 * - Clean, minimal aesthetic
 * - Support for text, icons, or both
 * - Accessible by default
 */

private val TabHeight = 48.dp
private val TabMinWidth = 90.dp
private val TabMaxWidth = 360.dp
private val TabHorizontalPadding = 16.dp
private val TabVerticalPadding = 12.dp
private val IndicatorHeight = 3.dp
private val IconSize = 24.dp
private val IconTextSpacing = 8.dp
private val AnimationDuration = 250

/**
 * Tab Row Container
 *
 * A container for tabs with an animated indicator that moves between selected tabs.
 *
 * @param selectedTabIndex The index of the currently selected tab
 * @param modifier Modifier for the tab row
 * @param containerColor Background color of the tab row
 * @param contentColor Content color for unselected tabs
 * @param indicatorColor Color of the selection indicator
 * @param tabs The tabs to display
 */
@Composable
fun VetraTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = VetraTheme.colors.canvasElevated,
    contentColor: Color = VetraTheme.colors.textSecondary,
    indicatorColor: Color = VetraTheme.colors.brand,
    tabs: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = containerColor,
        contentColor = contentColor
    ) {
        TabRowLayout(
            selectedTabIndex = selectedTabIndex,
            indicatorColor = indicatorColor,
            tabs = tabs
        )
    }
}

/**
 * Internal composable that handles the layout and indicator animation
 */
@Composable
private fun TabRowLayout(
    selectedTabIndex: Int,
    indicatorColor: Color,
    tabs: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val indicatorHeightPx = with(density) { IndicatorHeight.roundToPx() }

    // Animate indicator position as a fraction (0f to tabCount-1)
    val animatedSelectedIndex by animateFloatAsState(
        targetValue = selectedTabIndex.toFloat(),
        animationSpec = tween(
            durationMillis = AnimationDuration,
            easing = FastOutSlowInEasing
        ),
        label = "tabIndicatorPosition"
    )

    SubcomposeLayout { constraints ->
        val tabHeight = TabHeight.roundToPx()

        // First pass: count tabs to calculate width
        val tabMeasurables = subcompose("tabs", tabs)
        val tabCount = tabMeasurables.size
        val tabWidth = if (tabCount > 0) constraints.maxWidth / tabCount else constraints.maxWidth

        // Measure tabs with calculated fixed width
        val tabPlaceables = tabMeasurables.map { measurable ->
            measurable.measure(
                Constraints.fixed(
                    width = tabWidth,
                    height = tabHeight
                )
            )
        }

        // Calculate animated indicator position
        val safeAnimatedIndex = animatedSelectedIndex.coerceIn(
            0f,
            maxOf(0f, tabCount - 1f)
        )
        val indicatorOffset = (safeAnimatedIndex * tabWidth).toInt()

        // Measure indicator
        val indicatorPlaceable = subcompose("indicator") {
            Box(
                modifier = Modifier
                    .width(with(density) { tabWidth.toDp() })
                    .height(IndicatorHeight)
                    .background(
                        color = indicatorColor,
                        shape = VetraTheme.shapes.full
                    )
            )
        }.first().measure(
            Constraints.fixed(
                width = tabWidth,
                height = indicatorHeightPx
            )
        )

        layout(constraints.maxWidth, tabHeight) {
            // Place tabs
            tabPlaceables.forEachIndexed { index, placeable ->
                placeable.placeRelative(
                    x = index * tabWidth,
                    y = 0
                )
            }

            // Place animated indicator at bottom
            indicatorPlaceable.placeRelative(
                x = indicatorOffset,
                y = tabHeight - indicatorHeightPx
            )
        }
    }
}

/**
 * Internal Surface composable for TabRow background
 */
@Composable
private fun Surface(
    modifier: Modifier = Modifier,
    color: Color = VetraTheme.colors.canvasElevated,
    contentColor: Color = VetraTheme.colors.textPrimary,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(color)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            content()
        }
    }
}

/**
 * Standard Tab - Selectable tab item
 *
 * @param selected Whether this tab is selected
 * @param onClick Called when the tab is clicked
 * @param modifier Modifier for the tab
 * @param enabled Whether the tab is enabled
 * @param text Optional text label
 * @param icon Optional icon
 * @param selectedIcon Optional different icon when selected
 */
@Composable
fun VetraTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    selectedIcon: @Composable (() -> Unit)? = null
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    val contentColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.textDisabled
            selected -> colors.brand
            else -> colors.textSecondary
        },
        animationSpec = tween(durationMillis = AnimationDuration),
        label = "tabContentColor"
    )

    Box(
        modifier = modifier
            .height(TabHeight)
            .clip(shapes.none)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = vetraPressIndication()
            )
            .padding(
                horizontal = TabHorizontalPadding,
                vertical = TabVerticalPadding
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            when {
                // Icon and text
                icon != null && text != null -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (selected && selectedIcon != null) {
                            selectedIcon()
                        } else {
                            icon()
                        }
                        Spacer(modifier = Modifier.height(IconTextSpacing))
                        text()
                    }
                }
                // Icon only
                icon != null -> {
                    if (selected && selectedIcon != null) {
                        selectedIcon()
                    } else {
                        icon()
                    }
                }
                // Text only
                text != null -> {
                    text()
                }
            }
        }
    }
}

/**
 * Convenience overload that accepts String text
 */
@Composable
fun VetraTab(
    selected: Boolean,
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    selectedIcon: ImageVector? = null
) {
    val typography = VetraTheme.typography

    VetraTab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = {
            Text(
                text = text,
                style = typography.labelLg,
                maxLines = 1
            )
        },
        icon = if (icon != null) {
            {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(IconSize)
                )
            }
        } else null,
        selectedIcon = if (selectedIcon != null) {
            {
                Icon(
                    imageVector = selectedIcon,
                    contentDescription = null,
                    modifier = Modifier.size(IconSize)
                )
            }
        } else null
    )
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraTabPreview() {
    VetraTheme {
        var selectedTab by remember { mutableStateOf(0) }
        val tabs = listOf("Overview", "Features", "Reviews", "Related")

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Text Tabs",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraTabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    VetraTab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = title
                    )
                }
            }

            Text(
                "Individual Tab States",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            Row(
                modifier = Modifier
                    .background(VetraTheme.colors.canvasElevated)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                VetraTab(
                    selected = false,
                    onClick = {},
                    text = "Unselected"
                )
                VetraTab(
                    selected = true,
                    onClick = {},
                    text = "Selected"
                )
                VetraTab(
                    selected = false,
                    onClick = {},
                    text = "Disabled",
                    enabled = false
                )
            }
        }
    }
}

@Preview
@Composable
private fun VetraTabWithIconsPreview() {
    VetraTheme {
        var selectedTab by remember { mutableStateOf(0) }
        val tabs = listOf("Home", "Search", "Favorites", "Profile")
        val icons = listOf(
            Icons.Outlined.Home,
            Icons.Outlined.Search,
            Icons.Outlined.FavoriteBorder,
            Icons.Outlined.Person
        )
        val selectedIcons = listOf(
            Icons.Filled.Home,
            Icons.Filled.Search,
            Icons.Filled.Favorite,
            Icons.Filled.Person
        )

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Tabs with Icons",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraTabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    VetraTab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = title,
                        icon = icons[index],
                        selectedIcon = selectedIcons[index]
                    )
                }
            }

            Text(
                "Icon-Only Tabs",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            var iconOnlyTab by remember { mutableStateOf(1) }

            VetraTabRow(selectedTabIndex = iconOnlyTab) {
                icons.forEachIndexed { index, icon ->
                    VetraTab(
                        selected = iconOnlyTab == index,
                        onClick = { iconOnlyTab = index },
                        icon = {
                            Icon(
                                imageVector = if (iconOnlyTab == index) selectedIcons[index] else icon,
                                contentDescription = tabs[index],
                                modifier = Modifier.size(IconSize)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraTabThreeItemsPreview() {
    VetraTheme {
        var selectedTab by remember { mutableStateOf(1) }
        val tabs = listOf("Photos", "Videos", "Albums")

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Three Tabs",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraTabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    VetraTab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = title
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraTabDarkPreview() {
    VetraTheme(darkMode = true) {
        var selectedTab by remember { mutableStateOf(0) }
        val tabs = listOf("Trending", "Following", "Popular", "New")

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Dark Mode Tabs",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraTabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    VetraTab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = title
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VetraTabCustomColorsPreview() {
    VetraTheme {
        var selectedTab by remember { mutableStateOf(2) }
        val tabs = listOf("Details", "Specs", "Reviews")

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Custom Colors",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraTabRow(
                selectedTabIndex = selectedTab,
                containerColor = VetraTheme.colors.brandSubtle,
                indicatorColor = VetraTheme.colors.accent
            ) {
                tabs.forEachIndexed { index, title ->
                    VetraTab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = title
                    )
                }
            }
        }
    }
}

