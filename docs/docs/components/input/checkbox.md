# Checkbox

Checkboxes allow users to select multiple options with smooth animations and elegant design.

## Overview

VetraCheckbox provides a modern checkbox component with:

- Smooth checkmark animation with scale effect
- Color transitions for checked/unchecked states
- Indeterminate state support
- Clear visual feedback
- Accessible by default

```kotlin
var checked by remember { mutableStateOf(false) }

VetraCheckbox(
    checked = checked,
    onCheckedChange = { checked = it }
)
```

## Variants

=== "Standard"

    Basic checkbox for selections.
    
    ```kotlin
    VetraCheckbox(
        checked = checked,
        onCheckedChange = { checked = it }
    )
    ```

=== "With Label"

    Checkbox with associated text label.
    
    ```kotlin
    VetraCheckboxWithLabel(
        checked = checked,
        onCheckedChange = { checked = it },
        label = "Accept terms and conditions"
    )
    ```

=== "Indeterminate"

    Checkbox in indeterminate state (partially selected).
    
    ```kotlin
    VetraCheckbox(
        checked = allSelected,
        onCheckedChange = { selectAll() },
        indeterminate = someSelected
    )
    ```

## States

=== "Unchecked"

    ```kotlin
    VetraCheckbox(
        checked = false,
        onCheckedChange = { }
    )
    ```

=== "Checked"

    ```kotlin
    VetraCheckbox(
        checked = true,
        onCheckedChange = { }
    )
    ```

=== "Indeterminate"

    ```kotlin
    VetraCheckbox(
        checked = false,
        onCheckedChange = { },
        indeterminate = true
    )
    ```

=== "Disabled"

    ```kotlin
    VetraCheckbox(
        checked = false,
        onCheckedChange = { },
        enabled = false
    )
    ```

## Common Patterns

### Multiple Selection

```kotlin
var option1 by remember { mutableStateOf(false) }
var option2 by remember { mutableStateOf(false) }
var option3 by remember { mutableStateOf(false) }

Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    VetraCheckboxWithLabel(
        checked = option1,
        onCheckedChange = { option1 = it },
        label = "Option 1"
    )
    VetraCheckboxWithLabel(
        checked = option2,
        onCheckedChange = { option2 = it },
        label = "Option 2"
    )
    VetraCheckboxWithLabel(
        checked = option3,
        onCheckedChange = { option3 = it },
        label = "Option 3"
    )
}
```

### Select All Pattern

```kotlin
var option1 by remember { mutableStateOf(true) }
var option2 by remember { mutableStateOf(false) }
var option3 by remember { mutableStateOf(false) }

val allChecked = option1 && option2 && option3
val someChecked = !allChecked && (option1 || option2 || option3)

Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    VetraCheckboxWithLabel(
        checked = allChecked,
        onCheckedChange = { newChecked ->
            option1 = newChecked
            option2 = newChecked
            option3 = newChecked
        },
        label = "Select All",
        indeterminate = someChecked
    )
    
    Column(
        modifier = Modifier.padding(start = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        VetraCheckboxWithLabel(
            checked = option1,
            onCheckedChange = { option1 = it },
            label = "Option 1"
        )
        VetraCheckboxWithLabel(
            checked = option2,
            onCheckedChange = { option2 = it },
            label = "Option 2"
        )
        VetraCheckboxWithLabel(
            checked = option3,
            onCheckedChange = { option3 = it },
            label = "Option 3"
        )
    }
}
```

### Filter List

```kotlin
var showCompleted by remember { mutableStateOf(true) }
var showArchived by remember { mutableStateOf(false) }

Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Text("Filters", style = VetraTheme.typography.headingSm)
    
    VetraCheckboxWithLabel(
        checked = showCompleted,
        onCheckedChange = { showCompleted = it },
        label = "Show completed tasks"
    )
    
    VetraCheckboxWithLabel(
        checked = showArchived,
        onCheckedChange = { showArchived = it },
        label = "Show archived tasks"
    )
}
```

## Best Practices

!!! success "Do"
    - ✅ Use checkboxes for multiple selections
    - ✅ Provide clear, descriptive labels
    - ✅ Use indeterminate state for "select all" scenarios
    - ✅ Group related checkboxes together
    - ✅ Show selected count when applicable

!!! failure "Don't"
    - ❌ Use checkboxes for single selection (use radio buttons)
    - ❌ Use checkboxes for navigation (use buttons or tabs)
    - ❌ Nest checkboxes too deeply
    - ❌ Use vague labels without context

## Accessibility

- **Touch Targets**: Minimum 44×44dp built-in
- **Color Contrast**: Meets WCAG AA standards
- **Screen Readers**: Semantic `Role.Checkbox` support
- **Indeterminate State**: Properly announced by screen readers

!!! tip "Improve Accessibility"
    ```kotlin
    VetraCheckboxWithLabel(
        checked = accepted,
        onCheckedChange = { accepted = it },
        label = "I accept the terms and conditions",
        enabled = termsLoaded
    )
    ```

## API Reference

### VetraCheckbox

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `checked` | `Boolean` | Required | Whether the checkbox is checked |
| `onCheckedChange` | `((Boolean) -> Unit)?` | `null` | Callback when checkbox is toggled |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the checkbox is interactive |
| `indeterminate` | `Boolean` | `false` | Whether checkbox is in indeterminate state |

### VetraCheckboxWithLabel

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `checked` | `Boolean` | Required | Whether the checkbox is checked |
| `onCheckedChange` | `((Boolean) -> Unit)?` | `null` | Callback when checkbox is toggled |
| `label` | `String` | Required | Label text |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether the checkbox is interactive |
| `indeterminate` | `Boolean` | `false` | Whether checkbox is in indeterminate state |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Size | 20dp | Checkbox size |
| Touch Target | 44dp | Meets accessibility standards |
| Border Width | 2dp | Unchecked state |
| Animation Duration | 250ms | Smooth transition |

---

**See Also:**

- [Switch](switch.md) - For binary on/off states
- [Radio Button](radio-button.md) - For single selection from group

