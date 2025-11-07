# Divider

Dividers provide visual separation between content sections with multiple style variants.

## Overview

VetraDivider provides a simple divider component with:

- Clean separation of content sections
- Horizontal and vertical orientations
- Multiple style variants for different emphasis levels
- Optional text labels for section headers
- Consistent with Vetra design system

```kotlin
VetraDivider()
```

## Variants

=== "Standard"

    Basic divider for content separation.
    
    ```kotlin
    VetraDivider()
    ```

=== "Subtle"

    Lighter divider for subtle separation.
    
    ```kotlin
    VetraSubtleDivider()
    ```

=== "Strong"

    More prominent divider for clear separation.
    
    ```kotlin
    VetraStrongDivider()
    ```

=== "Brand"

    Divider with brand color accent.
    
    ```kotlin
    VetraBrandDivider()
    ```

=== "Inset"

    Divider with horizontal padding, commonly used in lists.
    
    ```kotlin
    VetraInsetDivider(startPadding = 48.dp)
    ```

=== "With Text"

    Divider with centered text label.
    
    ```kotlin
    VetraDividerWithText(text = "OR")
    ```

## Orientations

=== "Horizontal"

    Default horizontal divider.
    
    ```kotlin
    VetraDivider(orientation = DividerOrientation.Horizontal)
    ```

=== "Vertical"

    Vertical divider for side-by-side content.
    
    ```kotlin
    VetraDivider(orientation = DividerOrientation.Vertical)
    ```

## Common Patterns

### List Separators

```kotlin
Column {
    repeat(items.size) { index ->
        ListItem(item = items[index])
        if (index < items.size - 1) {
            VetraInsetDivider(startPadding = 16.dp)
        }
    }
}
```

### Section Dividers

```kotlin
Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
    Section1()
    VetraDivider()
    Section2()
    VetraDivider()
    Section3()
}
```

### Vertical Dividers

```kotlin
Row(
    modifier = Modifier.height(60.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
) {
    Box(modifier = Modifier.weight(1f)) {
        Content1()
    }
    VetraDivider(orientation = DividerOrientation.Vertical)
    Box(modifier = Modifier.weight(1f)) {
        Content2()
    }
}
```

### Labeled Dividers

```kotlin
VetraDividerWithText(text = "OR")

VetraDividerWithText(
    text = "Section",
    spacing = 16.dp
)
```

### Custom Styling

```kotlin
VetraDivider(
    thickness = 2.dp,
    color = VetraTheme.colors.brand
)
```

## Best Practices

!!! success "Do"
    - ✅ Use dividers to separate distinct content sections
    - ✅ Use inset dividers in lists
    - ✅ Use subtle dividers for less emphasis
    - ✅ Use labeled dividers for clarity
    - ✅ Use vertical dividers sparingly

!!! failure "Don't"
    - ❌ Overuse dividers (use spacing instead)
    - ❌ Use dividers for decoration
    - ❌ Make dividers too thick
    - ❌ Use dividers between closely related content

## Accessibility

- **Visual Separation**: Clear but not distracting
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Properly ignored by assistive technologies

## API Reference

### VetraDivider

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `orientation` | `DividerOrientation` | `Horizontal` | Orientation of the divider |
| `thickness` | `Dp` | `1.dp` | Thickness of the divider line |
| `color` | `Color` | `VetraTheme.colors.border` | Color of the divider |

### VetraSubtleDivider

Same API as `VetraDivider` but uses `borderSubtle` color.

### VetraStrongDivider

Same API as `VetraDivider` but uses 2dp thickness.

### VetraBrandDivider

Same API as `VetraDivider` but uses brand color.

### VetraInsetDivider

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `startPadding` | `Dp` | `16.dp` | Padding at the start |
| `endPadding` | `Dp` | `0.dp` | Padding at the end |
| `thickness` | `Dp` | `1.dp` | Thickness of the divider |
| `color` | `Color` | `VetraTheme.colors.border` | Color of the divider |

### VetraDividerWithText

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `text` | `String` | Required | Label text to display |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `thickness` | `Dp` | `1.dp` | Thickness of the divider lines |
| `color` | `Color` | `VetraTheme.colors.border` | Color of the divider lines |
| `spacing` | `Dp` | `12.dp` | Space between text and divider lines |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Standard Thickness | 1dp | Default thickness |
| Strong Thickness | 2dp | For emphasis |
| Brand Thickness | 2dp | Brand accent |

---

**See Also:**

- [Cards](cards.md) - For content grouping
- [Spacing](../../design-system/overview.md) - For layout spacing

