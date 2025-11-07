# Top App Bar

Top app bars display information and actions at the top of a screen.

## Overview

VetraTopAppBar provides a modern top app bar with:

- Translucent background with subtle border
- Clean typography with generous spacing
- Smooth scroll-based transitions
- No heavy shadows, just elegant separation
- Support for navigation icons and actions

```kotlin
VetraTopAppBar(
    title = { Text("Screen Title") },
    navigationIcon = {
        VetraIconButton(
            onClick = { },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )
    }
)
```

## Variants

=== "Standard"

    Clean and minimal top app bar.
    
    ```kotlin
    VetraTopAppBar(
        title = { Text("Title") }
    )
    ```

=== "With Navigation"

    Top app bar with navigation icon.
    
    ```kotlin
    VetraTopAppBar(
        title = { Text("Title") },
        navigationIcon = {
            VetraIconButton(
                onClick = { },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
    )
    ```

=== "With Actions"

    Top app bar with action buttons.
    
    ```kotlin
    VetraTopAppBar(
        title = { Text("Title") },
        actions = {
            VetraIconButton(
                onClick = { },
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
            VetraIconButton(
                onClick = { },
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }
    )
    ```

=== "Large"

    Large top app bar that collapses on scroll.
    
    ```kotlin
    VetraLargeTopAppBar(
        title = { Text("Large Title") },
        subtitle = { Text("Subtitle") },
        scrolled = isScrolled
    )
    ```

## Common Patterns

### Standard Screen

```kotlin
VetraTopAppBar(
    title = { Text("Settings") },
    navigationIcon = {
        VetraIconButton(
            onClick = { navigateBack() },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )
    },
    actions = {
        VetraIconButton(
            onClick = { showMenu() },
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More options"
        )
    }
)
```

### Scroll-Aware Border

```kotlin
var scrolled by remember { mutableStateOf(false) }

VetraTopAppBar(
    title = { Text("Title") },
    scrolled = scrolled
)
```

### Large Top App Bar

```kotlin
var scrolled by remember { mutableStateOf(false) }

VetraLargeTopAppBar(
    title = { Text("Profile") },
    subtitle = { Text("user@example.com") },
    scrolled = scrolled,
    navigationIcon = {
        VetraIconButton(
            onClick = { },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )
    }
)
```

## Best Practices

!!! success "Do"
    - ✅ Use top app bars for screen titles and primary actions
    - ✅ Include navigation icons for hierarchical navigation
    - ✅ Keep action icons relevant and limited
    - ✅ Use large top app bars for detail screens
    - ✅ Show border when content is scrolled

!!! failure "Don't"
    - ❌ Overcrowd with too many actions
    - ❌ Use top app bars for navigation (use navigation bars)
    - ❌ Hide important actions in menus unnecessarily
    - ❌ Make titles too long

## Accessibility

- **Touch Targets**: All icons meet minimum 44×44dp
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Proper content descriptions
- **Keyboard Navigation**: Full keyboard support

## API Reference

### VetraTopAppBar

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `title` | `@Composable () -> Unit` | Required | Title content |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `navigationIcon` | `@Composable (() -> Unit)?` | `null` | Optional navigation icon |
| `actions` | `@Composable RowScope.() -> Unit` | `{}` | Row of action buttons |
| `scrolled` | `Boolean` | `false` | Whether content is scrolled (affects border) |

### VetraLargeTopAppBar

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `title` | `@Composable () -> Unit` | Required | Title content |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `subtitle` | `@Composable (() -> Unit)?` | `null` | Optional subtitle |
| `navigationIcon` | `@Composable (() -> Unit)?` | `null` | Optional navigation icon |
| `actions` | `@Composable RowScope.() -> Unit` | `{}` | Row of action buttons |
| `scrolled` | `Boolean` | `false` | Whether content is scrolled |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Height | 64dp | Standard height |
| Large Expanded Height | 152dp | Expanded state |
| Large Collapsed Height | 64dp | Collapsed state |

---

**See Also:**

- [Navigation Bar](navigation-bar.md) - For bottom navigation
- [Icon Button](../input/icon-button.md) - For action buttons

