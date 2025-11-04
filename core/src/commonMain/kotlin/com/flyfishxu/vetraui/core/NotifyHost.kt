package com.flyfishxu.vetraui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Vetra Notify Host State
 *
 * State holder for managing notifications in the application.
 * Use this to show and manage multiple notifications in a queue.
 *
 */
@Stable
class NotifyHostState {
    private val mutex = Mutex()
    private val _currentNotify = mutableStateOf<NotifyItem?>(null)
    private val notifyQueue = mutableStateListOf<NotifyItem>()

    /**
     * The currently visible notification
     */
    val currentNotify: NotifyItem?
        get() = _currentNotify.value

    /**
     * Show a notification with the given parameters
     *
     * @param message The notification message
     * @param type The type of notification (Info, Success, Warning, Danger)
     * @param duration Duration in milliseconds before auto-dismiss (null for no auto-dismiss)
     * @param dismissible Whether the notification can be manually dismissed
     * @return The ID of the created notification
     */
    @OptIn(ExperimentalUuidApi::class)
    suspend fun showNotify(
        message: String,
        type: NotifyType = NotifyType.Info,
        duration: Long? = NotifyDefaultDuration,
        dismissible: Boolean = true
    ): String {
        val id = Uuid.random().toString()
        val notifyItem = NotifyItem(
            id = id,
            data = NotifyData(
                message = message,
                type = type,
                duration = duration,
                dismissible = dismissible
            )
        )

        mutex.withLock {
            if (_currentNotify.value == null) {
                _currentNotify.value = notifyItem
            } else {
                notifyQueue.add(notifyItem)
            }
        }

        return id
    }

    /**
     * Show an info notification
     *
     * @param message The notification message
     * @param duration Auto-dismiss duration (null for no auto-dismiss)
     * @param dismissible Whether manual dismiss is allowed
     * @return The ID of the created notification
     */
    suspend fun showInfo(
        message: String,
        duration: Long? = NotifyDefaultDuration,
        dismissible: Boolean = true
    ): String = showNotify(message, NotifyType.Info, duration, dismissible)

    /**
     * Show a success notification
     *
     * @param message The notification message
     * @param duration Auto-dismiss duration (null for no auto-dismiss)
     * @param dismissible Whether manual dismiss is allowed
     * @return The ID of the created notification
     */
    suspend fun showSuccess(
        message: String,
        duration: Long? = NotifyDefaultDuration,
        dismissible: Boolean = true
    ): String = showNotify(message, NotifyType.Success, duration, dismissible)

    /**
     * Show a warning notification
     *
     * @param message The notification message
     * @param duration Auto-dismiss duration (null for no auto-dismiss)
     * @param dismissible Whether manual dismiss is allowed
     * @return The ID of the created notification
     */
    suspend fun showWarning(
        message: String,
        duration: Long? = NotifyDefaultDuration,
        dismissible: Boolean = true
    ): String = showNotify(message, NotifyType.Warning, duration, dismissible)

    /**
     * Show a danger/error notification
     *
     * @param message The notification message
     * @param duration Auto-dismiss duration (null for no auto-dismiss)
     * @param dismissible Whether manual dismiss is allowed
     * @return The ID of the created notification
     */
    suspend fun showDanger(
        message: String,
        duration: Long? = NotifyDefaultDuration,
        dismissible: Boolean = true
    ): String = showNotify(message, NotifyType.Danger, duration, dismissible)

    /**
     * Dismiss the current notification
     */
    suspend fun dismissCurrent() {
        mutex.withLock {
            _currentNotify.value = null
            if (notifyQueue.isNotEmpty()) {
                _currentNotify.value = notifyQueue.removeFirst()
            }
        }
    }

    /**
     * Dismiss a specific notification by ID
     *
     * @param id The ID of the notification to dismiss
     */
    suspend fun dismiss(id: String) {
        mutex.withLock {
            if (_currentNotify.value?.id == id) {
                dismissCurrent()
            } else {
                notifyQueue.removeAll { it.id == id }
            }
        }
    }

    /**
     * Clear all notifications (current and queued)
     */
    suspend fun clearAll() {
        mutex.withLock {
            _currentNotify.value = null
            notifyQueue.clear()
        }
    }
}

/**
 * Internal notification item with unique ID
 */
data class NotifyItem(
    val id: String,
    val data: NotifyData
)

/**
 * Remember a NotifyHostState instance
 */
@Composable
fun rememberNotifyHostState(): NotifyHostState {
    return remember { NotifyHostState() }
}

/**
 * Vetra Notify Host
 *
 * Container for displaying notifications managed by NotifyHostState.
 * Place this at the top level of your screen or app to show notifications.
 *
 * @param hostState The state holder managing notifications
 * @param modifier Modifier for the host container
 */
@Composable
fun VetraNotifyHost(
    hostState: NotifyHostState,
    modifier: Modifier = Modifier
) {
    val currentNotify = hostState.currentNotify

    Box(modifier = modifier.fillMaxWidth()) {
        VetraNotify(
            visible = currentNotify != null,
            data = currentNotify?.data ?: NotifyData(message = ""),
            onDismiss = {
                // Use a simple state update instead of launching a coroutine
                // The dismissCurrent will be handled by the LaunchedEffect in VetraNotify
                kotlinx.coroutines.MainScope().launch {
                    hostState.dismissCurrent()
                }
            }
        )
    }
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraNotifyHostPreview() {
    VetraTheme {
        val notifyHostState = rememberNotifyHostState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas)
        ) {
            // Sample content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(VetraTheme.colors.canvas),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Main Content",
                    style = VetraTheme.typography.headingLg.copy(
                        color = VetraTheme.colors.textPrimary
                    )
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    VetraButton(onClick = {
                        kotlinx.coroutines.MainScope().launch {
                            notifyHostState.showInfo("This is an info notification")
                        }
                    }) {
                        Text("Show Info")
                    }

                    VetraButton(onClick = {
                        kotlinx.coroutines.MainScope().launch {
                            notifyHostState.showSuccess("Operation completed successfully!")
                        }
                    }) {
                        Text("Show Success")
                    }

                    VetraButton(onClick = {
                        kotlinx.coroutines.MainScope().launch {
                            notifyHostState.showWarning("Please review your changes")
                        }
                    }) {
                        Text("Show Warning")
                    }

                    VetraButton(onClick = {
                        kotlinx.coroutines.MainScope().launch {
                            notifyHostState.showDanger("An error occurred")
                        }
                    }) {
                        Text("Show Error")
                    }
                }
            }

            // Notify host overlay
            VetraNotifyHost(
                hostState = notifyHostState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Preview
@Composable
private fun VetraNotifyHostDarkPreview() {
    VetraTheme(darkMode = true) {
        val notifyHostState = rememberNotifyHostState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(VetraTheme.colors.canvas),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Dark Mode Preview",
                    style = VetraTheme.typography.headingLg.copy(
                        color = VetraTheme.colors.textPrimary
                    )
                )

                VetraButton(onClick = {
                    kotlinx.coroutines.MainScope().launch {
                        notifyHostState.showSuccess("Dark mode notification")
                    }
                }) {
                    Text("Show Notification")
                }
            }

            VetraNotifyHost(
                hostState = notifyHostState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

