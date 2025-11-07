package com.flyfishxu.vetraui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
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
 * Supports displaying multiple notifications simultaneously with height limit.
 * Automatically removes oldest notifications when exceeding screen height limit.
 *
 */
@Stable
class NotifyHostState {
    private val mutex = Mutex()
    private val _notifications = mutableStateListOf<NotifyItem>()
    private val _visibilityMap = mutableStateMapOf<String, Boolean>()
    private val _heightMap = mutableStateMapOf<String, Int>()
    private val internalScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    internal var maxHeightPx = 0

    /**
     * Single notification mode - when enabled, only one notification is shown at a time.
     * New notifications will automatically dismiss the existing one with animation.
     */
    var singleNotificationMode: Boolean = false

    /**
     * All currently visible notifications
     */
    val notifications: List<NotifyItem>
        get() = _notifications.toList()

    /**
     * Get visibility state for a notification
     */
    fun isVisible(id: String): Boolean = _visibilityMap[id] ?: false

    /**
     * Internal: Set notification height
     */
    internal fun setNotificationHeight(id: String, height: Int) {
        _heightMap[id] = height
    }

    /**
     * Calculate total height of all notifications
     */
    private fun calculateTotalHeight(): Int {
        return _notifications.sumOf { item ->
            _heightMap[item.id] ?: 80 // Use measured height or default estimate
        }
    }

    /**
     * Check if adding a new notification would exceed height limit
     * and remove oldest notifications if needed
     */
    private suspend fun checkAndRemoveOldestIfNeeded() {
        if (maxHeightPx <= 0) return

        val estimatedNewHeight = 80 // Estimated height for new notification

        // Remove oldest notifications until we have space for the new one
        while (_notifications.isNotEmpty()) {
            val totalHeight = calculateTotalHeight() + estimatedNewHeight
            if (totalHeight <= maxHeightPx) break

            val oldestId = _notifications.first().id
            dismiss(oldestId)
        }
    }

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

        // If single notification mode is enabled, dismiss all existing notifications first
        if (singleNotificationMode && _notifications.isNotEmpty()) {
            val existingIds = _notifications.map { it.id }
            
            // Trigger exit animation for all existing notifications at once
            mutex.withLock {
                existingIds.forEach { existingId ->
                    _visibilityMap[existingId] = false
                }
            }
            
            // Don't wait for animation, let them animate out while new one comes in
            // Remove after animation completes (NotifyExitDuration = 250ms, using 300ms for safety)
            internalScope.launch {
                delay(300)
                mutex.withLock {
                    existingIds.forEach { existingId ->
                        _notifications.removeAll { it.id == existingId }
                        _visibilityMap.remove(existingId)
                        _heightMap.remove(existingId)
                    }
                }
            }
        } else {
            // Check if we need to remove old notifications (multi-notification mode)
            if (maxHeightPx > 0) {
                checkAndRemoveOldestIfNeeded()
            }
        }

        mutex.withLock {
            _notifications.add(notifyItem)
            _visibilityMap[id] = false
        }

        // Delay to allow composition, then trigger enter animation
        delay(50)
        mutex.withLock {
            _visibilityMap[id] = true
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
     * Dismiss a specific notification by ID
     *
     * @param id The ID of the notification to dismiss
     */
    suspend fun dismiss(id: String) {
        // First, trigger exit animation
        mutex.withLock {
            _visibilityMap[id] = false
        }

        // Wait for exit animation to complete
        delay(300)

        // Then remove from lists
        mutex.withLock {
            _notifications.removeAll { it.id == id }
            _visibilityMap.remove(id)
            _heightMap.remove(id)
        }
    }

    /**
     * Clear all notifications
     */
    suspend fun clearAll() {
        // Trigger exit animations for all
        mutex.withLock {
            _notifications.forEach { item ->
                _visibilityMap[item.id] = false
            }
        }

        // Wait for animations
        delay(300)

        // Clear all data
        mutex.withLock {
            _notifications.clear()
            _visibilityMap.clear()
            _heightMap.clear()
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
 * Supports two display modes:
 * - Multiple notifications: Shows multiple notifications stacked (max 50% of screen height)
 * - Single notification mode: Shows only one notification at a time. When enabled via
 *   `hostState.singleNotificationMode = true`, new notifications will automatically
 *   dismiss existing ones with smooth animations.
 *
 * @param hostState The state holder managing notifications
 * @param modifier Modifier for the host container
 */
@Composable
fun VetraNotifyHost(
    hostState: NotifyHostState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val maxHeight = maxHeight
        val density = LocalDensity.current

        // Set max height limit (50% of screen height)
        LaunchedEffect(maxHeight) {
            hostState.maxHeightPx = with(density) { (maxHeight / 2).roundToPx() }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = maxHeight / 2),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            hostState.notifications.forEach { notifyItem ->
                key(notifyItem.id) {
                    val isVisible = hostState.isVisible(notifyItem.id)

                    VetraNotify(
                        visible = isVisible,
                        data = notifyItem.data,
                        onDismiss = {
                            scope.launch {
                                hostState.dismiss(notifyItem.id)
                            }
                        },
                        modifier = Modifier.onSizeChanged { size ->
                            hostState.setNotificationHeight(notifyItem.id, size.height)
                        }
                    )
                }
            }
        }
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
