package com.flyfishxu.vetraui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.VetraBrandCard
import com.flyfishxu.vetraui.core.VetraButton
import com.flyfishxu.vetraui.core.VetraCard
import com.flyfishxu.vetraui.core.VetraOutlinedButton
import com.flyfishxu.vetraui.core.VetraRadioButton
import com.flyfishxu.vetraui.core.indication.vetraPressIndication
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import com.flyfishxu.vetraui.theme.ThemeMode

@Composable
fun SettingsScreen(
    themeMode: ThemeMode,
    systemInDarkMode: Boolean,
    actualDarkMode: Boolean,
    onThemeModeChange: (ThemeMode) -> Unit
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val uriHandler = LocalUriHandler.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Settings",
                    style = typography.displaySm.copy(color = colors.textPrimary)
                )
                Text(
                    "Customize your experience",
                    style = typography.bodyLg.copy(color = colors.textSecondary)
                )
            }
        }

        // Appearance
        item {
            VetraCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Palette,
                            contentDescription = null,
                            tint = colors.brand
                        )
                        Text(
                            "Appearance",
                            style = typography.headingMd.copy(color = colors.textPrimary)
                        )
                    }

                    // Theme mode options
                    ThemeModeOption(
                        selected = themeMode == ThemeMode.SYSTEM,
                        onClick = { onThemeModeChange(ThemeMode.SYSTEM) },
                        icon = Icons.Outlined.AutoAwesome,
                        title = "System Default",
                        description = "Follow system theme (currently ${if (systemInDarkMode) "dark" else "light"})"
                    )

                    ThemeModeOption(
                        selected = themeMode == ThemeMode.LIGHT,
                        onClick = { onThemeModeChange(ThemeMode.LIGHT) },
                        icon = Icons.Outlined.LightMode,
                        title = "Light Mode",
                        description = "Always use light theme"
                    )

                    ThemeModeOption(
                        selected = themeMode == ThemeMode.DARK,
                        onClick = { onThemeModeChange(ThemeMode.DARK) },
                        icon = Icons.Outlined.DarkMode,
                        title = "Dark Mode",
                        description = "Always use dark theme"
                    )

                    // Info banner
                    if (themeMode == ThemeMode.SYSTEM) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(VetraTheme.shapes.sm)
                                .background(colors.brandSubtle)
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = null,
                                tint = colors.brand,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                "Theme automatically changes with your system settings",
                                style = typography.bodySm.copy(color = colors.onBrandSubtle)
                            )
                        }
                    }
                }
            }
        }

        // About
        item {
            VetraBrandCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                            tint = colors.brand
                        )
                        Text(
                            "About Vetra UI",
                            style = typography.headingMd.copy(color = colors.onBrandSubtle)
                        )
                    }

                    Text(
                        "Version 1.0.0-alpha02",
                        style = typography.bodyMd.copy(color = colors.onBrandSubtle)
                    )

                    Text(
                        "A modern, elegant design system for Compose Multiplatform. Built with accessibility, performance, and developer experience in mind.",
                        style = typography.bodySm.copy(color = colors.onBrandSubtle)
                    )

                    Text(
                        "Currently using ${if (actualDarkMode) "dark" else "light"} theme",
                        style = typography.bodySm.copy(color = colors.onBrandSubtle)
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        VetraOutlinedButton(onClick = {
                            uriHandler.openUri("https://github.com/flyfishxu/vetra-ui/tree/main/docs")
                        }) {
                            Text("Documentation")
                        }
                        VetraButton(onClick = {
                            uriHandler.openUri("https://github.com/flyfishxu/vetra")
                        }) {
                            Text("GitHub")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeModeOption(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    title: String,
    description: String
) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val interactionSource = remember { MutableInteractionSource() }

    // Animate background color smoothly
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) colors.brandSubtle else colors.canvasElevated,
        animationSpec = tween(durationMillis = 200),
        label = "cardBackgroundColor"
    )

    // Build card-like appearance with animated background
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .vetraShadow(elevation = shadows.sm, shape = shapes.md)
            .clip(shapes.md)
            // Add subtle border for better definition
            .background(colors.borderSubtle)
            .padding(1.dp)
            .clip(shapes.md)
            .background(backgroundColor)
            .clickable(
                onClick = onClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = vetraPressIndication()
            )
            .padding(15.dp) // Reduced by 1dp to account for border
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) colors.brand else colors.textSecondary,
                modifier = Modifier.size(24.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    title,
                    style = typography.bodyLg.copy(
                        color = if (selected) colors.onBrandSubtle else colors.textPrimary
                    )
                )
                Text(
                    description,
                    style = typography.bodySm.copy(
                        color = if (selected) colors.onBrandSubtle else colors.textSecondary
                    )
                )
            }

            VetraRadioButton(
                selected = selected,
                onClick = onClick
            )
        }
    }
}
