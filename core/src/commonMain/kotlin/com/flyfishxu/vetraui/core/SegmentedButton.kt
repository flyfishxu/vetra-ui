package com.flyfishxu.vetraui.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.indication.vetraPressIndication
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra SegmentedButton
 *
 * A modern segmented button component for mutually exclusive selections.
 * Perfect for switching between 2-5 options in a single control.
 *
 * Segmented Button Variants:
 * - Standard (VetraSegmentedButton): Solid selected segment with smooth animation
 * - Outlined (VetraSegmentedButtonOutlined): Border-only variant for subtle emphasis
 *
 * Design Features:
 * - Smooth sliding animation for selected state
 * - Support for text, icons, or both
 * - Animated color transitions
 * - Clean, modern aesthetics
 * - Accessible by default
 * - Consistent with Vetra design system
 *
 * Best Practices:
 * - Use 2-5 segments for optimal UX
 * - Keep segment labels concise
 * - Ensure all segments have similar width for better visual balance
 */

private val SegmentedButtonHeight = 40.dp
private val SegmentedButtonMinWidth = 60.dp
private val SegmentedButtonHorizontalPadding = 16.dp
private val SegmentedButtonVerticalPadding = 10.dp
private val SegmentedButtonIconSize = 18.dp
private val SegmentedButtonIconTextSpacing = 6.dp
private val AnimationDuration = 300

/**
 * Standard Segmented Button - Solid selected segment
 *
 * Use for mutually exclusive selections where you want clear visual hierarchy.
 * The selected segment has a solid background that smoothly animates between options.
 *
 * @param segments List of segment data (label, optional icon, enabled state)
 * @param selectedIndex Index of the currently selected segment
 * @param onSegmentSelected Called when a segment is clicked with the selected index
 * @param modifier Modifier for the segmented button container
 */
@Composable
fun VetraSegmentedButton(
    segments: List<SegmentData>,
    selectedIndex: Int,
    onSegmentSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    require(segments.size in 2..5) {
        "SegmentedButton should have 2-5 segments. Current: ${segments.size}"
    }

    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows

    Box(
        modifier = modifier
            .vetraShadow(elevation = shadows.sm, shape = shapes.sm)
            .clip(shapes.sm)
            .background(colors.canvasElevated)
            .border(width = 1.dp, color = colors.borderSubtle, shape = shapes.sm)
    ) {
        SegmentedButtonLayout(
            segments = segments,
            selectedIndex = selectedIndex,
            onSegmentSelected = onSegmentSelected,
            containerColor = colors.canvasElevated,
            selectedContainerColor = colors.brand,
            selectedContentColor = colors.onBrand,
            unselectedContentColor = colors.textSecondary,
            disabledContentColor = colors.textDisabled
        )
    }
}

/**
 * Outlined Segmented Button - Border-only variant
 *
 * Use for subtle segmented selections or when you want lighter visual weight.
 * The selected segment is indicated by brand color border and text.
 *
 * @param segments List of segment data (label, optional icon, enabled state)
 * @param selectedIndex Index of the currently selected segment
 * @param onSegmentSelected Called when a segment is clicked with the selected index
 * @param modifier Modifier for the segmented button container
 */
@Composable
fun VetraSegmentedButtonOutlined(
    segments: List<SegmentData>,
    selectedIndex: Int,
    onSegmentSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    require(segments.size in 2..5) {
        "SegmentedButton should have 2-5 segments. Current: ${segments.size}"
    }

    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes

    Box(
        modifier = modifier
            .clip(shapes.sm)
            .background(Color.Transparent)
            .border(width = 1.dp, color = colors.border, shape = shapes.sm)
    ) {
        SegmentedButtonLayout(
            segments = segments,
            selectedIndex = selectedIndex,
            onSegmentSelected = onSegmentSelected,
            containerColor = Color.Transparent,
            selectedContainerColor = colors.brandSubtle,
            selectedContentColor = colors.brand,
            unselectedContentColor = colors.textSecondary,
            disabledContentColor = colors.textDisabled
        )
    }
}

/**
 * Internal layout composable that handles segment arrangement and animation
 */
@Composable
private fun SegmentedButtonLayout(
    segments: List<SegmentData>,
    selectedIndex: Int,
    onSegmentSelected: (Int) -> Unit,
    containerColor: Color,
    selectedContainerColor: Color,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    disabledContentColor: Color
) {
    val density = LocalDensity.current
    val shapes = VetraTheme.shapes

    // Animate selected segment position
    val animatedSelectedIndex by animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        animationSpec = tween(
            durationMillis = AnimationDuration,
            easing = FastOutSlowInEasing
        ),
        label = "segmentedButtonPosition"
    )

    SubcomposeLayout { constraints ->
        val buttonHeight = SegmentedButtonHeight.roundToPx()
        val segmentCount = segments.size
        val segmentWidth =
            if (segmentCount > 0) constraints.maxWidth / segmentCount else constraints.maxWidth

        // Measure segments
        val segmentPlaceables = segments.mapIndexed { index, segment ->
            subcompose("segment_$index") {
                SegmentContent(
                    segment = segment,
                    selected = selectedIndex == index,
                    onClick = { if (segment.enabled) onSegmentSelected(index) },
                    selectedContentColor = selectedContentColor,
                    unselectedContentColor = unselectedContentColor,
                    disabledContentColor = disabledContentColor
                )
            }.map { measurable ->
                measurable.measure(
                    Constraints.fixed(
                        width = segmentWidth,
                        height = buttonHeight
                    )
                )
            }
        }

        // Calculate animated indicator position
        val safeAnimatedIndex = animatedSelectedIndex.coerceIn(
            0f,
            maxOf(0f, segmentCount - 1f)
        )
        val indicatorOffset = (safeAnimatedIndex * segmentWidth).toInt()

        // Measure indicator (selected background)
        val indicatorPlaceable = subcompose("indicator") {
            Box(
                modifier = Modifier
                    .width(with(density) { segmentWidth.toDp() })
                    .defaultMinSize(minHeight = SegmentedButtonHeight)
                    .padding(2.dp)
                    .background(
                        color = selectedContainerColor,
                        shape = shapes.xs
                    )
            )
        }.first().measure(
            Constraints.fixed(
                width = segmentWidth,
                height = buttonHeight
            )
        )

        layout(constraints.maxWidth, buttonHeight) {
            // Place animated indicator background
            indicatorPlaceable.placeRelative(
                x = indicatorOffset,
                y = 0
            )

            // Place segments
            segmentPlaceables.forEachIndexed { index, placeables ->
                placeables.forEach { placeable ->
                    placeable.placeRelative(
                        x = index * segmentWidth,
                        y = 0
                    )
                }
            }
        }
    }
}

/**
 * Individual segment content
 */
@Composable
private fun SegmentContent(
    segment: SegmentData,
    selected: Boolean,
    onClick: () -> Unit,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    disabledContentColor: Color
) {
    val typography = VetraTheme.typography
    val interactionSource = remember { MutableInteractionSource() }

    val contentColor by animateColorAsState(
        targetValue = when {
            !segment.enabled -> disabledContentColor
            selected -> selectedContentColor
            else -> unselectedContentColor
        },
        animationSpec = tween(durationMillis = AnimationDuration),
        label = "segmentContentColor"
    )

    val scale by animateFloatAsState(
        targetValue = if (selected) 1f else 0.95f,
        animationSpec = tween(durationMillis = AnimationDuration),
        label = "segmentScale"
    )

    Box(
        modifier = Modifier
            .defaultMinSize(
                minWidth = SegmentedButtonMinWidth,
                minHeight = SegmentedButtonHeight
            )
            .clickable(
                onClick = onClick,
                enabled = segment.enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = vetraPressIndication()
            )
            .padding(
                horizontal = SegmentedButtonHorizontalPadding,
                vertical = SegmentedButtonVerticalPadding
            )
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides typography.labelMd.copy(color = contentColor)
        ) {
            when {
                // Icon and text
                segment.icon != null && segment.label.isNotEmpty() -> {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = segment.icon,
                            contentDescription = null,
                            modifier = Modifier.size(SegmentedButtonIconSize),
                            tint = contentColor
                        )
                        Spacer(modifier = Modifier.width(SegmentedButtonIconTextSpacing))
                        Text(
                            text = segment.label,
                            maxLines = 1
                        )
                    }
                }
                // Icon only
                segment.icon != null -> {
                    Icon(
                        imageVector = segment.icon,
                        contentDescription = segment.label.ifEmpty { null },
                        modifier = Modifier.size(SegmentedButtonIconSize),
                        tint = contentColor
                    )
                }
                // Text only
                else -> {
                    Text(
                        text = segment.label,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

/**
 * Data class representing a single segment in a segmented button
 *
 * @param label Text label for the segment
 * @param icon Optional icon to display
 * @param enabled Whether the segment is enabled (default: true)
 */
data class SegmentData(
    val label: String,
    val icon: ImageVector? = null,
    val enabled: Boolean = true
)

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraSegmentedButtonPreview() {
    VetraTheme {
        var selectedIndex by remember { mutableStateOf(0) }
        val segments = listOf(
            SegmentData("Day"),
            SegmentData("Week"),
            SegmentData("Month"),
            SegmentData("Year")
        )

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Standard Segmented Button",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraSegmentedButton(
                segments = segments,
                selectedIndex = selectedIndex,
                onSegmentSelected = { selectedIndex = it }
            )

            Text(
                "Two Segments",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
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

            Text(
                "Three Segments",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
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
}

@Preview
@Composable
private fun VetraSegmentedButtonWithIconsPreview() {
    VetraTheme {
        var selectedIndex by remember { mutableStateOf(1) }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Segmented Button with Icons",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraSegmentedButton(
                segments = listOf(
                    SegmentData("Approve", Icons.Default.Check),
                    SegmentData("Reject", Icons.Default.Close),
                    SegmentData("Favorite", Icons.Default.Favorite)
                ),
                selectedIndex = selectedIndex,
                onSegmentSelected = { selectedIndex = it }
            )

            Text(
                "Icon Only",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            var iconOnlyIndex by remember { mutableStateOf(0) }
            VetraSegmentedButton(
                segments = listOf(
                    SegmentData("", Icons.Default.Check),
                    SegmentData("", Icons.Default.Close),
                    SegmentData("", Icons.Default.Favorite)
                ),
                selectedIndex = iconOnlyIndex,
                onSegmentSelected = { iconOnlyIndex = it }
            )
        }
    }
}

@Preview
@Composable
private fun VetraSegmentedButtonOutlinedPreview() {
    VetraTheme {
        var selectedIndex by remember { mutableStateOf(2) }
        val segments = listOf(
            SegmentData("All"),
            SegmentData("Active"),
            SegmentData("Completed"),
            SegmentData("Archived")
        )

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Outlined Segmented Button",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraSegmentedButtonOutlined(
                segments = segments,
                selectedIndex = selectedIndex,
                onSegmentSelected = { selectedIndex = it }
            )

            Text(
                "With Disabled Segment",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            var disabledSegmentIndex by remember { mutableStateOf(0) }
            VetraSegmentedButtonOutlined(
                segments = listOf(
                    SegmentData("Option 1"),
                    SegmentData("Disabled", enabled = false),
                    SegmentData("Option 3")
                ),
                selectedIndex = disabledSegmentIndex,
                onSegmentSelected = { disabledSegmentIndex = it }
            )
        }
    }
}

@Preview
@Composable
private fun VetraSegmentedButtonDarkPreview() {
    VetraTheme(darkMode = true) {
        var selectedIndex by remember { mutableStateOf(1) }
        val segments = listOf(
            SegmentData("1H"),
            SegmentData("1D"),
            SegmentData("1W"),
            SegmentData("1M"),
            SegmentData("1Y")
        )

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Dark Mode",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraSegmentedButton(
                segments = segments,
                selectedIndex = selectedIndex,
                onSegmentSelected = { selectedIndex = it }
            )

            var outlinedIndex by remember { mutableStateOf(2) }
            VetraSegmentedButtonOutlined(
                segments = segments,
                selectedIndex = outlinedIndex,
                onSegmentSelected = { outlinedIndex = it }
            )
        }
    }
}

@Preview
@Composable
private fun VetraSegmentedButtonFullWidthPreview() {
    VetraTheme {
        var selectedIndex by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Full Width Segmented Button",
                style = VetraTheme.typography.headingSm.copy(color = VetraTheme.colors.textPrimary)
            )

            VetraSegmentedButton(
                segments = listOf(
                    SegmentData("Overview"),
                    SegmentData("Details")
                ),
                selectedIndex = selectedIndex,
                onSegmentSelected = { selectedIndex = it },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
