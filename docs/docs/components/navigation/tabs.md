# Tabs

Tabs organize content into separate views that users can switch between.

## Overview

VetraTab provides a modern tab component with:

- Smooth indicator animation
- Color transitions for selected/unselected states
- Clean, minimal aesthetic
- Support for text, icons, or both
- Accessible by default

```kotlin
var selectedTabIndex by remember { mutableStateOf(0) }

VetraTabRow(selectedTabIndex = selectedTabIndex) {
    VetraTab(
        selected = selectedTabIndex == 0,
        onClick = { selectedTabIndex = 0 },
        text = { Text("Tab 1") }
    )
    // More tabs...
}
```

## Variants

=== "Text Only"

    Tabs with text labels.
    
    ```kotlin
    VetraTabRow(selectedTabIndex = selectedTabIndex) {
        VetraTab(
            selected = selectedTabIndex == 0,
            onClick = { selectedTabIndex = 0 },
            text = { Text("Home") }
        )
    }
    ```

=== "Icon Only"

    Tabs with icons only.
    
    ```kotlin
    VetraTabRow(selectedTabIndex = selectedTabIndex) {
        VetraTab(
            selected = selectedTabIndex == 0,
            onClick = { selectedTabIndex = 0 },
            icon = { Icon(Icons.Default.Home, null) }
        )
    }
    ```

=== "Icon and Text"

    Tabs with both icon and text.
    
    ```kotlin
    VetraTabRow(selectedTabIndex = selectedTabIndex) {
        VetraTab(
            selected = selectedTabIndex == 0,
            onClick = { selectedTabIndex = 0 },
            icon = { Icon(Icons.Default.Home, null) },
            text = { Text("Home") }
        )
    }
    ```

## Common Patterns

### Standard Tabs

```kotlin
var selectedTabIndex by remember { mutableStateOf(0) }

Column {
    VetraTabRow(selectedTabIndex = selectedTabIndex) {
        VetraTab(
            selected = selectedTabIndex == 0,
            onClick = { selectedTabIndex = 0 },
            text = { Text("All") }
        )
        VetraTab(
            selected = selectedTabIndex == 1,
            onClick = { selectedTabIndex = 1 },
            text = { Text("Active") }
        )
        VetraTab(
            selected = selectedTabIndex == 2,
            onClick = { selectedTabIndex = 2 },
            text = { Text("Completed") }
        )
    }
    
    // Tab content
    when (selectedTabIndex) {
        0 -> AllContent()
        1 -> ActiveContent()
        2 -> CompletedContent()
    }
}
```

### Scrollable Tabs

```kotlin
VetraScrollableTabRow(selectedTabIndex = selectedTabIndex) {
    repeat(10) { index ->
        VetraTab(
            selected = selectedTabIndex == index,
            onClick = { selectedTabIndex = index },
            text = { Text("Tab ${index + 1}") }
        )
    }
}
```

### Icon Tabs

```kotlin
VetraTabRow(selectedTabIndex = selectedTabIndex) {
    VetraTab(
        selected = selectedTabIndex == 0,
        onClick = { selectedTabIndex = 0 },
        icon = { Icon(Icons.Default.Home, null) }
    )
    VetraTab(
        selected = selectedTabIndex == 1,
        onClick = { selectedTabIndex = 1 },
        icon = { Icon(Icons.Default.Search, null) }
    )
    VetraTab(
        selected = selectedTabIndex == 2,
        onClick = { selectedTabIndex = 2 },
        icon = { Icon(Icons.Default.Favorite, null) }
    )
}
```

## Best Practices

!!! success "Do"
    - ✅ Use tabs for related content views
    - ✅ Keep tab labels concise
    - ✅ Use scrollable tabs for many options
    - ✅ Show clear selected state
    - ✅ Limit to 2-5 tabs when possible

!!! failure "Don't"
    - ❌ Use tabs for navigation (use navigation bar)
    - ❌ Use tabs for actions (use buttons)
    - ❌ Use too many tabs
    - ❌ Make tabs too small

## Accessibility

- **Touch Targets**: All tabs meet minimum 48dp height
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Proper semantic roles
- **Keyboard Navigation**: Full keyboard support

## API Reference

### VetraTabRow

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `selectedTabIndex` | `Int` | Required | Index of selected tab |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `containerColor` | `Color` | `VetraTheme.colors.canvas` | Background color |
| `contentColor` | `Color` | `VetraTheme.colors.textSecondary` | Unselected tab color |
| `indicatorColor` | `Color` | `VetraTheme.colors.brand` | Indicator color |
| `tabs` | `@Composable () -> Unit` | Required | Tab content |

### VetraScrollableTabRow

Same API as `VetraTabRow` but scrollable.

### VetraTab

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `selected` | `Boolean` | Required | Whether tab is selected |
| `onClick` | `() -> Unit` | Required | Callback when tab is clicked |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether tab is enabled |
| `text` | `@Composable (() -> Unit)?` | `null` | Optional text label |
| `icon` | `@Composable (() -> Unit)?` | `null` | Optional icon |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Height | 48dp | Tab height |
| Min Width | 90dp | Minimum tab width |
| Max Width | 360dp | Maximum tab width |
| Indicator Height | 3dp | Selection indicator |
| Icon Size | 24dp | Icon size |
| Animation Duration | 250ms | Transition animation |

---

**See Also:**

- [Navigation Bar](navigation-bar.md) - For bottom navigation
- [Top App Bar](top-app-bar.md) - For top navigation

