package com.flyfishxu.vetraui.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.indication.vetraPressIndication
import com.flyfishxu.vetraui.core.theme.VetraTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Vetra Notify
 *
 * A modern, elegant notification component that slides in from the top of the screen.
 * Designed to provide clear, timely feedback to users about actions, states, or events.
 *
 * Notify Types:
 * - Info (VetraNotifyInfo): Informational messages with blue accent
 * - Success (VetraNotifySuccess): Success confirmations with green accent
 * - Warning (VetraNotifyWarning): Warning messages with amber/orange accent
 * - Danger (VetraNotifyDanger): Error or critical messages with red accent
 *
 * Design Features:
 * - Smooth slide-in animation from top
 * - Auto-dismiss with configurable duration
 * - Optional manual dismiss button
 * - Icon + message layout
 * - Adaptive shadows for depth
 * - Accessible by default
 * - Seamless light/dark mode support
 */

/**
 * Internal data class for holding 4 values
 */
private data class Tuple4<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)

private val NotifyHorizontalPadding = 16.dp
private val NotifyVerticalPadding = 12.dp
private val NotifyIconSize = 20.dp
private val NotifyCloseIconSize = 18.dp
private val NotifyEnterDuration = 350
private val NotifyExitDuration = 250
val NotifyDefaultDuration = 3000L

/**
 * Notification type defining the visual style and semantic meaning
 */
enum class NotifyType {
    Info,
    Success,
    Warning,
    Danger
}

/**
 * Notify data class containing all notification information
 *
 * @param message The notification message text
 * @param type The type of notification (Info, Success, Warning, Danger)
 * @param duration Duration in milliseconds before auto-dismiss (null for no auto-dismiss)
 * @param dismissible Whether the notification can be manually dismissed
 */
data class NotifyData(
    val message: String,
    val type: NotifyType = NotifyType.Info,
    val duration: Long? = NotifyDefaultDuration,
    val dismissible: Boolean = true
)

/**
 * Vetra Notify Component
 *
 * Displays a notification banner that slides in from the top of the screen.
 * Automatically dismisses after the specified duration if configured.
 *
 * @param visible Whether the notification is currently visible
 * @param data Notification data containing message, type, and behavior
 * @param onDismiss Callback invoked when the notification is dismissed
 * @param modifier Modifier for the notification container
 */
@Composable
fun VetraNotify(
    visible: Boolean,
    data: NotifyData,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    val shadows = VetraTheme.shadows
    val typography = VetraTheme.typography

    // Auto-dismiss logic
    LaunchedEffect(visible, data) {
        if (visible && data.duration != null) {
            delay(data.duration)
            onDismiss()
        }
    }

    // Get colors and icon based on type
    val notifyStyle = when (data.type) {
        NotifyType.Info -> Tuple4(
            colors.canvasElevated,
            colors.textPrimary,
            colors.info,
            Icons.Default.Info
        )
        NotifyType.Success -> Tuple4(
            colors.canvasElevated,
            colors.textPrimary,
            colors.success,
            Icons.Default.Check
        )
        NotifyType.Warning -> Tuple4(
            colors.canvasElevated,
            colors.textPrimary,
            colors.warning,
            Icons.Default.Warning
        )
        NotifyType.Danger -> Tuple4(
            colors.canvasElevated,
            colors.textPrimary,
            colors.danger,
            Icons.Default.Close
        )
    }
    
    val backgroundColor = notifyStyle.first
    val contentColor = notifyStyle.second
    val accentColor = notifyStyle.third
    val icon = notifyStyle.fourth

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = spring(
                dampingRatio = 0.8f,
                stiffness = 300f
            ),
            initialOffsetY = { -it }
        ) + fadeIn(
            animationSpec = tween(durationMillis = NotifyEnterDuration)
        ),
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = NotifyExitDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut(
            animationSpec = tween(durationMillis = NotifyExitDuration)
        ),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shapes.sm)
                    .border(width= 1.dp, color = notifyStyle.third, shape = shapes.sm)
                    .background(backgroundColor)
                    .padding(
                        horizontal = NotifyHorizontalPadding,
                        vertical = NotifyVerticalPadding
                    ),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Leading icon with colored background
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(accentColor.copy(alpha = 0.12f), shapes.full)
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(NotifyIconSize),
                        tint = accentColor
                    )
                }

                // Message text
                CompositionLocalProvider(
                    LocalContentColor provides contentColor,
                    LocalTextStyle provides typography.bodyMd.copy(color = contentColor)
                ) {
                    Text(
                        text = data.message,
                        modifier = Modifier.weight(1f)
                    )
                }

                // Dismiss button
                if (data.dismissible) {
                    val interactionSource = remember { MutableInteractionSource() }
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(shapes.full)
                            .clickable(
                                onClick = onDismiss,
                                role = Role.Button,
                                interactionSource = interactionSource,
                                indication = vetraPressIndication(
                                    pressAlpha = 0.08f
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Dismiss",
                            modifier = Modifier.size(NotifyCloseIconSize),
                            tint = colors.textTertiary
                        )
                    }
                }
            }
        }
    }
}

/**
 * Info Notify - Informational message
 *
 * Convenience function for creating an informational notification.
 * Uses the info color scheme (blue) by default.
 *
 * @param visible Whether the notification is visible
 * @param message The notification message
 * @param onDismiss Callback when dismissed
 * @param duration Auto-dismiss duration in milliseconds (null for no auto-dismiss)
 * @param dismissible Whether manual dismiss is allowed
 * @param modifier Modifier for the notification
 */
@Composable
fun VetraNotifyInfo(
    visible: Boolean,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    duration: Long? = NotifyDefaultDuration,
    dismissible: Boolean = true
) {
    VetraNotify(
        visible = visible,
        data = NotifyData(
            message = message,
            type = NotifyType.Info,
            duration = duration,
            dismissible = dismissible
        ),
        onDismiss = onDismiss,
        modifier = modifier
    )
}

/**
 * Success Notify - Success confirmation
 *
 * Convenience function for creating a success notification.
 * Uses the success color scheme (green) by default.
 *
 * @param visible Whether the notification is visible
 * @param message The notification message
 * @param onDismiss Callback when dismissed
 * @param duration Auto-dismiss duration in milliseconds (null for no auto-dismiss)
 * @param dismissible Whether manual dismiss is allowed
 * @param modifier Modifier for the notification
 */
@Composable
fun VetraNotifySuccess(
    visible: Boolean,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    duration: Long? = NotifyDefaultDuration,
    dismissible: Boolean = true
) {
    VetraNotify(
        visible = visible,
        data = NotifyData(
            message = message,
            type = NotifyType.Success,
            duration = duration,
            dismissible = dismissible
        ),
        onDismiss = onDismiss,
        modifier = modifier
    )
}

/**
 * Warning Notify - Warning message
 *
 * Convenience function for creating a warning notification.
 * Uses the warning color scheme (amber/orange) by default.
 *
 * @param visible Whether the notification is visible
 * @param message The notification message
 * @param onDismiss Callback when dismissed
 * @param duration Auto-dismiss duration in milliseconds (null for no auto-dismiss)
 * @param dismissible Whether manual dismiss is allowed
 * @param modifier Modifier for the notification
 */
@Composable
fun VetraNotifyWarning(
    visible: Boolean,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    duration: Long? = NotifyDefaultDuration,
    dismissible: Boolean = true
) {
    VetraNotify(
        visible = visible,
        data = NotifyData(
            message = message,
            type = NotifyType.Warning,
            duration = duration,
            dismissible = dismissible
        ),
        onDismiss = onDismiss,
        modifier = modifier
    )
}

/**
 * Danger Notify - Error or critical message
 *
 * Convenience function for creating an error/danger notification.
 * Uses the danger color scheme (red) by default.
 *
 * @param visible Whether the notification is visible
 * @param message The notification message
 * @param onDismiss Callback when dismissed
 * @param duration Auto-dismiss duration in milliseconds (null for no auto-dismiss)
 * @param dismissible Whether manual dismiss is allowed
 * @param modifier Modifier for the notification
 */
@Composable
fun VetraNotifyDanger(
    visible: Boolean,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    duration: Long? = NotifyDefaultDuration,
    dismissible: Boolean = true
) {
    VetraNotify(
        visible = visible,
        data = NotifyData(
            message = message,
            type = NotifyType.Danger,
            duration = duration,
            dismissible = dismissible
        ),
        onDismiss = onDismiss,
        modifier = modifier
    )
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraNotifyPreview() {
    VetraTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Info notification
                VetraNotifyInfo(
                    visible = true,
                    message = "This is an informational message",
                    onDismiss = {}
                )

                // Success notification
                VetraNotifySuccess(
                    visible = true,
                    message = "Action completed successfully!",
                    onDismiss = {}
                )

                // Warning notification
                VetraNotifyWarning(
                    visible = true,
                    message = "Please check your input before continuing",
                    onDismiss = {}
                )

                // Danger notification
                VetraNotifyDanger(
                    visible = true,
                    message = "An error occurred while processing your request",
                    onDismiss = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun VetraNotifyNonDismissiblePreview() {
    VetraTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Non-dismissible info
                VetraNotifyInfo(
                    visible = true,
                    message = "This notification cannot be manually dismissed",
                    onDismiss = {},
                    dismissible = false
                )

                // Non-dismissible with no auto-dismiss
                VetraNotifyWarning(
                    visible = true,
                    message = "This notification stays until programmatically dismissed",
                    onDismiss = {},
                    duration = null,
                    dismissible = false
                )
            }
        }
    }
}

@Preview
@Composable
private fun VetraNotifyLongMessagePreview() {
    VetraTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
        ) {
            VetraNotifySuccess(
                visible = true,
                message = "This is a notification with a much longer message that might wrap to multiple lines in the notification banner",
                onDismiss = {}
            )
        }
    }
}

@Preview
@Composable
private fun VetraNotifyDarkPreview() {
    VetraTheme(darkMode = true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                VetraNotifyInfo(
                    visible = true,
                    message = "Dark mode info notification",
                    onDismiss = {}
                )

                VetraNotifySuccess(
                    visible = true,
                    message = "Dark mode success notification",
                    onDismiss = {}
                )

                VetraNotifyWarning(
                    visible = true,
                    message = "Dark mode warning notification",
                    onDismiss = {}
                )

                VetraNotifyDanger(
                    visible = true,
                    message = "Dark mode error notification",
                    onDismiss = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun VetraNotifyAnimationPreview() {
    VetraTheme {
        var visible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            while (true) {
                delay(1000)
                visible = true
                delay(3000)
                visible = false
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(VetraTheme.colors.canvas)
        ) {
            VetraNotifySuccess(
                visible = visible,
                message = "This notification demonstrates the slide-in animation",
                onDismiss = { visible = false }
            )
        }
    }
}
