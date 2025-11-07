# Buttons

Buttons trigger actions when clicked. Vetra UI provides five button variants for different levels of emphasis.

## Variants

=== "Primary"

    Main action button with solid brand color.
    
    ```kotlin
    VetraButton(onClick = { /* action */ }) {
        Text("Primary Action")
    }
    ```
    
    **When to use:** The most important action on screen (e.g., "Submit", "Continue", "Save").
    
    !!! tip "Visual Hierarchy"
        Limit to **one primary button** per section for clear hierarchy.

=== "Secondary"

    Secondary actions with accent color.
    
    ```kotlin
    VetraSecondaryButton(onClick = { /* action */ }) {
        Text("Secondary Action")
    }
    ```
    
    **When to use:** Important but not primary actions (e.g., "Add to Cart", "Share").

=== "Outlined"

    Tertiary actions with border only.
    
    ```kotlin
    VetraOutlinedButton(onClick = { /* action */ }) {
        Text("Outlined Action")
    }
    ```
    
    **When to use:** Less important actions (e.g., "Cancel", "Back", "Skip").

=== "Ghost"

    Minimal button with no background.
    
    ```kotlin
    VetraGhostButton(onClick = { /* action */ }) {
        Text("Ghost Action")
    }
    ```
    
    **When to use:** Least important actions or dense UIs (e.g., "Maybe Later", navigation items).

=== "Danger"

    Destructive actions with danger color.
    
    ```kotlin
    VetraDangerButton(onClick = { /* action */ }) {
        Text("Delete")
    }
    ```
    
    **When to use:** Actions that delete or destroy data.
    
    !!! warning "Use with Caution"
        Always confirm destructive actions with a dialog.

## Common Patterns

### Button States

```kotlin
VetraButton(
    onClick = { submit() },
    enabled = formValid  // (1)!
) {
    Text("Submit")
}
```

1. Disable button when conditions aren't met

### With Icons

=== "Leading Icon"

    ```kotlin
    VetraButton(onClick = { /* */ }) {
        Icon(Icons.Default.Add, null, Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text("Add Item")
    }
    ```

=== "Trailing Icon"

    ```kotlin
    VetraButton(onClick = { /* */ }) {
        Text("Continue")
        Spacer(Modifier.width(8.dp))
        Icon(Icons.Default.ArrowForward, null, Modifier.size(18.dp))
    }
    ```

### Loading State

```kotlin
VetraButton(
    onClick = { startLoading() },
    enabled = !isLoading
) {
    if (isLoading) {
        VetraLoadingIndicator(size = 18.dp, color = VetraTheme.colors.onBrand)
        Spacer(Modifier.width(8.dp))
    }
    Text(if (isLoading) "Loading..." else "Submit")
}
```

### Button Groups

=== "Horizontal"

    ```kotlin
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        VetraOutlinedButton(onClick = { }, Modifier.weight(1f)) {
            Text("Cancel")
        }
        VetraButton(onClick = { }, Modifier.weight(1f)) {
            Text("Confirm")
        }
    }
    ```

=== "Vertical"

    ```kotlin
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        VetraButton(onClick = { }, Modifier.fillMaxWidth()) {
            Text("Primary")
        }
        VetraSecondaryButton(onClick = { }, Modifier.fillMaxWidth()) {
            Text("Secondary")
        }
    }
    ```

## Customization

### Full Width

```kotlin
VetraButton(
    onClick = { /* */ },
    modifier = Modifier.fillMaxWidth()
) {
    Text("Full Width Button")
}
```

### Custom Size

```kotlin
VetraButton(
    onClick = { /* */ },
    modifier = Modifier.size(width = 200.dp, height = 56.dp)
) {
    Text("Large Button")
}
```

## Best Practices

!!! success "Do"
    - ✅ Use one primary button per section
    - ✅ Choose variant based on action importance
    - ✅ Provide descriptive button text
    - ✅ Disable buttons when actions aren't available
    - ✅ Ensure minimum 44×44dp touch targets

!!! failure "Don't"
    - ❌ Use multiple primary buttons in same context
    - ❌ Use vague labels like "Click Here" or "OK"
    - ❌ Make buttons too small (< 88dp width)
    - ❌ Use danger buttons for non-destructive actions
    - ❌ Forget to handle loading states

## Accessibility

- **Touch Targets**: Minimum 44×44dp built-in
- **Color Contrast**: All variants meet WCAG AA standards
- **Focus Indicators**: Automatic press indication
- **Screen Readers**: Semantic `Role.Button` support

!!! tip "Improve Accessibility"
    ```kotlin
    VetraButton(
        onClick = { submit() },
        enabled = formValid,
        modifier = Modifier.semantics {
            if (!formValid) {
                stateDescription = "Please fill all required fields"
            }
        }
    ) {
        Text("Submit")
    }
    ```

## API Reference

### All Button Variants

All button variants share the same API signature:

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `onClick` | `() -> Unit` | Required | Callback when button is clicked |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether button is interactive |
| `content` | `@Composable RowScope.() -> Unit` | Required | Button content (text, icons, etc.) |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Min Height | 44dp | Meets accessibility standards |
| Min Width | 88dp | Comfortable click target |
| Horizontal Padding | 20dp | Internal spacing |
| Vertical Padding | 12dp | Internal spacing |
| Corner Radius | 8dp | `shapes.sm` |

### Visual Properties by Variant

| Variant | Background | Text Color | Border | Shadow |
|---------|------------|------------|--------|--------|
| **Primary** | `brand` | `onBrand` | None | `shadows.sm` |
| **Secondary** | `accent` | `onAccent` | None | `shadows.sm` |
| **Outlined** | Transparent | `brand` | `border` | None |
| **Ghost** | Transparent | `brand` | None | None |
| **Danger** | `danger` | `onDanger` | None | `shadows.sm` |

---

**See Also:**

- [Design: Colors](../../design-system/colors.md) - Button color system
- [Design: Elevation](../../design-system/elevation.md) - Button shadows
