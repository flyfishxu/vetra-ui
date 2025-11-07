# Dialog

Dialogs present focused content and actions in an overlay with smooth animations.

## Overview

VetraDialog provides a modern dialog component with:

- Elegant scale + fade animations
- Adaptive shadows for depth
- Responsive sizing
- Accessibility-focused
- Seamless light/dark mode support

```kotlin
var showDialog by remember { mutableStateOf(false) }

VetraDialog(
    visible = showDialog,
    onDismissRequest = { showDialog = false },
    title = { Text("Confirm") },
    content = {
        Text("Are you sure?")
    },
    confirmButton = {
        VetraButton(onClick = { showDialog = false }) {
            Text("Confirm")
        }
    }
)
```

## Variants

=== "Standard"

    Full dialog with title, content, and actions.
    
    ```kotlin
    VetraDialog(
        visible = visible,
        onDismissRequest = { },
        title = { Text("Title") },
        content = { Text("Content") },
        confirmButton = {
            VetraButton(onClick = { }) { Text("OK") }
        }
    )
    ```

=== "Alert"

    Quick alert with message and confirm/dismiss actions.
    
    ```kotlin
    VetraAlertDialog(
        visible = visible,
        onDismissRequest = { },
        title = "Delete Item",
        message = "This action cannot be undone.",
        onConfirm = { }
    )
    ```

=== "Custom"

    Fully customizable content.
    
    ```kotlin
    VetraCustomDialog(
        visible = visible,
        onDismissRequest = { },
        content = {
            // Custom content
        }
    )
    ```

## Common Patterns

### Confirmation Dialog

```kotlin
var showDialog by remember { mutableStateOf(false) }

VetraAlertDialog(
    visible = showDialog,
    onDismissRequest = { showDialog = false },
    title = "Delete Item",
    message = "Are you sure you want to delete this item? This action cannot be undone.",
    confirmText = "Delete",
    dismissText = "Cancel",
    isDanger = true,
    onConfirm = {
        deleteItem()
        showDialog = false
    }
)
```

### Form Dialog

```kotlin
VetraDialog(
    visible = visible,
    onDismissRequest = { },
    title = { Text("Edit Profile") },
    content = {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            VetraTextField(
                value = name,
                onValueChange = { name = it },
                label = "Name"
            )
            VetraTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email"
            )
        }
    },
    confirmButton = {
        VetraButton(onClick = { save() }) {
            Text("Save")
        }
    },
    dismissButton = {
        VetraOutlinedButton(onClick = { }) {
            Text("Cancel")
        }
    }
)
```

### Custom Dialog

```kotlin
VetraCustomDialog(
    visible = visible,
    onDismissRequest = { },
    content = {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Custom Content", style = VetraTheme.typography.headingLg)
            // Custom layout
        }
    }
)
```

## Best Practices

!!! success "Do"
    - ✅ Use dialogs for important confirmations
    - ✅ Provide clear titles and messages
    - ✅ Use danger buttons for destructive actions
    - ✅ Allow dismissal via backdrop or cancel button
    - ✅ Keep dialogs focused and concise

!!! failure "Don't"
    - ❌ Overuse dialogs (use notifications for simple messages)
    - ❌ Block critical user flows
    - ❌ Use dialogs for navigation
    - ❌ Make dialogs too large

## Accessibility

- **Focus Management**: Proper focus handling
- **Screen Readers**: Proper semantic roles
- **Keyboard Navigation**: Full keyboard support
- **Dismissal**: Multiple dismissal methods

## API Reference

### VetraDialog

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `visible` | `Boolean` | Required | Whether dialog is visible |
| `onDismissRequest` | `() -> Unit` | Required | Called when user dismisses |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `title` | `@Composable (() -> Unit)?` | `null` | Optional title |
| `content` | `@Composable ColumnScope.() -> Unit` | Required | Dialog content |
| `confirmButton` | `@Composable RowScope.() -> Unit` | Required | Confirm action button |
| `dismissButton` | `@Composable RowScope.() -> Unit?` | `null` | Optional dismiss button |
| `properties` | `DialogProperties` | `DialogProperties()` | Dialog properties |

### VetraAlertDialog

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `visible` | `Boolean` | Required | Whether dialog is visible |
| `onDismissRequest` | `() -> Unit` | Required | Called when user dismisses |
| `title` | `String` | Required | Alert title |
| `message` | `String` | Required | Alert message |
| `onConfirm` | `() -> Unit` | Required | Called when confirmed |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `confirmText` | `String` | `"Confirm"` | Confirm button text |
| `dismissText` | `String` | `"Cancel"` | Dismiss button text |
| `isDanger` | `Boolean` | `false` | Whether this is a dangerous action |
| `properties` | `DialogProperties` | `DialogProperties()` | Dialog properties |

### VetraCustomDialog

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `visible` | `Boolean` | Required | Whether dialog is visible |
| `onDismissRequest` | `() -> Unit` | Required | Called when user dismisses |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `content` | `@Composable () -> Unit` | Required | Custom content |
| `properties` | `DialogProperties` | `DialogProperties()` | Dialog properties |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Min Width | 280dp | Minimum dialog width |
| Max Width | 560dp | Maximum dialog width |
| Padding | 24dp | Internal padding |
| Animation Duration | 300ms | Transition animation |

---

**See Also:**

- [Notify](notify.md) - For non-blocking messages
- [Buttons](../input/buttons.md) - For dialog actions

