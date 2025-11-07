# Icon Button

Icon buttons are icon-only buttons for compact actions and toolbars.

## Overview

VetraIconButton provides a modern icon button component with:

- Clean, modern aesthetics
- Clear hover/press states
- Accessible by default
- Consistent sizing
- Multiple variants

```kotlin
VetraIconButton(
    onClick = { },
    imageVector = Icons.Default.Settings,
    contentDescription = "Settings"
)
```

## Variants

=== "Standard"

    No background, minimal.
    
    ```kotlin
    VetraIconButton(
        onClick = { },
        imageVector = Icons.Default.Settings,
        contentDescription = "Settings"
    )
    ```

=== "Filled"

    Solid background for emphasis.
    
    ```kotlin
    VetraFilledIconButton(
        onClick = { },
        imageVector = Icons.Default.Favorite,
        contentDescription = "Like"
    )
    ```

=== "Outlined"

    Border only.
    
    ```kotlin
    VetraOutlinedIconButton(
        onClick = { },
        imageVector = Icons.Default.FavoriteBorder,
        contentDescription = "Like"
    )
    ```

## Common Patterns

### Toolbar Actions

```kotlin
Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    VetraIconButton(
        onClick = { },
        imageVector = Icons.Default.Search,
        contentDescription = "Search"
    )
    VetraIconButton(
        onClick = { },
        imageVector = Icons.Default.MoreVert,
        contentDescription = "More options"
    )
}
```

### With Badges

```kotlin
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

### Navigation

```kotlin
VetraIconButton(
    onClick = { navigateBack() },
    imageVector = Icons.Default.ArrowBack,
    contentDescription = "Back"
)
```

## Best Practices

!!! success "Do"
    - ✅ Use icon buttons for secondary actions
    - ✅ Provide clear content descriptions
    - ✅ Use recognizable icons
    - ✅ Group related icon buttons
    - ✅ Use filled variant for emphasis

!!! failure "Don't"
    - ❌ Use icon buttons for primary actions (use buttons)
    - ❌ Use unclear icons
    - ❌ Forget content descriptions
    - ❌ Make buttons too small

## Accessibility

- **Touch Targets**: Minimum 40×40dp built-in
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Proper content descriptions required
- **Keyboard Navigation**: Full keyboard support

!!! tip "Improve Accessibility"
    ```kotlin
    VetraIconButton(
        onClick = { },
        imageVector = Icons.Default.Settings,
        contentDescription = "Open settings"
    )
    ```

## API Reference

### VetraIconButton

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `onClick` | `() -> Unit` | Required | Callback when button is clicked |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether button is enabled |
| `content` | `@Composable () -> Unit` | Required | Button content (icon) |

Or with ImageVector:

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `onClick` | `() -> Unit` | Required | Callback when button is clicked |
| `imageVector` | `ImageVector` | Required | Icon to display |
| `contentDescription` | `String?` | Required | Content description for accessibility |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether button is enabled |

### VetraFilledIconButton

Same API as `VetraIconButton` with filled style.

### VetraOutlinedIconButton

Same API as `VetraIconButton` with outlined style.

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Size | 40dp | Button size |
| Icon Size | 20dp | Icon size |

---

**See Also:**

- [Buttons](buttons.md) - For primary actions
- [Badges](../display/badges.md) - For notification badges

