# Chips

Chips are compact elements that represent input, attribute, or action with optional icons and dismiss functionality.

## Overview

VetraChip provides a modern chip component with:

- Interactive with click support
- Optional leading icon
- Optional trailing action (like close button)
- Selection state support
- Clean, modern aesthetics
- Multiple variants for different use cases

```kotlin
VetraChip(
    label = "Kotlin",
    onClick = { /* action */ }
)
```

## Variants

=== "Standard"

    Solid background for default states.
    
    ```kotlin
    VetraChip(
        label = "Tag",
        onClick = { }
    )
    ```

=== "Outlined"

    Border only for subtle emphasis.
    
    ```kotlin
    VetraChipOutlined(
        label = "Tag",
        onClick = { }
    )
    ```

=== "Assist"

    Action chip for suggestions or quick actions.
    
    ```kotlin
    VetraChipAssist(
        label = "Add to cart",
        onClick = { }
    )
    ```

=== "Filter"

    Filter chip with selection state.
    
    ```kotlin
    VetraChipFilter(
        label = "All",
        selected = true,
        onClick = { }
    )
    ```

## Common Patterns

### Tags with Dismiss

```kotlin
var tags by remember { mutableStateOf(listOf("Design", "Development", "UI/UX")) }

Row(
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    modifier = Modifier.padding(8.dp)
) {
    tags.forEach { tag ->
        VetraChip(
            label = tag,
            trailingIcon = Icons.Default.Close,
            onTrailingIconClick = {
                tags = tags - tag
            }
        )
    }
}
```

### Filter Chips

```kotlin
var selectedFilter by remember { mutableStateOf("All") }

Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    VetraChipFilter(
        label = "All",
        selected = selectedFilter == "All",
        onClick = { selectedFilter = "All" }
    )
    VetraChipFilter(
        label = "Active",
        selected = selectedFilter == "Active",
        onClick = { selectedFilter = "Active" }
    )
    VetraChipFilter(
        label = "Completed",
        selected = selectedFilter == "Completed",
        onClick = { selectedFilter = "Completed" }
    )
}
```

### Action Chips

```kotlin
Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    VetraChipAssist(
        label = "Add to cart",
        onClick = { addToCart() },
        leadingIcon = Icons.Default.Add
    )
    VetraChipAssist(
        label = "Share",
        onClick = { share() }
    )
}
```

### With Icons

```kotlin
VetraChip(
    label = "Kotlin",
    leadingIcon = Icons.Default.Code,
    onClick = { }
)

VetraChip(
    label = "Removable",
    trailingIcon = Icons.Default.Close,
    onTrailingIconClick = { remove() }
)
```

## Best Practices

!!! success "Do"
    - ✅ Use chips for tags, filters, or compact actions
    - ✅ Provide clear, concise labels
    - ✅ Use filter chips for selection states
    - ✅ Use assist chips for quick actions
    - ✅ Allow dismissal when appropriate

!!! failure "Don't"
    - ❌ Use chips for primary actions (use buttons)
    - ❌ Use chips for navigation (use tabs)
    - ❌ Make chips too small to read
    - ❌ Overuse chips in a single view

## Accessibility

- **Touch Targets**: Minimum height meets accessibility standards
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Proper semantic roles
- **Keyboard Navigation**: Full keyboard support

## API Reference

### VetraChip

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `label` | `String` | Required | Chip text label |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `onClick` | `(() -> Unit)?` | `null` | Optional click handler |
| `enabled` | `Boolean` | `true` | Whether the chip is enabled |
| `leadingIcon` | `ImageVector?` | `null` | Optional leading icon |
| `trailingIcon` | `ImageVector?` | `null` | Optional trailing icon |
| `onTrailingIconClick` | `(() -> Unit)?` | `null` | Click handler for trailing icon |

### VetraChipOutlined

Same API as `VetraChip` with outlined style.

### VetraChipAssist

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `label` | `String` | Required | Chip text label |
| `onClick` | `() -> Unit` | Required | Click handler |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the chip is enabled |
| `leadingIcon` | `ImageVector?` | `null` | Optional leading icon |

### VetraChipFilter

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `label` | `String` | Required | Chip text label |
| `selected` | `Boolean` | Required | Whether the chip is selected |
| `onClick` | `() -> Unit` | Required | Click handler to toggle selection |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the chip is enabled |
| `leadingIcon` | `ImageVector?` | `null` | Optional leading icon (shown when not selected) |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Height | 32dp | Chip height |
| Horizontal Padding | 12dp | Internal spacing |
| Vertical Padding | 6dp | Internal spacing |
| Icon Size | 18dp | Leading icon size |
| Trailing Icon Size | 16dp | Trailing icon size |

---

**See Also:**

- [Badges](badges.md) - For status indicators
- [Buttons](../input/buttons.md) - For primary actions

