# Shape System

Vetra UI's shape system provides a consistent corner radius scale for creating visual hierarchy and comfortable UI elements.

## Philosophy

- **Comfortable Corners**: Soft, approachable rounded corners
- **Clear Hierarchy**: Different sizes for different component types
- **Consistent Rhythm**: Predictable, harmonious scale
- **Elegant Balance**: Not too sharp, not too rounded

## Shape Scale

Vetra UI provides 8 predefined corner radius values:

```kotlin
VetraTheme.shapes.none   // 0dp - Sharp corners
VetraTheme.shapes.xs     // 4dp - Extra small
VetraTheme.shapes.sm     // 8dp - Small
VetraTheme.shapes.md     // 12dp - Medium
VetraTheme.shapes.lg     // 16dp - Large
VetraTheme.shapes.xl     // 24dp - Extra large
VetraTheme.shapes.xxl    // 32dp - 2X large
VetraTheme.shapes.full   // 9999dp - Fully rounded
```

## Shape Definitions

### None - 0dp

Sharp corners for specific design needs.

```kotlin
Box(
    modifier = Modifier
        .size(100.dp)
        .background(VetraTheme.colors.brand, shape = VetraTheme.shapes.none)
)
```

**Usage:**
- Images in grids
- Edge-to-edge layouts
- When sharp corners are intentional

### XS - 4dp

Extra small rounding for tiny elements.

```kotlin
VetraBadge(
    text = "New",
    shape = VetraTheme.shapes.xs
)
```

**Usage:**
- Badges
- Small chips
- Compact UI elements
- Status indicators

### SM - 8dp

Small rounding for buttons and inputs.

```kotlin
VetraButton(onClick = { }) {
    Text("Button") // Uses shapes.sm by default
}

VetraTextField(
    value = text,
    onValueChange = { text = it },
    shape = VetraTheme.shapes.sm
)
```

**Usage:**
- Buttons (default)
- Text fields
- Small cards
- Chips

### MD - 12dp

Medium rounding for cards and containers.

```kotlin
VetraCard(
    modifier = Modifier.fillMaxWidth(),
    shape = VetraTheme.shapes.md // Default
) {
    // Card content
}
```

**Usage:**
- Cards (default)
- Panels
- Medium containers
- List items

### LG - 16dp

Large rounding for prominent cards.

```kotlin
VetraElevatedCard(
    modifier = Modifier.size(200.dp),
    shape = VetraTheme.shapes.lg
) {
    // Featured content
}
```

**Usage:**
- Large cards
- Feature containers
- Image containers
- Bottom sheets

### XL - 24dp

Extra large rounding for dialogs and sheets.

```kotlin
VetraDialog(
    onDismissRequest = { },
    shape = VetraTheme.shapes.xl // Default
) {
    // Dialog content
}
```

**Usage:**
- Dialogs (default)
- Bottom sheets
- Modals
- Floating panels

### XXL - 32dp

Very large rounding for full-screen modals.

```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .background(
            VetraTheme.colors.canvasElevated,
            shape = VetraTheme.shapes.xxl
        )
)
```

**Usage:**
- Full-screen modals
- Large overlays
- Splash screens with rounded corners

### Full - 9999dp

Fully rounded (circular or pill-shaped).

```kotlin
// Circular
Box(
    modifier = Modifier
        .size(48.dp)
        .background(VetraTheme.colors.brand, shape = VetraTheme.shapes.full)
)

// Pill-shaped
Box(
    modifier = Modifier
        .height(32.dp)
        .width(100.dp)
        .background(VetraTheme.colors.accent, shape = VetraTheme.shapes.full)
)
```

**Usage:**
- Avatar images
- Icon buttons (circular)
- Pills and tags
- Floating action buttons
- Progress indicators

## Usage Examples

### Basic Usage

```kotlin
@Composable
fun ShapeExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Card with medium rounding
        VetraCard(shape = VetraTheme.shapes.md) {
            Text(
                text = "Standard Card",
                modifier = Modifier.padding(16.dp)
            )
        }
        
        // Button with small rounding
        VetraButton(onClick = { }) {
            Text("Button")
        }
        
        // Chip with small rounding
        VetraChip(
            text = "Chip",
            shape = VetraTheme.shapes.sm
        )
        
        // Badge with extra small rounding
        VetraBadge(
            text = "5",
            shape = VetraTheme.shapes.xs
        )
        
        // Avatar with full rounding
        Image(
            painter = painterResource("avatar.png"),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(48.dp)
                .clip(VetraTheme.shapes.full)
        )
    }
}
```

### Custom Shapes

```kotlin
// Apply custom shape to any component
Box(
    modifier = Modifier
        .size(100.dp)
        .background(
            color = VetraTheme.colors.brandSubtle,
            shape = VetraTheme.shapes.lg
        )
        .vetraShadow(
            elevation = VetraTheme.shadows.md,
            shape = VetraTheme.shapes.lg
        )
)
```

### Clipping Content

```kotlin
// Clip content to shape
Image(
    painter = rememberImagePainter("photo.jpg"),
    contentDescription = "Photo",
    modifier = Modifier
        .size(200.dp)
        .clip(VetraTheme.shapes.md)
)

// Clip with border
Image(
    painter = rememberImagePainter("photo.jpg"),
    contentDescription = "Photo",
    modifier = Modifier
        .size(200.dp)
        .border(
            width = 2.dp,
            color = VetraTheme.colors.border,
            shape = VetraTheme.shapes.md
        )
        .clip(VetraTheme.shapes.md)
)
```

### Animated Shapes

```kotlin
@Composable
fun AnimatedShapeButton() {
    var expanded by remember { mutableStateOf(false) }
    val cornerRadius by animateDpAsState(
        targetValue = if (expanded) 24.dp else 8.dp
    )
    
    Box(
        modifier = Modifier
            .height(48.dp)
            .width(if (expanded) 200.dp else 100.dp)
            .background(
                VetraTheme.colors.brand,
                RoundedCornerShape(cornerRadius)
            )
            .clickable { expanded = !expanded }
    )
}
```

## Component Default Shapes

Different components use different default shapes:

| Component | Default Shape | Reasoning |
|-----------|--------------|-----------|
| Button | `sm` (8dp) | Comfortable, balanced |
| Card | `md` (12dp) | Distinct, not too rounded |
| Dialog | `xl` (24dp) | Prominent, friendly |
| TextField | `sm` (8dp) | Consistent with buttons |
| Chip | `sm` (8dp) | Compact, clear |
| Badge | `xs` (4dp) | Small, subtle |
| Icon Button | `full` | Circular, traditional |
| Avatar | `full` | Circular standard |
| Menu | `md` (12dp) | Comfortable dropdown |
| Bottom Sheet | `xl` (24dp) | Friendly, accessible |

## Customization

### Custom Shape Scale

```kotlin
val CustomShapes = DefaultVetraShapes.copy(
    sm = RoundedCornerShape(6.dp),  // Less rounded buttons
    md = RoundedCornerShape(16.dp), // More rounded cards
    full = RoundedCornerShape(50)   // Perfect circles
)

VetraTheme(shapes = CustomShapes) {
    // Your app with custom shapes
}
```

### Per-Component Shapes

```kotlin
// Override shape for specific component
VetraButton(
    onClick = { },
    modifier = Modifier
        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
) {
    Text("Custom Shape")
}
```

### Complex Shapes

```kotlin
// Different corners
val customShape = RoundedCornerShape(
    topStart = 16.dp,
    topEnd = 16.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)

Box(
    modifier = Modifier
        .size(100.dp)
        .background(VetraTheme.colors.brand, customShape)
)

// Cutout shape
val cutoutShape = RoundedCornerShape(
    topStart = 16.dp,
    topEnd = 0.dp,
    bottomEnd = 16.dp,
    bottomStart = 0.dp
)
```

## Guidelines

### Size Selection

Choose shapes based on component size:

```kotlin
// Small components (< 40dp)
VetraBadge(shape = VetraTheme.shapes.xs)

// Medium components (40-100dp)
VetraButton(shape = VetraTheme.shapes.sm)

// Large components (100-300dp)
VetraCard(shape = VetraTheme.shapes.md)

// Very large components (> 300dp)
VetraDialog(shape = VetraTheme.shapes.xl)
```

### Consistency

Maintain consistent shapes for similar components:

```kotlin
// ✅ Consistent
VetraButton(onClick = { }) { Text("Save") }
VetraSecondaryButton(onClick = { }) { Text("Cancel") }
// Both use shapes.sm

// ❌ Inconsistent
VetraButton(onClick = { }) { Text("Save") } // shapes.sm
Box(
    modifier = Modifier
        .background(color, RoundedCornerShape(20.dp)) // Random size
) { Text("Cancel") }
```

### Visual Hierarchy

Use shapes to reinforce hierarchy:

```kotlin
Column {
    // Primary action - standard rounding
    VetraButton(onClick = { }) {
        Text("Continue")
    }
    
    // Secondary action - same rounding for consistency
    VetraOutlinedButton(onClick = { }) {
        Text("Go Back")
    }
    
    // Info card - slightly more rounded
    VetraCard(shape = VetraTheme.shapes.lg) {
        Text("Additional information")
    }
}
```

## Best Practices

### ✅ Do

- Use predefined shape values
- Match shape to component size
- Maintain consistency across similar elements
- Consider the overall visual rhythm
- Use `full` for avatars and circular buttons
- Clip images to shapes for polish

### ❌ Don't

- Create random corner radius values
- Mix too many different shapes
- Use excessive rounding (except for `full`)
- Apply sharp corners without reason
- Ignore the scale for custom components
- Over-round small components

## Accessibility

Shapes don't directly impact accessibility, but consider:

- **Touch Targets**: Ensure touch areas are at least 44×44dp regardless of visual shape
- **Focus Indicators**: Shape should accommodate focus rings
- **Consistency**: Predictable shapes help users understand interactivity

```kotlin
// Touch target is larger than visual shape
VetraButton(
    onClick = { },
    modifier = Modifier
        .defaultMinSize(minWidth = 88.dp, minHeight = 44.dp) // Minimum touch target
) {
    Text("Button")
}
```

## Migration from Material

```kotlin
// Material
Button(
    onClick = { },
    shape = MaterialTheme.shapes.medium
)

// Vetra
VetraButton(
    onClick = { }
) // Uses VetraTheme.shapes.sm automatically
```

---

**Next:** Learn about [Elevation →](elevation.md)


