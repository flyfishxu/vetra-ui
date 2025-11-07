# Development Guidelines

Code style and best practices for contributing to Vetra UI.

## Code Style

Follow Kotlin coding conventions:

- Use meaningful names
- Keep functions focused and small
- Add comments for complex logic
- Write in English

## Component Development

When creating components:

1. **Start with Design**: Consider the component's purpose and user experience
2. **Follow MVI Architecture**: Maintain separation of concerns
3. **Design for Extensibility**: Think long-term
4. **Match Material API**: Keep learning curve low
5. **Avoid Material Wrapping**: Build from scratch
6. **Support Cross-Platform**: Test on multiple platforms

## File Organization

```
core/src/commonMain/kotlin/com/flyfishxu/vetraui/core/
├── Button.kt              # Button components
├── Card.kt                # Card components
├── theme/
│   ├── VetraTheme.kt     # Theme composable
│   ├── VetraColorScheme.kt
│   └── ...
└── indication/           # Interaction effects
```

## Writing Components

```kotlin
/**
 * Component Name
 *
 * Brief description of the component and its purpose.
 *
 * @param param1 Description
 * @param param2 Description
 */
@Composable
fun VetraComponent(
    param1: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = VetraTheme.colors
    val shapes = VetraTheme.shapes
    
    // Implementation
}

// ============================================================================
// Previews
// ============================================================================

@Preview
@Composable
private fun VetraComponentPreview() {
    VetraTheme {
        // Light mode preview
    }
}

@Preview
@Composable
private fun VetraComponentDarkPreview() {
    VetraTheme(darkMode = true) {
        // Dark mode preview
    }
}
```

## Testing

- Add `@Preview` for all components
- Test in both light and dark modes
- Verify cross-platform compatibility
- Check accessibility

## Design Principles

Remember Vetra UI's core principles:

### Elegance Over Flash
No effects for effect's sake—every element serves a purpose

### Unity Over Variety
Consistent design language reduces cognitive load

### Natural Over Mechanical
Motion follows physics, interactions follow intuition

### Clarity Over Abstraction
Intuitive naming and predictable APIs

## Component Checklist

When creating a new component, ensure you complete all items in this checklist:

!!! tip "Component Checklist"
    Use this checklist before submitting a new component to ensure quality and consistency.

=== "Design & Architecture"

    - [ ] Follows Vetra design principles
    - [ ] Uses semantic naming
    - [ ] Follows existing API patterns
    - [ ] Has KDoc comments

=== "Functionality"

    - [ ] Supports light and dark modes
    - [ ] Works on all platforms (Android, iOS, Desktop, Web)
    - [ ] Handles disabled states
    - [ ] Includes proper animations

=== "Quality Assurance"

    - [ ] Includes `@Preview` examples
    - [ ] Accessible by default
    - [ ] Has documentation page

---

!!! success "Quick Check"
    Before submitting, verify:
    
    - [ ] All checklist items are completed
    - [ ] Code follows style guide
    - [ ] Tests pass on all platforms
    - [ ] Documentation is complete

## Documentation

When adding documentation:

- Write in English
- Use clear, concise language
- Include code examples
- Add visual examples when helpful
- Follow existing documentation structure
- Use mkdocs features (tabs, admonitions)

## Next Steps

- [Pull Request Process](pull-request.md) - Submit your changes

