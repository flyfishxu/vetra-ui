# Loading

Loading indicators show progress and loading states with smooth animations.

## Overview

VetraLoading provides multiple loading indicator types:

- Smooth, continuous animations
- Multiple size variants
- Customizable colors
- Minimal CPU usage
- Elegant motion design

```kotlin
VetraLoadingSpinner()
```

## Variants

=== "Spinner"

    Circular spinner with smooth rotation.
    
    ```kotlin
    VetraLoadingSpinner()
    ```

=== "Dots"

    Three animated dots that bounce in sequence.
    
    ```kotlin
    VetraLoadingDots()
    ```

=== "Pulse"

    Concentric circles that pulse outward.
    
    ```kotlin
    VetraLoadingPulse()
    ```

=== "Linear"

    Linear progress bar for determinate or indeterminate progress.
    
    ```kotlin
    VetraLinearProgress(progress = 0.5f)
    ```

=== "Full Screen"

    Centered loading indicator with optional message.
    
    ```kotlin
    VetraFullScreenLoading(message = "Loading...")
    ```

## Common Patterns

### Button Loading State

```kotlin
VetraButton(
    onClick = { },
    enabled = !isLoading
) {
    if (isLoading) {
        VetraLoadingSpinner(size = LoadingSize.SMALL, color = VetraTheme.colors.onBrand)
        Spacer(Modifier.width(8.dp))
    }
    Text(if (isLoading) "Loading..." else "Submit")
}
```

### Inline Loading

```kotlin
if (isLoading) {
    VetraLoadingSpinner(size = LoadingSize.MEDIUM)
} else {
    Content()
}
```

### Progress Bar

```kotlin
// Indeterminate
VetraLinearProgress()

// Determinate
VetraLinearProgress(progress = uploadProgress)
```

### Full Screen Loading

```kotlin
if (isLoading) {
    VetraFullScreenLoading(
        message = "Loading content...",
        loadingType = LoadingType.SPINNER
    )
} else {
    Content()
}
```

### Custom Colors

```kotlin
VetraLoadingSpinner(
    color = VetraTheme.colors.accent,
    size = LoadingSize.LARGE
)
```

## Best Practices

!!! success "Do"
    - ✅ Show loading states for async operations
    - ✅ Use appropriate indicator type
    - ✅ Provide context when helpful
    - ✅ Use determinate progress when possible
    - ✅ Keep animations smooth

!!! failure "Don't"
    - ❌ Show loading for instant operations
    - ❌ Use multiple loading indicators simultaneously
    - ❌ Block user interaction unnecessarily
    - ❌ Make loading indicators too large

## Accessibility

- **Screen Readers**: Proper announcements
- **Animation**: Respects reduced motion preferences
- **Color Contrast**: Meets WCAG AA standards

## API Reference

### VetraLoadingSpinner

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `size` | `LoadingSize` | `MEDIUM` | Size variant |
| `color` | `Color` | `VetraTheme.colors.brand` | Spinner color |
| `strokeWidth` | `Dp?` | `null` | Optional stroke width |

### VetraLoadingDots

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `color` | `Color` | `VetraTheme.colors.brand` | Dots color |
| `dotSize` | `Dp` | `8.dp` | Size of each dot |
| `spacing` | `Dp` | `8.dp` | Spacing between dots |

### VetraLoadingPulse

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `size` | `LoadingSize` | `MEDIUM` | Size variant |
| `color` | `Color` | `VetraTheme.colors.brand` | Pulse color |

### VetraLinearProgress

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `progress` | `Float?` | `null` | Progress (0f-1f), null for indeterminate |
| `color` | `Color` | `VetraTheme.colors.brand` | Progress color |
| `trackColor` | `Color` | `VetraTheme.colors.borderSubtle` | Track color |
| `height` | `Dp` | `4.dp` | Progress bar height |

### VetraFullScreenLoading

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `message` | `String?` | `null` | Optional loading message |
| `loadingType` | `LoadingType` | `SPINNER` | Type of loading indicator |

### LoadingSize

- `SMALL` - 24dp
- `MEDIUM` - 40dp (default)
- `LARGE` - 56dp
- `XLARGE` - 72dp

---

**See Also:**

- [Skeleton](skeleton.md) - For content placeholders
- [Pull to Refresh](pull-to-refresh.md) - For refresh loading

