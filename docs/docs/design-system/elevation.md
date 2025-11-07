# Elevation & Shadows

Vetra UI features a refined adaptive shadow system designed for clear visual hierarchy, natural appearance, and cross-platform consistency.

## Philosophy

- **Adaptive Configuration**: Automatic adjustment between light and dark modes
- **Clear Visibility**: Properly tuned alpha values for clear depth perception
- **Natural Hierarchy**: Visible shadows that create clear separation
- **Cross-Platform Optimized**: Consistent rendering across all platforms

## Elevation Levels

Vetra UI provides seven standard elevation levels:

```kotlin
VetraTheme.shadows.none   // 0dp - No elevation
VetraTheme.shadows.xs     // 1dp - Barely perceptible
VetraTheme.shadows.sm     // 2dp - Light elevation
VetraTheme.shadows.md     // 4dp - Moderate elevation
VetraTheme.shadows.lg     // 8dp - Strong elevation
VetraTheme.shadows.xl     // 16dp - Maximum elevation
VetraTheme.shadows.xxl    // 24dp - Dramatic elevation
```

## Elevation Scale

### None - 0dp

No elevation for flat elements.

```kotlin
Box(
    modifier = Modifier
        .vetraShadow(elevation = VetraTheme.shadows.none)
        .background(VetraTheme.colors.canvasElevated)
)
```

**Usage:**
- Flat surfaces
- Embedded content
- Elements flush with background

### XS - 1dp

Barely perceptible elevation for subtle hover states.

```kotlin
VetraCard(
    modifier = Modifier
        .vetraShadow(elevation = VetraTheme.shadows.xs)
) {
    // Hovering element
}
```

**Usage:**
- Hover states
- Subtle separation
- Slightly elevated content

### SM - 2dp

Light elevation for cards and buttons.

```kotlin
VetraButton(onClick = { }) {
    Text("Button") // Uses shadows.sm by default
}

VetraCard {
    // Standard card elevation
}
```

**Usage:**
- Cards (default)
- Buttons (default)
- Contained elements
- List items with separation

### MD - 4dp

Moderate elevation for raised elements.

```kotlin
VetraElevatedCard(
    modifier = Modifier
        .vetraShadow(elevation = VetraTheme.shadows.md)
) {
    // Raised card content
}
```

**Usage:**
- Raised buttons
- Slider thumbs
- Selected items
- Floating toolbars

### LG - 8dp

Strong elevation for floating elements.

```kotlin
VetraMenu(
    expanded = showMenu,
    onDismissRequest = { showMenu = false },
    modifier = Modifier
        .vetraShadow(elevation = VetraTheme.shadows.lg)
) {
    // Menu items
}
```

**Usage:**
- Dropdowns
- Tooltips
- Floating menus
- Pop-ups

### XL - 16dp

Maximum elevation for modal surfaces.

```kotlin
VetraDialog(
    onDismissRequest = { },
    modifier = Modifier
        .vetraShadow(elevation = VetraTheme.shadows.xl)
) {
    // Dialog content
}
```

**Usage:**
- Dialogs (default)
- Navigation drawers
- Modal sheets
- Prominent overlays

### XXL - 24dp

Dramatic elevation for critical alerts.

```kotlin
Box(
    modifier = Modifier
        .fillMaxWidth()
        .vetraShadow(
            elevation = VetraTheme.shadows.xxl,
            shape = VetraTheme.shapes.xl
        )
        .background(VetraTheme.colors.danger)
) {
    // Critical alert content
}
```

**Usage:**
- Critical modals
- Full-screen overlays
- Emergency alerts
- Splash screens

## Usage

### Basic Usage

Apply adaptive shadows with the `vetraShadow` modifier:

```kotlin
Box(
    modifier = Modifier
        .vetraShadow(
            elevation = VetraTheme.shadows.md,
            shape = VetraTheme.shapes.md
        )
        .background(VetraTheme.colors.canvasElevated, VetraTheme.shapes.md)
) {
    // Your content
}
```

The shadow automatically adapts based on theme mode:
- **Light mode**: 15% ambient, 25% spot shadows (1.15× spread)
- **Dark mode**: 25% ambient, 35% spot shadows (1.2× spread)

### Advanced Configuration

Customize shadow appearance:

```kotlin
Modifier.vetraShadow(
    elevation = VetraTheme.shadows.lg,
    shape = VetraTheme.shapes.md,
    clip = false  // Don't clip content to shape
)
```

**Parameters:**
- `elevation`: Shadow height (Dp value)
- `shape`: Shadow shape (should match element shape)
- `clip`: Clip content to shape (default: false)

### Custom Shadow Config

For special cases, provide custom shadow configuration:

```kotlin
VetraTheme(
    darkMode = false,
    shadowConfig = ShadowConfig(
        ambientAlpha = 0.15f,
        spotAlpha = 0.20f,
        elevationMultiplier = 1.2f
    )
) {
    // Your app content
}
```

## Examples

### Card with Shadow

```kotlin
@Composable
fun ElevatedContent() {
    VetraCard(
        modifier = Modifier
            .fillMaxWidth()
            .vetraShadow(
                elevation = VetraTheme.shadows.md,
                shape = VetraTheme.shapes.md
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Elevated Card",
                style = VetraTheme.typography.headingMd
            )
            Text(
                text = "With beautiful shadow",
                style = VetraTheme.typography.bodyMd,
                color = VetraTheme.colors.textSecondary
            )
        }
    }
}
```

### Floating Action Button

```kotlin
@Composable
fun FloatingActionButton() {
    VetraFilledIconButton(
        onClick = { },
        modifier = Modifier
            .size(56.dp)
            .vetraShadow(
                elevation = VetraTheme.shadows.lg,
                shape = VetraTheme.shapes.full
            )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add"
        )
    }
}
```

### Dialog with Shadow

```kotlin
@Composable
fun ConfirmDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(VetraTheme.colors.overlay)
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .vetraShadow(
                    elevation = VetraTheme.shadows.xl,
                    shape = VetraTheme.shapes.xl
                )
                .background(VetraTheme.colors.canvasElevated, VetraTheme.shapes.xl)
                .padding(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Confirm Action",
                    style = VetraTheme.typography.headingLg
                )
                Text(
                    text = "Are you sure you want to continue?",
                    style = VetraTheme.typography.bodyMd
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    VetraOutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }
                    VetraButton(
                        onClick = onConfirm,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}
```

## Design Principles

### 1. Consistency
Use predefined levels to ensure uniform visual hierarchy across your app.

### 2. Progressive Scale
The elevation gradually increases from xs (1dp) to xxl (24dp), creating natural depth perception.

### 3. Clear Visibility
Properly tuned alpha values (12-18% light, 24-32% dark) ensure shadows are clearly visible in both modes.

### 4. Adaptive Design
Different configurations for light and dark modes ensure optimal visibility.

### 5. Natural Feel
Mimics real-world lighting with heavier shadows below, creating a sense of light from above.

### 6. Cross-Platform
Consistent appearance across Android, iOS, Desktop, and Web platforms.

## Technical Implementation

The adaptive shadow system features:

1. **Mode-Aware Configuration**:
   - Light Mode: Ambient 15%, Spot 25%, Multiplier 1.15×
   - Dark Mode: Ambient 25%, Spot 35%, Multiplier 1.2×

2. **Diffusion Coefficient**: 
   - Actual spread = elevation × multiplier
   - Provides natural, controlled shadow spread

3. **Dual Shadow System**:
   - Ambient shadow: Soft base for overall depth
   - Spot shadow: Directional for clear separation

4. **Natural Lighting Model**:
   - Mimics light from above
   - Heavier shadows at bottom for realism

5. **Performance Optimized**:
   - Conditional application (0dp = no shadow)
   - Single composition pass
   - Efficient theme value access

## Component Default Elevations

| Component | Default Elevation | Reasoning |
|-----------|------------------|-----------|
| Button | `sm` (2dp) | Subtle, clickable |
| Card | `sm` (2dp) | Clear separation |
| Elevated Card | `md` (4dp) | More prominent |
| Menu | `lg` (8dp) | Floats above content |
| Dialog | `xl` (16dp) | Modal emphasis |
| Tooltip | `lg` (8dp) | Floats, readable |
| Bottom Sheet | `xl` (16dp) | Modal surface |
| FAB | `lg` (8dp) | Floating, accessible |

## Guidelines

### Hierarchy Rules

Create clear hierarchy through elevation:

```kotlin
Column {
    // Background - no elevation
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(VetraTheme.colors.canvas)
    )
    
    // Card - light elevation
    VetraCard(
        modifier = Modifier
            .vetraShadow(elevation = VetraTheme.shadows.sm)
    ) {
        Text("Content")
    }
    
    // Floating menu - strong elevation
    VetraMenu(
        modifier = Modifier
            .vetraShadow(elevation = VetraTheme.shadows.lg)
    ) {
        MenuItem("Option")
    }
    
    // Dialog - maximum elevation
    VetraDialog(
        modifier = Modifier
            .vetraShadow(elevation = VetraTheme.shadows.xl)
    ) {
        Text("Modal")
    }
}
```

### Avoid Elevation Conflicts

Don't place high elevation items inside lower elevation containers:

```kotlin
// ❌ Bad: Dialog (xl) inside Card (sm)
VetraCard {
    VetraDialog { } // Breaks hierarchy
}

// ✅ Good: Proper layering
Box {
    VetraCard { /* Content */ }
    if (showDialog) {
        VetraDialog { /* Modal */ }
    }
}
```

## Best Practices

### ✅ Do

- Use predefined elevation levels
- Follow the elevation hierarchy
- Let components use default elevations
- Animate elevation changes smoothly
- Test in both light and dark modes
- Consider performance on lower-end devices

### ❌ Don't

- Create arbitrary elevation values
- Stack high elevation over higher elevation
- Apply excessive shadows
- Use shadows for decorative purposes only
- Ignore the adaptive shadow system
- Over-elevate standard content

## Accessibility

Elevation doesn't directly impact accessibility, but:

- **Color Contrast**: Ensure elevated surfaces maintain proper contrast
- **Focus Indicators**: Elevation should not obscure focus rings
- **Motion**: Reduce elevation animations if motion is reduced

```kotlin
// Ensure content on elevated surfaces is accessible
Box(
    modifier = Modifier
        .vetraShadow(elevation = VetraTheme.shadows.md)
        .background(VetraTheme.colors.canvasElevated)
) {
    Text(
        text = "Accessible text",
        color = VetraTheme.colors.textPrimary // Good contrast
    )
}
```

## Migration Guide

### From Standard Shadows

```kotlin
// Old
Modifier.shadow(elevation = 4.dp, shape = shape)

// New
Modifier.vetraShadow(elevation = VetraTheme.shadows.md, shape = shape)
```

### Elevation Mapping

- 0dp → `shadows.none` (0dp)
- 1dp → `shadows.xs` (1dp)
- 2-3dp → `shadows.sm` (2dp)
- 4-6dp → `shadows.md` (4dp)
- 8-12dp → `shadows.lg` (8dp)
- 16-20dp → `shadows.xl` (16dp)
- 24dp+ → `shadows.xxl` (24dp)

---

**See Also:**

- [Colors](colors.md) - Color system
- [Shapes](shapes.md) - Corner radius system

