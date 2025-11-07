# Cards

Cards group related content in a container with optional elevation and interaction.

## Variants

=== "Standard"

    Default card with soft shadow elevation.
    
    ```kotlin
    VetraCard {
        Text("Card content")
    }
    ```
    
    **When to use:** Default choice for most content grouping needs.

=== "Flat"

    No elevation or shadow, minimal visual weight.
    
    ```kotlin
    VetraFlatCard {
        Text("Subtle grouping")
    }
    ```
    
    **When to use:** Dense layouts, nested cards, or subtle grouping.

=== "Elevated"

    Higher elevation for emphasis.
    
    ```kotlin
    VetraElevatedCard {
        Text("Important content")
    }
    ```
    
    **When to use:** Featured content, important information, floating elements.

=== "Outlined"

    Border only, no shadow.
    
    ```kotlin
    VetraOutlinedCard {
        Text("Bordered content")
    }
    ```
    
    **When to use:** Light themes, minimalist designs, clear boundaries needed.

=== "Brand"

    Subtle brand color background.
    
    ```kotlin
    VetraBrandCard {
        Text("Special content")
    }
    ```
    
    **When to use:** Highlight special or promotional content.

## Interactive Cards

Cards can be clickable:

```kotlin
VetraCard(onClick = { navigateToDetails() }) {
    Column {
        Text("Product Name", style = VetraTheme.typography.headingMd)
        Text("$99.99", style = VetraTheme.typography.bodyLg)
    }
}
```

!!! tip "Interaction Feedback"
    Clickable cards automatically show press indication animation.

## Common Patterns

### Product Card

```kotlin
VetraCard(
    modifier = Modifier.width(200.dp),
    onClick = { /* view product */ }
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Image(/* product image */)
        Text("Product", style = VetraTheme.typography.headingSm)
        Text("$99.99", style = VetraTheme.typography.bodyLg)
        VetraButton(onClick = { /* buy */ }, Modifier.fillMaxWidth()) {
            Text("Buy Now")
        }
    }
}
```

### Info Card with Icon

```kotlin
VetraBrandCard {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Info,
            contentDescription = null,
            tint = VetraTheme.colors.brand
        )
        Column {
            Text("Pro Tip", style = VetraTheme.typography.labelLg)
            Text("Use shortcuts to work faster", style = VetraTheme.typography.bodyMd)
        }
    }
}
```

### Stats Card

```kotlin
VetraFlatCard(modifier = Modifier.width(120.dp)) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text("1,234", style = VetraTheme.typography.headingLg)
        Text("Downloads", style = VetraTheme.typography.bodySm)
    }
}
```

## Layout

### Card Grid

```kotlin
LazyVerticalGrid(
    columns = GridCells.Adaptive(minSize = 150.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    items(products) { product ->
        VetraCard(onClick = { view(product) }) {
            ProductContent(product)
        }
    }
}
```

### Card List

```kotlin
LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    items(items) { item ->
        VetraCard(modifier = Modifier.fillMaxWidth()) {
            ListItemContent(item)
        }
    }
}
```

## Best Practices

!!! success "Do"
    - ✅ Use standard card as default
    - ✅ Choose variant based on visual hierarchy needs
    - ✅ Maintain consistent spacing between cards
    - ✅ Provide adequate padding inside cards (default is 16dp)
    - ✅ Use elevated cards sparingly for emphasis

!!! failure "Don't"
    - ❌ Nest high-elevation cards inside cards
    - ❌ Mix too many card variants on one screen
    - ❌ Overcrowd cards with too much content
    - ❌ Use clickable cards without visual feedback
    - ❌ Apply excessive custom styling

## Accessibility

- **Touch Targets**: Clickable cards ensure minimum touch area
- **Focus Indicators**: Automatic press indication for interactive cards
- **Semantic Roles**: Clickable cards use `Role.Button` for screen readers

## API Reference

### All Card Variants

All card variants share the same API:

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `onClick` | `(() -> Unit)?` | `null` | Optional click handler (makes card interactive) |
| `content` | `@Composable ColumnScope.() -> Unit` | Required | Card content in vertical layout |

### Visual Properties

| Variant | Background | Elevation | Border | Use Case |
|---------|------------|-----------|--------|----------|
| **Standard** | `canvasElevated` | `shadows.sm` | Subtle | Default content grouping |
| **Flat** | `canvasSubtle` | None | None | Subtle grouping |
| **Elevated** | `canvasElevated` | `shadows.xl` | None | Emphasis, featured content |
| **Outlined** | `canvasElevated` | None | `border` | Clear boundaries |
| **Brand** | `brandSubtle` | None | None | Special/promotional content |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Content Padding | 16dp (or 15dp/14dp with border) | Internal spacing |
| Corner Radius | 12dp | `shapes.md` |
| Min Height | None | Height determined by content |
| Min Width | None | Width determined by modifier |

---

**See Also:**

- [Buttons](../input/buttons.md) - Action buttons inside cards
- [Design: Elevation](../../design-system/elevation.md) - Understanding card shadows
- [Design: Shapes](../../design-system/shapes.md) - Corner radius system

