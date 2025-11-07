# Skeleton

Skeleton loaders provide content placeholders with shimmer animation while content loads.

## Overview

VetraSkeleton provides skeleton loading components with:

- Smooth shimmer animation
- Multiple shape variants
- Customizable colors
- Composable skeleton layouts
- Perfect for content placeholders

```kotlin
VetraSkeleton(
    modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
)
```

## Variants

=== "Basic"

    Basic skeleton box with shimmer.
    
    ```kotlin
    VetraSkeleton(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
    ```

=== "Text"

    Skeleton placeholder for text lines.
    
    ```kotlin
    VetraSkeletonText()
    VetraSkeletonText(width = 200.dp)
    ```

=== "Circle"

    Circular skeleton placeholder, perfect for avatars.
    
    ```kotlin
    VetraSkeletonCircle(size = 40.dp)
    ```

=== "Card"

    Pre-composed skeleton for card-like content.
    
    ```kotlin
    VetraSkeletonCard(
        showImage = true,
        linesCount = 3
    )
    ```

=== "List Item"

    Pre-composed skeleton for list item content.
    
    ```kotlin
    VetraSkeletonListItem(
        showAvatar = true,
        linesCount = 2
    )
    ```

=== "Button"

    Skeleton placeholder for button.
    
    ```kotlin
    VetraSkeletonButton()
    ```

## Common Patterns

### Content Loading

```kotlin
if (isLoading) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        VetraSkeletonText()
        VetraSkeletonText(width = 200.dp)
        VetraSkeletonText(width = 150.dp)
    }
} else {
    Content()
}
```

### Card Skeleton

```kotlin
VetraCard {
    VetraSkeletonCard(
        showImage = true,
        imageHeight = 180.dp,
        linesCount = 4
    )
}
```

### List Skeleton

```kotlin
Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
    repeat(3) {
        VetraCard {
            VetraSkeletonListItem(
                showAvatar = true,
                linesCount = 2
            )
        }
    }
}
```

### Avatar with Text

```kotlin
Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
    VetraSkeletonCircle(size = 48.dp)
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        VetraSkeletonText(width = 200.dp, height = 18.dp)
        VetraSkeletonText(width = 150.dp, height = 14.dp)
    }
}
```

## Best Practices

!!! success "Do"
    - ✅ Use skeletons for content placeholders
    - ✅ Match skeleton shape to actual content
    - ✅ Show skeletons immediately while loading
    - ✅ Use appropriate skeleton variants
    - ✅ Keep animations smooth

!!! failure "Don't"
    - ❌ Use skeletons for instant operations
    - ❌ Make skeletons too detailed
    - ❌ Show skeletons for too long
    - ❌ Use skeletons for error states

## Accessibility

- **Screen Readers**: Proper loading announcements
- **Animation**: Respects reduced motion preferences
- **Visual**: Clear placeholder indication

## API Reference

### VetraSkeleton

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `shape` | `Shape` | `VetraTheme.shapes.md` | Shape of the skeleton |
| `baseColor` | `Color` | `VetraTheme.colors.borderSubtle` | Base color |
| `shimmerColor` | `Color` | `VetraTheme.colors.canvasElevated` | Shimmer color |

### VetraSkeletonText

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `width` | `Dp?` | `null` | Width (null for fillMaxWidth) |
| `height` | `Dp` | `16.dp` | Height of the line |

### VetraSkeletonCircle

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `size` | `Dp` | `40.dp` | Size of the circle |

### VetraSkeletonCard

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `showImage` | `Boolean` | `true` | Whether to show image placeholder |
| `imageHeight` | `Dp` | `200.dp` | Image placeholder height |
| `linesCount` | `Int` | `3` | Number of text lines |

### VetraSkeletonListItem

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `showAvatar` | `Boolean` | `true` | Whether to show avatar placeholder |
| `avatarSize` | `Dp` | `48.dp` | Avatar size |
| `linesCount` | `Int` | `2` | Number of text lines |

### VetraSkeletonButton

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `width` | `Dp` | `120.dp` | Button width |
| `height` | `Dp` | `44.dp` | Button height |

---

**See Also:**

- [Loading](loading.md) - For loading indicators
- [Cards](../display/cards.md) - For card skeletons

