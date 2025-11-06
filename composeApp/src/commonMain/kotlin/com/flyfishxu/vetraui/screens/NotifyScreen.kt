package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.NotifyType
import com.flyfishxu.vetraui.core.VetraButton
import com.flyfishxu.vetraui.core.VetraGhostButton
import com.flyfishxu.vetraui.core.VetraNotifyHost
import com.flyfishxu.vetraui.core.VetraSecondaryButton
import com.flyfishxu.vetraui.core.rememberNotifyHostState
import com.flyfishxu.vetraui.core.theme.VetraTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Notify Screen
 *
 * Demonstrates the Vetra Notify component with various types and configurations
 */
@Composable
fun NotifyScreen() {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography
    val notifyHostState = rememberNotifyHostState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.canvas),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Basic Types",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )

                    Text(
                        "Four semantic notification types with distinct colors and icons",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        VetraButton(
                            onClick = {
                                coroutineScope.launch {
                                    notifyHostState.showInfo("This is an informational message")
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Info")
                        }

                        VetraSecondaryButton(
                            onClick = {
                                coroutineScope.launch {
                                    notifyHostState.showSuccess("Operation completed successfully!")
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Success")
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        VetraButton(
                            onClick = {
                                coroutineScope.launch {
                                    notifyHostState.showWarning("Please review your input")
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Warning")
                        }

                        VetraSecondaryButton(
                            onClick = {
                                coroutineScope.launch {
                                    notifyHostState.showDanger("An error occurred")
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Danger")
                        }
                    }
                }
            }

            // Duration Options
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Duration Options",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )

                    Text(
                        "Control how long notifications stay visible",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        VetraButton(
                            onClick = {
                                coroutineScope.launch {
                                    notifyHostState.showNotify(
                                        message = "Quick message (1s)",
                                        type = NotifyType.Info,
                                        duration = 1000L
                                    )
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("1 Second")
                        }

                        VetraButton(
                            onClick = {
                                coroutineScope.launch {
                                    notifyHostState.showNotify(
                                        message = "Standard message (3s)",
                                        type = NotifyType.Success,
                                        duration = 3000L
                                    )
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("3 Seconds")
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        VetraButton(
                            onClick = {
                                coroutineScope.launch {
                                    notifyHostState.showNotify(
                                        message = "Long message (5s)",
                                        type = NotifyType.Warning,
                                        duration = 5000L
                                    )
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("5 Seconds")
                        }

                        VetraButton(
                            onClick = {
                                coroutineScope.launch {
                                    notifyHostState.showNotify(
                                        message = "No auto-dismiss - click X to close",
                                        type = NotifyType.Danger,
                                        duration = null
                                    )
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Manual Only")
                        }
                    }
                }
            }

            // Advanced Options
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Advanced Options",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )

                    Text(
                        "Additional notification configurations",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    VetraButton(
                        onClick = {
                            coroutineScope.launch {
                                notifyHostState.showNotify(
                                    message = "This notification cannot be manually dismissed",
                                    type = NotifyType.Info,
                                    duration = 5000L,
                                    dismissible = false
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Non-dismissible (Auto-close only)")
                    }

                    VetraButton(
                        onClick = {
                            coroutineScope.launch {
                                notifyHostState.showNotify(
                                    message = "This is a long notification message that demonstrates text wrapping across multiple lines in the notification banner. The notification component handles long text gracefully.",
                                    type = NotifyType.Success,
                                    duration = 5000L
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Long Message")
                    }

                    VetraGhostButton(
                        onClick = {
                            coroutineScope.launch {
                                notifyHostState.clearAll()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Clear All Notifications")
                    }
                }
            }

            // Multiple Notifications
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Multiple Notifications",
                        style = typography.headingMd.copy(color = colors.textPrimary)
                    )

                    Text(
                        "Multiple notifications displayed simultaneously (max 50% screen height). Oldest auto-removed when limit exceeded.",
                        style = typography.bodyMd.copy(color = colors.textSecondary)
                    )

                    VetraButton(
                        onClick = {
                            coroutineScope.launch {
                                notifyHostState.showInfo("First notification")
                                notifyHostState.showSuccess("Second notification")
                                notifyHostState.showWarning("Third notification")
                                notifyHostState.showDanger("Fourth notification")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Show 4 Stacked Notifications")
                    }

                    VetraSecondaryButton(
                        onClick = {
                            coroutineScope.launch {
                                // Show many notifications to test height limit
                                repeat(10) { i ->
                                    notifyHostState.showInfo("Notification ${i + 1}")
                                    delay(100)
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Test Height Limit (10 notifications)")
                    }
                }
            }
        }

        // Notify Host - positioned at the top
        VetraNotifyHost(
            hostState = notifyHostState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}