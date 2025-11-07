# Theming

Customize colors, typography, shapes, and shadows in Vetra UI.

## Custom Colors

Override default colors:

```kotlin
VetraTheme(
    colors = VetraLightColorScheme.copy(
        brand = Color(0xFF1E40AF),  // Custom blue
        accent = Color(0xFF9333EA)   // Custom purple
    )
) {
    // Your app content
}
```

## Custom Typography

Customize typography:

```kotlin
VetraTheme(
    typography = VetraTypography(
        displayLg = TextStyle(
            fontFamily = FontFamily.Default,
            fontSize = 57.sp,
            fontWeight = FontWeight.Bold
        ),
        // ... other styles
    )
) {
    // Your app content
}
```

## Custom Shapes

Customize corner radius:

```kotlin
VetraTheme(
    shapes = VetraShapes(
        xs = RoundedCornerShape(4.dp),
        sm = RoundedCornerShape(8.dp),
        md = RoundedCornerShape(12.dp),
        lg = RoundedCornerShape(16.dp),
        xl = RoundedCornerShape(24.dp)
    )
) {
    // Your app content
}
```

## Custom Shadows

Customize shadow elevations:

```kotlin
VetraTheme(
    shadows = VetraShadows(
        xs = Shadow(...),
        sm = Shadow(...),
        // ... other shadows
    )
) {
    // Your app content
}
```

## Complete Custom Theme

```kotlin
VetraTheme(
    darkMode = false,
    colors = customColorScheme,
    typography = customTypography,
    shapes = customShapes,
    shadows = customShadows
) {
    // Your app content
}
```

## Dynamic Theme Switching

Switch themes dynamically:

```kotlin
var isDarkMode by remember { mutableStateOf(false) }

VetraTheme(
    darkMode = isDarkMode,
    colors = if (isDarkMode) VetraDarkColorScheme else VetraLightColorScheme
) {
    // Your app content
}
```

## Next Steps

- [Common Patterns](patterns.md) - See theming in action
- [Design System](../../design-system/overview.md) - Learn about design tokens

