# Notify

Notifications provide non-blocking feedback messages that slide in from the top.

## Overview

VetraNotify provides a modern notification system with:

- Smooth slide-in animation from top
- Auto-dismiss with configurable duration
- Optional manual dismiss button
- Icon + message layout
- Adaptive shadows for depth
- Multiple notification types
- Single notification mode support

```kotlin
VetraNotifyHost {
    VetraNotifyInfo(
        message = "Operation completed successfully",
        visible = visible,
        onDismiss = { visible = false }
    )
}
```

## Variants

=== "Info"

    Informational messages with blue accent.
    
    ```kotlin
    VetraNotifyInfo(
        message = "Info message",
        visible = visible,
        onDismiss = { }
    )
    ```

=== "Success"

    Success confirmations with green accent.
    
    ```kotlin
    VetraNotifySuccess(
        message = "Success!",
        visible = visible,
        onDismiss = { }
    )
    ```

=== "Warning"

    Warning messages with amber accent.
    
    ```kotlin
    VetraNotifyWarning(
        message = "Warning message",
        visible = visible,
        onDismiss = { }
    )
    ```

=== "Danger"

    Error or critical messages with red accent.
    
    ```kotlin
    VetraNotifyDanger(
        message = "Error occurred",
        visible = visible,
        onDismiss = { }
    )
    ```

## Common Patterns

### Basic Notification

```kotlin
var showNotify by remember { mutableStateOf(false) }

VetraNotifyHost {
    VetraNotifySuccess(
        message = "Settings saved successfully",
        visible = showNotify,
        onDismiss = { showNotify = false }
    )
}

// Trigger notification
VetraButton(onClick = { showNotify = true }) {
    Text("Save")
}
```

### With Action

```kotlin
VetraNotifyInfo(
    message = "File uploaded",
    visible = visible,
    onDismiss = { },
    actionLabel = "View",
    onAction = { navigateToFile() }
)
```

### Custom Duration

```kotlin
VetraNotifySuccess(
    message = "Operation completed",
    visible = visible,
    onDismiss = { },
    duration = 5000L // 5 seconds
)
```

### Multiple Notifications

```kotlin
VetraNotifyHost {
    notifications.forEach { notification ->
        when (notification.type) {
            NotifyType.Success -> VetraNotifySuccess(
                message = notification.message,
                visible = notification.visible,
                onDismiss = { dismissNotification(notification.id) }
            )
            // Handle other types...
        }
    }
}
```

### Single Notification Mode

When enabled, only one notification is displayed at a time. New notifications will automatically dismiss existing ones with smooth animations.

```kotlin
val notifyHostState = rememberNotifyHostState()

// Enable single notification mode
notifyHostState.singleNotificationMode = true

VetraNotifyHost(hostState = notifyHostState) {
    // Your app content
    VetraNotifySuccess(
        message = "Settings saved",
        visible = visible,
        onDismiss = { visible = false }
    )
}
```

This mode is useful when you want to ensure users focus on one notification at a time, preventing notification overload.

## Best Practices

!!! success "Do"
    - ✅ Use notifications for non-critical feedback
    - ✅ Keep messages concise and clear
    - ✅ Use appropriate notification types
    - ✅ Provide actions when helpful
    - ✅ Auto-dismiss after reasonable time
    - ✅ Enable single notification mode when you want users to focus on one message at a time

!!! failure "Don't"
    - ❌ Use notifications for critical errors (use dialogs)
    - ❌ Overuse notifications
    - ❌ Make messages too long
    - ❌ Block important content

## Accessibility

- **Screen Readers**: Proper announcements
- **Color Contrast**: Meets WCAG AA standards
- **Dismissal**: Multiple dismissal methods
- **Focus Management**: Proper focus handling

## API Reference

### VetraNotifyHost

Container for notifications. Wrap your app content with this.

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `hostState` | `NotifyHostState?` | `null` | Optional state holder for advanced control |
| `modifier` | `Modifier` | `Modifier` | Modifier for the host container |
| `content` | `@Composable () -> Unit` | Required | App content |

### NotifyHostState

State holder for managing notification display behavior.

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `singleNotificationMode` | `Boolean` | `false` | When enabled, only one notification is shown at a time. New notifications automatically dismiss existing ones with animation. |

**Usage:**

```kotlin
val notifyHostState = rememberNotifyHostState()
notifyHostState.singleNotificationMode = true

VetraNotifyHost(hostState = notifyHostState) {
    // Your notifications
}
```

### All Notify Variants

All notify variants share similar API:

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `message` | `String` | Required | Notification message |
| `visible` | `Boolean` | Required | Whether notification is visible |
| `onDismiss` | `() -> Unit` | Required | Called when dismissed |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `actionLabel` | `String?` | `null` | Optional action label |
| `onAction` | `(() -> Unit)?` | `null` | Optional action callback |
| `duration` | `Long` | `NotifyDefaultDuration` | Auto-dismiss duration |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Horizontal Padding | 16dp | Internal spacing |
| Vertical Padding | 12dp | Internal spacing |
| Icon Size | 20dp | Icon size |
| Default Duration | 3000ms | Auto-dismiss time |

---

**See Also:**

- [Dialog](dialog.md) - For blocking confirmations
- [Loading](loading.md) - For loading states

