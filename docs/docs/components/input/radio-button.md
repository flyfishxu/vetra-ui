# Radio Button

Radio buttons allow users to select a single option from a group with smooth animations and elegant design.

## Overview

VetraRadioButton provides a modern radio button component with:

- Smooth scale transition for the inner indicator
- Color transitions for selected/unselected states
- Clear visual feedback
- Group management support
- Accessible by default

```kotlin
var selectedOption by remember { mutableStateOf("option1") }

VetraRadioButton(
    selected = selectedOption == "option1",
    onClick = { selectedOption = "option1" }
)
```

## Variants

=== "Standard"

    Basic radio button for single selection.
    
    ```kotlin
    VetraRadioButton(
        selected = selected,
        onClick = { selected = true }
    )
    ```

=== "With Label"

    Radio button with associated text label.
    
    ```kotlin
    VetraRadioButtonWithLabel(
        selected = selectedOption == "option1",
        onClick = { selectedOption = "option1" },
        label = "Option 1"
    )
    ```

=== "Group"

    Convenience composable for managing a group of radio buttons.
    
    ```kotlin
    VetraRadioGroup(
        options = listOf("Small", "Medium", "Large"),
        selectedOption = selectedSize,
        onOptionSelected = { selectedSize = it }
    )
    ```

## States

=== "Unselected"

    ```kotlin
    VetraRadioButton(
        selected = false,
        onClick = { }
    )
    ```

=== "Selected"

    ```kotlin
    VetraRadioButton(
        selected = true,
        onClick = { }
    )
    ```

=== "Disabled"

    ```kotlin
    VetraRadioButton(
        selected = false,
        onClick = { },
        enabled = false
    )
    ```

## Common Patterns

### Simple Selection

```kotlin
var selectedIndex by remember { mutableStateOf(0) }

Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    VetraRadioButtonWithLabel(
        selected = selectedIndex == 0,
        onClick = { selectedIndex = 0 },
        label = "Option 1"
    )
    VetraRadioButtonWithLabel(
        selected = selectedIndex == 1,
        onClick = { selectedIndex = 1 },
        label = "Option 2"
    )
    VetraRadioButtonWithLabel(
        selected = selectedIndex == 2,
        onClick = { selectedIndex = 2 },
        label = "Option 3"
    )
}
```

### Using Radio Group

```kotlin
var selectedSize by remember { mutableStateOf("Medium") }

VetraRadioGroup(
    options = listOf("Small", "Medium", "Large"),
    selectedOption = selectedSize,
    onOptionSelected = { selectedSize = it }
)
```

### Custom Option Labels

```kotlin
data class Plan(val id: String, val name: String, val price: String)

var selectedPlan by remember { mutableStateOf(plans[0]) }

VetraRadioGroup(
    options = plans,
    selectedOption = selectedPlan,
    onOptionSelected = { selectedPlan = it },
    optionLabel = { "${it.name} - ${it.price}" }
)
```

### Settings Screen

```kotlin
var theme by remember { mutableStateOf("light") }
var language by remember { mutableStateOf("en") }

Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Theme", style = VetraTheme.typography.headingSm)
        VetraRadioGroup(
            options = listOf("Light", "Dark", "Auto"),
            selectedOption = theme,
            onOptionSelected = { theme = it }
        )
    }
    
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Language", style = VetraTheme.typography.headingSm)
        VetraRadioGroup(
            options = listOf("English", "Spanish", "French"),
            selectedOption = language,
            onOptionSelected = { language = it }
        )
    }
}
```

## Best Practices

!!! success "Do"
    - ✅ Use radio buttons for single selection from a group
    - ✅ Group related radio buttons together
    - ✅ Provide clear, descriptive labels
    - ✅ Always have one option selected by default when appropriate
    - ✅ Use visual grouping (cards, dividers) for clarity

!!! failure "Don't"
    - ❌ Use radio buttons for multiple selections (use checkboxes)
    - ❌ Use radio buttons for binary on/off (use switches)
    - ❌ Mix radio buttons with checkboxes in the same group
    - ❌ Use radio buttons for navigation (use tabs)

## Accessibility

- **Touch Targets**: Minimum 44×44dp built-in
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Semantic `Role.RadioButton` support
- **Group Management**: Properly grouped for screen readers

!!! tip "Improve Accessibility"
    ```kotlin
    VetraRadioGroup(
        options = sizes,
        selectedOption = selectedSize,
        onOptionSelected = { selectedSize = it },
        optionLabel = { "Size: $it" }
    )
    ```

## API Reference

### VetraRadioButton

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `selected` | `Boolean` | Required | Whether the radio button is selected |
| `onClick` | `(() -> Unit)?` | `null` | Callback when radio button is clicked |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the radio button is interactive |

### VetraRadioButtonWithLabel

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `selected` | `Boolean` | Required | Whether the radio button is selected |
| `onClick` | `(() -> Unit)?` | `null` | Callback when radio button is clicked |
| `label` | `String` | Required | Label text |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the radio button is interactive |

### VetraRadioGroup

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `options` | `List<T>` | Required | List of options to display |
| `selectedOption` | `T` | Required | Currently selected option |
| `onOptionSelected` | `(T) -> Unit` | Required | Callback when an option is selected |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the radio buttons are enabled |
| `optionLabel` | `(T) -> String` | `{ it.toString() }` | Lambda to get the label for each option |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Size | 20dp | Radio button size |
| Inner Circle | 10dp | Selected indicator |
| Touch Target | 44dp | Meets accessibility standards |
| Border Width | 2dp | Outer circle |
| Animation Duration | 250ms | Smooth transition |

---

**See Also:**

- [Checkbox](checkbox.md) - For multiple selections
- [Switch](switch.md) - For binary on/off states

