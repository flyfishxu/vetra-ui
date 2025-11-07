# Navigation Bar

Navigation bars provide primary navigation at the bottom of the screen with icon and label support.

## Overview

VetraNavigationBar provides a modern bottom navigation bar with:

- Animated pill indicator with smooth transitions
- Icon scale animations for selected state
- Subtle label fade effects
- Elegant press feedback
- Clean, minimal aesthetic

```kotlin
var selectedIndex by remember { mutableStateOf(0) }

VetraNavigationBar {
    VetraNavigationBarItem(
        selected = selectedIndex == 0,
        onClick = { selectedIndex = 0 },
        icon = { Icon(Icons.Default.Home, null) },
        label = { Text("Home") }
    )
    // More items...
}
```

## Basic Usage

=== "With Labels"

    ```kotlin
    VetraNavigationBar {
        VetraNavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )
        VetraNavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { selectedIndex = 1 },
            icon = { Icon(Icons.Default.Search, null) },
            label = { Text("Search") }
        )
    }
    ```

=== "Icon Only"

    ```kotlin
    VetraNavigationBar {
        VetraNavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = { Icon(Icons.Default.Home, null) }
        )
    }
    ```

=== "With Selected Icons"

    ```kotlin
    VetraNavigationBar {
        VetraNavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = { Icon(Icons.Outlined.Home, null) },
            selectedIcon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )
    }
    ```

## Common Patterns

### Standard Navigation

```kotlin
var selectedIndex by remember { mutableStateOf(0) }

VetraNavigationBar {
    VetraNavigationBarItem(
        selected = selectedIndex == 0,
        onClick = { selectedIndex = 0 },
        icon = { Icon(Icons.Default.Home, null) },
        label = { Text("Home") }
    )
    VetraNavigationBarItem(
        selected = selectedIndex == 1,
        onClick = { selectedIndex = 1 },
        icon = { Icon(Icons.Default.Search, null) },
        label = { Text("Search") }
    )
    VetraNavigationBarItem(
        selected = selectedIndex == 2,
        onClick = { selectedIndex = 2 },
        icon = { Icon(Icons.Default.Favorite, null) },
        label = { Text("Favorites") }
    )
    VetraNavigationBarItem(
        selected = selectedIndex == 3,
        onClick = { selectedIndex = 3 },
        icon = { Icon(Icons.Default.Person, null) },
        label = { Text("Profile") }
    )
}
```

### With Badges

```kotlin
VetraNavigationBar {
    Box {
        VetraNavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = { Icon(Icons.Default.Notifications, null) },
            label = { Text("Notifications") }
        )
        VetraBadgeDot(
            count = 5,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}
```

## Best Practices

!!! success "Do"
    - ✅ Use 3-5 navigation items
    - ✅ Use clear, recognizable icons
    - ✅ Provide labels for clarity
    - ✅ Keep navigation consistent across screens
    - ✅ Use badges for notifications

!!! failure "Don't"
    - ❌ Use more than 5 items
    - ❌ Use navigation bars for actions (use top app bar)
    - ❌ Hide navigation on scroll
    - ❌ Use unclear icons

## Accessibility

- **Touch Targets**: All items meet minimum 44×44dp
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Proper content descriptions
- **Keyboard Navigation**: Full keyboard support

## API Reference

### VetraNavigationBar

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `content` | `@Composable RowScope.() -> Unit` | Required | Navigation items |

### VetraNavigationBarItem

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `selected` | `Boolean` | Required | Whether this item is selected |
| `onClick` | `() -> Unit` | Required | Callback when item is clicked |
| `icon` | `@Composable () -> Unit` | Required | Icon to display |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the item is enabled |
| `label` | `@Composable (() -> Unit)?` | `null` | Optional label text |
| `selectedIcon` | `@Composable (() -> Unit)?` | `null` | Optional different icon when selected |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Height | 72dp | Navigation bar height |
| Item Size | 56dp | Item touch target |
| Icon Size | 24dp | Icon size |
| Animation Duration | 300ms | Transition animation |

---

**See Also:**

- [Top App Bar](top-app-bar.md) - For top navigation
- [Tabs](tabs.md) - For content switching

