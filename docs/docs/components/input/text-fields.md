# Text Fields

Text fields allow users to input text with animated label and underline.

## Overview

VetraTextField features elegant animations:

- Animated underline expands from center on focus
- Floating label scales and slides smoothly
- Placeholder fades in after label floats
- Clear visual states (focused, error, disabled)

```kotlin
var text by remember { mutableStateOf("") }

VetraTextField(
    value = text,
    onValueChange = { text = it },
    label = "Username",
    placeholder = "Enter your username"
)
```

## States

=== "Default"

    ```kotlin
    VetraTextField(
        value = text,
        onValueChange = { text = it },
        label = "Email"
    )
    ```

=== "With Icons"

    ```kotlin
    VetraTextField(
        value = email,
        onValueChange = { email = it },
        label = "Email",
        leadingIcon = {
            Icon(Icons.Default.Email, null)
        }
    )
    ```

=== "Error"

    ```kotlin
    VetraTextField(
        value = invalidEmail,
        onValueChange = { /* */ },
        label = "Email",
        isError = true  // (1)!
    )
    ```
    
    1. Shows danger color for underline and label

=== "Disabled"

    ```kotlin
    VetraTextField(
        value = "Read only",
        onValueChange = { },
        label = "Status",
        enabled = false
    )
    ```

## Common Patterns

### Form Fields

```kotlin
Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
    VetraTextField(
        value = name,
        onValueChange = { name = it },
        label = "Full Name",
        placeholder = "John Doe"
    )
    
    VetraTextField(
        value = email,
        onValueChange = { email = it },
        label = "Email",
        placeholder = "john@example.com",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        )
    )
    
    VetraTextField(
        value = password,
        onValueChange = { password = it },
        label = "Password",
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
}
```

### Search Field

```kotlin
VetraTextField(
    value = query,
    onValueChange = { query = it },
    placeholder = "Search...",  // (1)!
    leadingIcon = {
        Icon(Icons.Default.Search, "Search")
    },
    trailingIcon = {
        if (query.isNotEmpty()) {
            IconButton(onClick = { query = "" }) {
                Icon(Icons.Default.Clear, "Clear")
            }
        }
    }
)
```

1. No label for cleaner search UI

### Validation

```kotlin
var email by remember { mutableStateOf("") }
val isValidEmail = remember(email) {
    email.matches(Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
}

VetraTextField(
    value = email,
    onValueChange = { email = it },
    label = "Email",
    isError = email.isNotEmpty() && !isValidEmail,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
)

if (email.isNotEmpty() && !isValidEmail) {
    Text(
        text = "Please enter a valid email",
        color = VetraTheme.colors.danger,
        style = VetraTheme.typography.bodySm,
        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
    )
}
```

### Multiline Input

```kotlin
VetraTextField(
    value = description,
    onValueChange = { description = it },
    label = "Description",
    placeholder = "Tell us more...",
    singleLine = false,
    maxLines = 5
)
```

## Icons

### Leading Icon

Use for input type indication:

```kotlin
VetraTextField(
    value = email,
    onValueChange = { email = it },
    label = "Email",
    leadingIcon = {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = null,
            tint = VetraTheme.colors.textSecondary
        )
    }
)
```

### Trailing Icon

Use for actions or status:

```kotlin
VetraTextField(
    value = password,
    onValueChange = { password = it },
    label = "Password",
    visualTransformation = if (showPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    },
    trailingIcon = {
        IconButton(onClick = { showPassword = !showPassword }) {
            Icon(
                if (showPassword) Icons.Default.VisibilityOff
                else Icons.Default.Visibility,
                "Toggle visibility"
            )
        }
    }
)
```

## Keyboard Options

```kotlin
// Email
keyboardOptions = KeyboardOptions(
    keyboardType = KeyboardType.Email,
    imeAction = ImeAction.Next
)

// Phone
keyboardOptions = KeyboardOptions(
    keyboardType = KeyboardType.Phone
)

// Number
keyboardOptions = KeyboardOptions(
    keyboardType = KeyboardType.Number
)
```

## Best Practices

!!! success "Do"
    - ✅ Use descriptive labels
    - ✅ Provide helpful placeholders
    - ✅ Show clear error messages
    - ✅ Use appropriate keyboard types
    - ✅ Validate input appropriately
    - ✅ Disable when not editable

!!! failure "Don't"
    - ❌ Use placeholder as sole label
    - ❌ Show errors before user finishes typing
    - ❌ Use unclear error messages
    - ❌ Make required fields unclear
    - ❌ Forget to handle keyboard actions
    - ❌ Use tiny text or inadequate spacing

## Accessibility

- **Label Position**: Floating label stays visible with content
- **Error States**: Clear visual indication with color and message
- **Focus**: Animated underline shows focus state
- **Keyboard**: Proper keyboard types for input context
- **Screen Readers**: Labels are properly announced

!!! tip "Improve Accessibility"
    - Always provide labels for screen readers
    - Use descriptive error messages
    - Ensure adequate touch targets for trailing icons
    - Test with keyboard navigation

## API Reference

### VetraTextField

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `value` | `String` | Required | Current text value |
| `onValueChange` | `(String) -> Unit` | Required | Callback when text changes |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether field is editable |
| `readOnly` | `Boolean` | `false` | Whether field is read-only |
| `label` | `String?` | `null` | Floating label text |
| `placeholder` | `String?` | `null` | Placeholder text (shows after label floats) |
| `leadingIcon` | `@Composable (() -> Unit)?` | `null` | Icon before text |
| `trailingIcon` | `@Composable (() -> Unit)?` | `null` | Icon after text |
| `isError` | `Boolean` | `false` | Whether field is in error state |
| `visualTransformation` | `VisualTransformation` | `None` | Transform display (e.g., password) |
| `keyboardOptions` | `KeyboardOptions` | `Default` | Keyboard configuration |
| `keyboardActions` | `KeyboardActions` | `Default` | Keyboard action handlers |
| `singleLine` | `Boolean` | `true` | Single or multiline input |
| `maxLines` | `Int` | `1` if single, `Int.MAX_VALUE` if multi | Maximum lines |

### Default Dimensions

| Property | Value | Note |
|----------|-------|------|
| Min Height | 58dp | Comfortable touch target |
| Horizontal Padding | 16dp | Internal spacing |
| Vertical Padding | 20dp | Internal spacing |
| Underline Width | 2dp | Focus indicator |
| Corner Radius | 8dp | `shapes.sm` |

### Animation Timing

| Animation | Duration | Easing | Description |
|-----------|----------|--------|-------------|
| Underline Expansion | 300ms | EaseInOutCubic | Expands from center |
| Label Float | 300ms | EaseOut | Scales and moves up |
| Placeholder Fade | 250ms | EaseOut | Fades in after label |
| Color Transition | 300ms | EaseInOutCubic | Smooth color changes |

### Color States

| State | Underline | Label | Text |
|-------|-----------|-------|------|
| **Default** | `border` | `textSecondary` | `textPrimary` |
| **Focused** | `brand` | `textSecondary` | `textPrimary` |
| **Error** | `danger` | `danger` | `textPrimary` |
| **Disabled** | `border` | `textDisabled` | `textDisabled` |

---

**See Also:**

- [Buttons](buttons.md) - Form submission buttons
- [Design: Colors](../../design-system/colors.md) - Text field colors
- [Design: Typography](../../design-system/typography.md) - Text styles

