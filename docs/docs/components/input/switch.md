# Switch

Switches toggle between on/off states with smooth animations and elegant design.

## Overview

VetraSwitch provides a modern toggle component with:

- Smooth thumb transition animation
- Color transitions for states
- Subtle shadow on active state
- Clear visual feedback
- Accessible by default

```kotlin
var enabled by remember { mutableStateOf(false) }

VetraSwitch(
    checked = enabled,
    onCheckedChange = { enabled = it }
)
```

## Basic Usage

=== "Standard Switch"

    ```kotlin
    VetraSwitch(
        checked = checked,
        onCheckedChange = { checked = it }
    )
    ```

=== "With Label"

    ```kotlin
    VetraSwitchWithLabel(
        checked = enabled,
        onCheckedChange = { enabled = it },
        label = "Enable Notifications"
    )
    ```

## States

=== "Unchecked"

    ```kotlin
    VetraSwitch(
        checked = false,
        onCheckedChange = { }
    )
    ```

=== "Checked"

    ```kotlin
    VetraSwitch(
        checked = true,
        onCheckedChange = { }
    )
    ```

=== "Disabled Unchecked"

    ```kotlin
    VetraSwitch(
        checked = false,
        onCheckedChange = { },
        enabled = false
    )
    ```

=== "Disabled Checked"

    ```kotlin
    VetraSwitch(
        checked = true,
        onCheckedChange = { },
        enabled = false
    )
    ```

## Common Patterns

### Settings Screen

```kotlin
Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    VetraSwitchWithLabel(
        checked = notificationsEnabled,
        onCheckedChange = { notificationsEnabled = it },
        label = "Push Notifications"
    )
    
    VetraSwitchWithLabel(
        checked = darkModeEnabled,
        onCheckedChange = { darkModeEnabled = it },
        label = "Dark Mode"
    )
    
    VetraSwitchWithLabel(
        checked = autoSyncEnabled,
        onCheckedChange = { autoSyncEnabled = it },
        label = "Auto Sync",
        enabled = isOnline
    )
}
```

### Inline Switch

```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Text("Enable Feature")
    VetraSwitch(
        checked = featureEnabled,
        onCheckedChange = { featureEnabled = it }
    )
}
```

## Best Practices

!!! success "Do"
    - ✅ Use switches for binary on/off states
    - ✅ Provide clear labels describing what the switch controls
    - ✅ Group related switches together
    - ✅ Disable switches when prerequisites aren't met

!!! failure "Don't"
    - ❌ Use switches for multiple selections (use checkboxes)
    - ❌ Use switches for navigation (use buttons or tabs)
    - ❌ Use vague labels like "Enable" without context
    - ❌ Hide switches in nested menus

## Accessibility

- **Touch Targets**: Minimum 44×44dp built-in
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Semantic `Role.Switch` support
- **Keyboard Navigation**: Full keyboard support

!!! tip "Improve Accessibility"
    ```kotlin
    VetraSwitchWithLabel(
        checked = enabled,
        onCheckedChange = { enabled = it },
        label = "Enable notifications",
        enabled = hasPermission
    )
    ```

## API Reference

### VetraSwitch

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `checked` | `Boolean` | Required | Whether the switch is checked |
| `onCheckedChange` | `((Boolean) -> Unit)?` | `null` | Callback when switch is toggled |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the switch is interactive |

### VetraSwitchWithLabel

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `checked` | `Boolean` | Required | Whether the switch is checked |
| `onCheckedChange` | `(Boolean) -> Unit` | Required | Callback when switch is toggled |
| `label` | `String` | Required | Label text |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the switch is interactive |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Width | 48dp | Track width |
| Height | 28dp | Track height |
| Thumb Size | 22dp | Circular thumb |
| Animation Duration | 250ms | Smooth transition |

---

**See Also:**

- [Checkbox](checkbox.md) - For multiple selections
- [Radio Button](radio-button.md) - For single selection from group

