# Badges

Badges are small status indicators that display counts, labels, or status information.

## Overview

VetraBadge provides a modern badge component with:

- Compact size with clear readability
- Semantic color variants
- Consistent with Vetra design system
- Perfect for status indicators and counts
- Multiple variants for different use cases

```kotlin
VetraBadge("New")
```

## Variants

=== "Standard"

    Brand color for default states.
    
    ```kotlin
    VetraBadge("New")
    ```

=== "Secondary"

    Accent color for alternative emphasis.
    
    ```kotlin
    VetraBadgeSecondary("Featured")
    ```

=== "Success"

    Green for positive states.
    
    ```kotlin
    VetraBadgeSuccess("Active")
    ```

=== "Warning"

    Amber for cautionary states.
    
    ```kotlin
    VetraBadgeWarning("Pending")
    ```

=== "Danger"

    Red for errors or alerts.
    
    ```kotlin
    VetraBadgeDanger("Error")
    ```

=== "Info"

    Cyan for informational states.
    
    ```kotlin
    VetraBadgeInfo("Info")
    ```

=== "Outlined"

    Border only for subtle emphasis.
    
    ```kotlin
    VetraBadgeOutlined("Beta")
    ```

=== "Dot"

    Small dot indicator for notifications.
    
    ```kotlin
    VetraBadgeDot(count = 5)
    ```

## Common Patterns

### Status Indicators

```kotlin
Row(
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Text("Status:")
    VetraBadgeSuccess("Online")
}

Row(
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Text("Version:")
    VetraBadgeOutlined("Beta")
}
```

### Count Badges

```kotlin
Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    VetraBadge("1")
    VetraBadge("12")
    VetraBadge("99+")
    VetraBadgeSuccess("5")
    VetraBadgeDanger("3")
}
```

### Notification Badges

```kotlin
Row(
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Text("Notifications")
    VetraBadgeDot(count = 3)
}

// On icon button
Box {
    VetraIconButton(
        onClick = { },
        imageVector = Icons.Default.Notifications,
        contentDescription = "Notifications"
    )
    VetraBadgeDot(
        count = 5,
        modifier = Modifier.align(Alignment.TopEnd)
    )
}
```

### Category Tags

```kotlin
Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    VetraBadge("New")
    VetraBadgeSecondary("Featured")
    VetraBadgeInfo("Updated")
}
```

## Best Practices

!!! success "Do"
    - ✅ Use badges for status, counts, or labels
    - ✅ Choose semantic colors appropriately
    - ✅ Keep badge text concise
    - ✅ Use dot badges for notifications
    - ✅ Show "99+" for counts over 99

!!! failure "Don't"
    - ❌ Use badges for actions (use buttons or chips)
    - ❌ Use badges for navigation (use tabs)
    - ❌ Make badges too small to read
    - ❌ Overuse badges in a single view

## Accessibility

- **Color Contrast**: All variants meet WCAG AA standards
- **Screen Readers**: Proper content description
- **Text Size**: Readable at minimum size

## API Reference

### All Badge Variants

All badge variants share similar API:

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `text` | `String` | Required | Badge text content |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |

### VetraBadgeDot

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `count` | `Int?` | `null` | Optional count to display (null for empty dot) |
| `color` | `Color?` | `null` | Optional color (defaults to danger color) |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Horizontal Padding | 6dp | Internal spacing |
| Vertical Padding | 2dp | Internal spacing |
| Dot Size | 8dp | Empty dot size |

---

**See Also:**

- [Chips](chips.md) - For interactive tags
- [Icon Button](../input/icon-button.md) - For badge placement

