# Menu

Menus display a list of choices on a temporary surface.

## Overview

VetraMenu provides a modern dropdown menu system with:

- Smooth animations
- Elegant styling
- Support for icons and dividers
- Accessible by default
- Lightweight and natural feel

```kotlin
var expanded by remember { mutableStateOf(false) }

VetraDropdownMenu(
    expanded = expanded,
    onDismissRequest = { expanded = false }
) {
    VetraMenuItem(
        text = "Settings",
        icon = Icons.Default.Settings,
        onClick = { }
    )
    VetraMenuDivider()
    VetraMenuItem(
        text = "Logout",
        icon = Icons.Default.Logout,
        onClick = { }
    )
}
```

## Basic Usage

=== "Simple Menu"

    ```kotlin
    var expanded by remember { mutableStateOf(false) }
    
    VetraDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        VetraMenuItem(text = "Option 1", onClick = { })
        VetraMenuItem(text = "Option 2", onClick = { })
        VetraMenuItem(text = "Option 3", onClick = { })
    }
    ```

=== "With Icons"

    ```kotlin
    VetraDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        VetraMenuItem(
            text = "Settings",
            icon = Icons.Default.Settings,
            onClick = { }
        )
        VetraMenuItem(
            text = "Help",
            icon = Icons.Default.Help,
            onClick = { }
        )
    }
    ```

=== "With Dividers"

    ```kotlin
    VetraDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        VetraMenuItem(text = "Edit", onClick = { })
        VetraMenuItem(text = "Share", onClick = { })
        VetraMenuDivider()
        VetraMenuItem(text = "Delete", onClick = { })
    }
    ```

## Common Patterns

### Context Menu

```kotlin
var expanded by remember { mutableStateOf(false) }

Box {
    VetraIconButton(
        onClick = { expanded = true },
        imageVector = Icons.Default.MoreVert,
        contentDescription = "More options"
    )
    
    VetraDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        VetraMenuItem(
            text = "Edit",
            icon = Icons.Default.Edit,
            onClick = {
                editItem()
                expanded = false
            }
        )
        VetraMenuItem(
            text = "Share",
            icon = Icons.Default.Share,
            onClick = {
                shareItem()
                expanded = false
            }
        )
        VetraMenuDivider()
        VetraMenuItem(
            text = "Delete",
            icon = Icons.Default.Delete,
            onClick = {
                deleteItem()
                expanded = false
            }
        )
    }
}
```

### User Menu

```kotlin
VetraDropdownMenu(
    expanded = expanded,
    onDismissRequest = { expanded = false }
) {
    VetraMenuItem(
        text = "Profile",
        icon = Icons.Default.Person,
        onClick = { navigateToProfile() }
    )
    VetraMenuItem(
        text = "Settings",
        icon = Icons.Default.Settings,
        onClick = { navigateToSettings() }
    )
    VetraMenuDivider()
    VetraMenuItem(
        text = "Logout",
        icon = Icons.Default.Logout,
        onClick = { logout() }
    )
}
```

## Best Practices

!!! success "Do"
    - ✅ Use menus for secondary actions
    - ✅ Group related items together
    - ✅ Use dividers to separate groups
    - ✅ Provide icons for clarity
    - ✅ Keep menu items concise

!!! failure "Don't"
    - ❌ Use menus for primary actions (use buttons)
    - ❌ Overcrowd menus with too many items
    - ❌ Use menus for navigation (use navigation bars)
    - ❌ Hide critical actions in menus

## Accessibility

- **Keyboard Navigation**: Full keyboard support
- **Screen Readers**: Proper semantic roles
- **Focus Management**: Proper focus handling
- **Dismissal**: Multiple dismissal methods

## API Reference

### VetraDropdownMenu

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `expanded` | `Boolean` | Required | Whether menu is visible |
| `onDismissRequest` | `() -> Unit` | Required | Called when menu should dismiss |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `offset` | `IntOffset` | `IntOffset(0, 0)` | Offset from anchor |
| `content` | `@Composable ColumnScope.() -> Unit` | Required | Menu items |

### VetraMenuItem

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `text` | `String` | Required | Menu item text |
| `onClick` | `() -> Unit` | Required | Callback when item is clicked |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether item is enabled |
| `icon` | `ImageVector?` | `null` | Optional leading icon |

### VetraMenuDivider

Divider for separating menu items. No parameters.

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Min Width | 180dp | Minimum menu width |
| Max Width | 280dp | Maximum menu width |
| Item Height | 48dp | Menu item height |
| Item Padding | 16dp | Internal padding |
| Icon Size | 20dp | Icon size |
| Animation Duration | 250ms | Transition animation |

---

**See Also:**

- [Icon Button](../input/icon-button.md) - For menu triggers
- [Buttons](../input/buttons.md) - For primary actions

