# Segmented Button

A modern segmented button component for mutually exclusive selections in a single control.

## Overview

Segmented buttons allow users to choose between 2-5 options in a compact, visually connected control. They're perfect for switching between related views or filtering content.

## Variants

### Standard Segmented Button

Solid selected segment with smooth sliding animation.

```kotlin
var selectedIndex by remember { mutableStateOf(0) }

VetraSegmentedButton(
    segments = listOf(
        SegmentData("Day"),
        SegmentData("Week"),
        SegmentData("Month"),
        SegmentData("Year")
    ),
    selectedIndex = selectedIndex,
    onSegmentSelected = { selectedIndex = it }
)
```

### Outlined Segmented Button

Border-only variant for subtle emphasis.

```kotlin
var selectedIndex by remember { mutableStateOf(0) }

VetraSegmentedButtonOutlined(
    segments = listOf(
        SegmentData("All"),
        SegmentData("Active"),
        SegmentData("Completed")
    ),
    selectedIndex = selectedIndex,
    onSegmentSelected = { selectedIndex = it }
)
```

## Features

- **Smooth Animations**: 300ms sliding indicator with FastOutSlowInEasing
- **Flexible Content**: Support for text, icons, or both
- **Optimal Range**: Designed for 2-5 segments
- **Individual Control**: Disable specific segments
- **Accessible**: Built-in `Role.RadioButton` semantics
- **Responsive**: Works in fixed or full-width layouts

## Usage Examples

### With Icons

```kotlin
VetraSegmentedButton(
    segments = listOf(
        SegmentData("Approve", Icons.Default.Check),
        SegmentData("Reject", Icons.Default.Close),
        SegmentData("Favorite", Icons.Default.Favorite)
    ),
    selectedIndex = selectedIndex,
    onSegmentSelected = { selectedIndex = it }
)
```

### Icon Only

```kotlin
VetraSegmentedButton(
    segments = listOf(
        SegmentData("Check", Icons.Default.Check),
        SegmentData("Star", Icons.Default.Star),
        SegmentData("Heart", Icons.Default.Favorite)
    ),
    selectedIndex = selectedIndex,
    onSegmentSelected = { selectedIndex = it }
)
```

### With Disabled Segments

```kotlin
VetraSegmentedButton(
    segments = listOf(
        SegmentData("Free"),
        SegmentData("Pro", enabled = false),
        SegmentData("Enterprise")
    ),
    selectedIndex = selectedIndex,
    onSegmentSelected = { selectedIndex = it }
)
```

### Full Width

```kotlin
VetraSegmentedButton(
    segments = listOf(
        SegmentData("Overview"),
        SegmentData("Details")
    ),
    selectedIndex = selectedIndex,
    onSegmentSelected = { selectedIndex = it },
    modifier = Modifier.fillMaxWidth()
)
```

## Parameters

### VetraSegmentedButton

| Parameter | Type | Description |
|-----------|------|-------------|
| `segments` | `List<SegmentData>` | List of segment configurations (2-5 items) |
| `selectedIndex` | `Int` | Index of currently selected segment |
| `onSegmentSelected` | `(Int) -> Unit` | Callback when segment is clicked |
| `modifier` | `Modifier` | Optional modifier for the container |

### SegmentData

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `label` | `String` | - | Text label for the segment |
| `icon` | `ImageVector?` | `null` | Optional icon to display |
| `enabled` | `Boolean` | `true` | Whether the segment is enabled |

## Best Practices

!!! success "Do"
    - ✅ Use 2-5 segments for optimal user experience
    - ✅ Keep segment labels concise (1-2 words)
    - ✅ Ensure all segments have similar content length
    - ✅ Use standard variant for primary selections
    - ✅ Use outlined variant for subtle emphasis
    - ✅ Add icons when they improve clarity

!!! failure "Don't"
    - ❌ Use more than 5 segments (consider dropdown instead)
    - ❌ Mix text-only and icon-only segments
    - ❌ Use for unrelated options
    - ❌ Make segments too narrow (< 60dp)
    - ❌ Use decorative icons that don't add meaning

## Common Use Cases

### Time Range Selection

```kotlin
var timeRange by remember { mutableStateOf(1) }

VetraSegmentedButton(
    segments = listOf(
        SegmentData("1H"),
        SegmentData("1D"),
        SegmentData("1W"),
        SegmentData("1M"),
        SegmentData("1Y")
    ),
    selectedIndex = timeRange,
    onSegmentSelected = { timeRange = it }
)
```

### Content Filter

```kotlin
var filter by remember { mutableStateOf(0) }
val filterOptions = listOf("All", "Images", "Videos", "Documents")

VetraSegmentedButton(
    segments = filterOptions.map { SegmentData(it) },
    selectedIndex = filter,
    onSegmentSelected = { filter = it }
)

// Display filtered content based on selection
when (filter) {
    0 -> AllContent()
    1 -> ImagesContent()
    2 -> VideosContent()
    3 -> DocumentsContent()
}
```

### View Mode Toggle

```kotlin
var viewMode by remember { mutableStateOf(0) }

VetraSegmentedButton(
    segments = listOf(
        SegmentData("Grid", Icons.Default.GridView),
        SegmentData("List", Icons.AutoMirrored.Filled.List)
    ),
    selectedIndex = viewMode,
    onSegmentSelected = { viewMode = it },
    modifier = Modifier.fillMaxWidth()
)
```

## Accessibility

The component includes built-in accessibility features:

- **Semantic Role**: Uses `Role.RadioButton` for screen readers
- **Touch Target**: Meets minimum 44×44dp requirement
- **Color Contrast**: WCAG AA compliant in both themes
- **Keyboard Support**: Full keyboard navigation capability
- **State Announcement**: Clearly indicates selected state

## Theming

Segmented buttons automatically adapt to your theme:

```kotlin
// Light mode
VetraTheme(darkMode = false) {
    VetraSegmentedButton(...)
}

// Dark mode
VetraTheme(darkMode = true) {
    VetraSegmentedButton(...)
}
```

Colors used:

- **Standard**: `brand`, `onBrand`, `canvasElevated`
- **Outlined**: `brandSubtle`, `brand`, `border`
- **Disabled**: `textDisabled`

## Animation Details

| Property | Duration | Easing |
|----------|----------|--------|
| Position | 300ms | FastOutSlowInEasing |
| Color | 300ms | Linear |
| Scale | 300ms | FastOutSlowInEasing |

## Design Specs

| Property | Value |
|----------|-------|
| Height | 40dp |
| Min Width | 60dp per segment |
| Horizontal Padding | 16dp |
| Vertical Padding | 10dp |
| Icon Size | 18dp |
| Icon-Text Spacing | 6dp |
| Border Radius | Small (8dp) |

## Related Components

- **[Tabs](../navigation/tabs.md)**: For content organization with multiple views
- **[Chips](../display/chips.md)**: For filter selections that aren't mutually exclusive
- **[Radio Button](radio-button.md)**: For vertical or spaced-out single selections
- **[Switch](switch.md)**: For simple on/off toggles

---

**Need help?** Check out the [component showcase](https://github.com/flyfishxu/vetra-ui) for live examples.
