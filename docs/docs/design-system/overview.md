# Design System Overview

Vetra UI is built on a comprehensive, independent design system that emphasizes **elegant simplicity** and **intuitive design**.

## Philosophy

Vetra UI creates a universal aesthetic that works across all platforms and contexts. Unlike Material Design, which follows Google's specific design language, Vetra UI is designed to be:

- **Independent**: Not based on any existing design system
- **Semantic**: Names that make sense (`brand` instead of `primary`)
- **Universal**: Works beautifully across all platforms
- **Flexible**: Easy to customize while maintaining consistency
- **Accessible**: Built with accessibility in mind from the start

## Core Principles

### 1. Elegance Over Flash

Every visual element and animation serves a clear purpose. No effects for effect's sake.

```kotlin
// ✅ Purposeful animation
VetraButton(onClick = { /* ... */ }) {
    // Smooth press indication with purpose
    Text("Submit")
}

// ❌ Unnecessary effects
AnimatedButton(
    rotation = 360f,
    scale = 2f,
    // Distracting, no purpose
)
```

### 2. Unity Over Variety

Consistent design language reduces cognitive load and creates cohesive experiences.

- Use the same spacing scale throughout your app
- Stick to the defined elevation levels
- Follow the typography hierarchy
- Use semantic colors consistently

### 3. Natural Over Mechanical

Motion follows physics, interactions follow intuition.

- Spring-based animations feel natural
- Easing curves are carefully tuned
- Touch targets are appropriately sized
- Feedback is immediate and clear

### 4. Clarity Over Abstraction

Intuitive naming and predictable APIs make development a joy.

```kotlin
// ✅ Vetra: Clear and semantic
VetraTheme.colors.brand
VetraTheme.colors.textSecondary
VetraTheme.colors.canvasElevated

// ❌ Material: Technical and abstract
MaterialTheme.colorScheme.primary
MaterialTheme.colorScheme.onSecondaryContainer
MaterialTheme.colorScheme.surfaceVariant
```

## Design Tokens

Vetra UI's design system is built on five core token systems:

### [Colors](colors.md)
Semantic color roles that make sense, with automatic dark mode support.

- Brand colors for primary actions
- Accent colors for secondary emphasis
- Canvas colors for backgrounds
- Semantic colors for states (success, warning, danger, info)

### [Typography](typography.md)
Harmonious type scale with clear hierarchy.

- Display styles for major headings
- Heading styles for sections
- Body styles for content
- Label styles for interactive elements

### [Shapes](shapes.md)
Consistent corner radius scale for all elements.

- 8 predefined radius sizes from none to full
- Consistent visual language
- Easy to apply and customize

### [Elevation](elevation.md)
Seven elevation levels with adaptive, mode-aware shadows.

- Natural depth perception
- Automatic adjustment for light/dark mode
- Cross-platform consistency

## Design System Structure

```
VetraTheme
├── colors: VetraColorScheme
│   ├── Brand colors (brand, onBrand, brandSubtle, ...)
│   ├── Accent colors (accent, onAccent, ...)
│   ├── Canvas colors (canvas, canvasElevated, ...)
│   ├── Text colors (textPrimary, textSecondary, ...)
│   ├── Border colors (border, borderSubtle, ...)
│   └── Semantic colors (success, warning, danger, info)
│
├── typography: VetraTypography
│   ├── Display (displayXl, displayLg, displayMd, displaySm)
│   ├── Heading (headingXl, headingLg, headingMd, headingSm)
│   ├── Body (bodyLg, bodyMd, bodySm)
│   └── Label (labelLg, labelMd, labelSm)
│
├── shapes: VetraShapes
│   ├── none (0dp), xs (4dp), sm (8dp), md (12dp)
│   ├── lg (16dp), xl (24dp), xxl (32dp)
│   └── full (9999dp)
│
└── shadows: VetraShadows
    ├── none (0dp), xs (1dp), sm (2dp), md (4dp)
    ├── lg (8dp), xl (16dp), xxl (24dp)
    └── Adaptive shadow config for light/dark modes
```

## Usage

Access design tokens through the `VetraTheme` object:

```kotlin
@Composable
fun MyComponent() {
    Box(
        modifier = Modifier
            .background(
                color = VetraTheme.colors.brand,
                shape = VetraTheme.shapes.md
            )
            .vetraShadow(
                elevation = VetraTheme.shadows.sm,
                shape = VetraTheme.shapes.md
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Hello Vetra",
            style = VetraTheme.typography.headingMd,
            color = VetraTheme.colors.onBrand
        )
    }
}
```

## Comparison with Material Design

| Aspect | Material Design | Vetra UI |
|--------|----------------|----------|
| **Philosophy** | Google's design language | Universal, independent design |
| **Naming** | Technical (primary, onSecondaryContainer) | Semantic (brand, textSecondary) |
| **Color System** | 28+ color roles | Intuitive semantic names |
| **Shadows** | Single configuration | Adaptive for light/dark modes |
| **Typography** | Display, Headline, Title, Body, Label | Display, Heading, Body, Label |
| **Learning Curve** | Complex color system | Intuitive and straightforward |
| **Flexibility** | Highly opinionated | Flexible with clear defaults |
| **Target** | Android-first | True cross-platform |

## Design Guidelines

### Visual Hierarchy

Create clear hierarchy through:

1. **Size**: Use the typography scale appropriately
2. **Weight**: Bold for emphasis, regular for body
3. **Color**: Primary for important, secondary for supporting
4. **Elevation**: Higher shadows for more important elements

### Spacing & Layout

- Use consistent spacing from the spacing scale
- Follow the 8pt grid system when possible
- Maintain generous whitespace for breathing room
- Group related elements together

### Color Usage

- **Brand**: Main actions, primary navigation, brand elements
- **Accent**: Secondary actions, highlights, special features
- **Canvas**: Backgrounds, surfaces
- **Text**: Content, labels, descriptions
- **Semantic**: Success, error, warning, info states

### Motion & Animation

- **Duration**: 200-400ms for most transitions
- **Easing**: Use `EaseInOutCubic` for smooth motion
- **Spring**: Use spring physics for playful interactions
- **Purpose**: Every animation should have a clear reason

### Accessibility

- **Contrast**: Minimum 4.5:1 for normal text, 3:1 for large text
- **Touch Targets**: Minimum 44dp × 44dp
- **Focus Indicators**: Clear visual feedback for keyboard navigation
- **Disabled States**: Clear visual distinction
- **Screen Readers**: Semantic HTML and proper ARIA labels

## Best Practices

### ✅ Do

- Use semantic color names consistently
- Follow the type scale hierarchy
- Apply consistent spacing
- Use elevation purposefully
- Animate state changes smoothly
- Test in both light and dark modes
- Consider accessibility from the start

### ❌ Don't

- Mix Material and Vetra components
- Use arbitrary colors outside the system
- Apply heavy or inconsistent shadows
- Create custom color names without good reason
- Skip animation specs
- Ignore disabled states
- Forget keyboard navigation

## Customization

While Vetra UI provides beautiful defaults, you can customize any aspect:

```kotlin
VetraTheme(
    colors = VetraLightColorScheme.copy(
        brand = Color(0xFF1E40AF),
        accent = Color(0xFF9333EA)
    ),
    typography = DefaultVetraTypography.copy(
        headingMd = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    ),
    shapes = DefaultVetraShapes.copy(
        md = RoundedCornerShape(16.dp)
    )
) {
    // Your app
}
```

## Next Steps

Explore each design token system in detail:

- **[Colors](colors.md)** - Semantic color system
- **[Typography](typography.md)** - Type scale and styles
- **[Shapes](shapes.md)** - Corner radius system
- **[Elevation](elevation.md)** - Shadow system

---

**Vetra UI** - Elegant by design, intuitive by nature.

