# Slider

Sliders allow users to select a value from a continuous or discrete range with smooth dragging interaction.

## Overview

VetraSlider provides a modern slider component with:

- Smooth dragging interaction
- Elegant thumb with soft shadow
- Clear track visualization
- Support for continuous and discrete values
- Range slider support
- Accessible by default

```kotlin
var value by remember { mutableStateOf(0.5f) }

VetraSlider(
    value = value,
    onValueChange = { value = it }
)
```

## Variants

=== "Continuous"

    Standard slider for continuous value selection.
    
    ```kotlin
    VetraSlider(
        value = value,
        onValueChange = { value = it },
        valueRange = 0f..100f
    )
    ```

=== "Discrete"

    Slider with discrete steps.
    
    ```kotlin
    VetraSlider(
        value = value,
        onValueChange = { value = it },
        valueRange = 0f..5f,
        steps = 3
    )
    ```

=== "With Label"

    Slider with label and value display.
    
    ```kotlin
    VetraSliderWithLabel(
        value = volume,
        onValueChange = { volume = it },
        label = "Volume",
        valueRange = 0f..100f,
        valueFormatter = { "${it.toInt()}%" }
    )
    ```

=== "Range"

    Range slider for selecting a range of values.
    
    ```kotlin
    VetraRangeSlider(
        values = rangeValues,
        onValuesChange = { rangeValues = it },
        valueRange = 0f..100f
    )
    ```

## Common Patterns

### Volume Control

```kotlin
var volume by remember { mutableStateOf(50f) }

VetraSliderWithLabel(
    value = volume,
    onValueChange = { volume = it },
    label = "Volume",
    valueRange = 0f..100f,
    valueFormatter = { "${it.toInt()}%" }
)
```

### Rating Slider

```kotlin
var rating by remember { mutableStateOf(3f) }

VetraSliderWithLabel(
    value = rating,
    onValueChange = { rating = it },
    label = "Rating",
    valueRange = 0f..5f,
    steps = 4,
    valueFormatter = { "${it.toInt()}/5" }
)
```

### Price Range Filter

```kotlin
var priceRange by remember { mutableStateOf(0f..1000f) }

Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Text("Price Range")
    VetraRangeSlider(
        values = priceRange,
        onValuesChange = { priceRange = it },
        valueRange = 0f..1000f
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("$${priceRange.start.toInt()}")
        Text("$${priceRange.endInclusive.toInt()}")
    }
}
```

### Brightness Control

```kotlin
var brightness by remember { mutableStateOf(0.7f) }

VetraSliderWithLabel(
    value = brightness,
    onValueChange = { brightness = it },
    label = "Brightness",
    valueFormatter = { "${(it * 100).toInt()}%" }
)
```

### Disabled State

```kotlin
VetraSlider(
    value = 0.5f,
    onValueChange = { },
    enabled = false
)
```

## Best Practices

!!! success "Do"
    - ✅ Use sliders for value selection within a range
    - ✅ Provide clear labels and value displays
    - ✅ Use discrete steps when values are meaningful
    - ✅ Show current value when helpful
    - ✅ Use range sliders for filtering

!!! failure "Don't"
    - ❌ Use sliders for binary choices (use switches)
    - ❌ Use sliders for small sets of options (use radio buttons)
    - ❌ Hide the current value when it's important
    - ❌ Make sliders too small to interact with

## Accessibility

- **Touch Targets**: Thumb size meets accessibility standards
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Proper content description
- **Keyboard Navigation**: Full keyboard support

!!! tip "Improve Accessibility"
    ```kotlin
    VetraSliderWithLabel(
        value = value,
        onValueChange = { value = it },
        label = "Volume level",
        valueFormatter = { "${it.toInt()} percent" }
    )
    ```

## API Reference

### VetraSlider

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `value` | `Float` | Required | Current value of the slider |
| `onValueChange` | `(Float) -> Unit` | Required | Callback when value changes during drag |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the slider is interactive |
| `valueRange` | `ClosedFloatingPointRange<Float>` | `0f..1f` | Range of values the slider can take |
| `steps` | `Int` | `0` | Number of discrete steps (0 for continuous) |
| `onValueChangeFinished` | `(() -> Unit)?` | `null` | Callback when user stops dragging |

### VetraSliderWithLabel

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `value` | `Float` | Required | Current value of the slider |
| `onValueChange` | `(Float) -> Unit` | Required | Callback when value changes during drag |
| `label` | `String` | Required | Label text |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the slider is interactive |
| `valueRange` | `ClosedFloatingPointRange<Float>` | `0f..1f` | Range of values the slider can take |
| `steps` | `Int` | `0` | Number of discrete steps (0 for continuous) |
| `showValue` | `Boolean` | `true` | Whether to show the current value |
| `valueFormatter` | `(Float) -> String` | `{ "${(it * 10).toInt() / 10.0}" }` | Custom formatter for the value display |
| `onValueChangeFinished` | `(() -> Unit)?` | `null` | Callback when user stops dragging |

### VetraRangeSlider

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `values` | `ClosedFloatingPointRange<Float>` | Required | Current start and end values |
| `onValuesChange` | `(ClosedFloatingPointRange<Float>) -> Unit` | Required | Callback when values change during drag |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the slider is interactive |
| `valueRange` | `ClosedFloatingPointRange<Float>` | `0f..1f` | Range of values the slider can take |
| `steps` | `Int` | `0` | Number of discrete steps (0 for continuous) |
| `onValuesChangeFinished` | `(() -> Unit)?` | `null` | Callback when user stops dragging |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Height | 40dp | Slider height |
| Track Height | 4dp | Track thickness |
| Thumb Size | 20dp | Default thumb size |
| Thumb Hover Size | 24dp | Size when dragging |
| Animation Duration | 150ms | Thumb animation |

---

**See Also:**

- [Switch](switch.md) - For binary on/off states
- [Checkbox](checkbox.md) - For multiple selections

