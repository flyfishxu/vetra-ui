# Components Overview

Vetra UI provides a complete set of modern, elegant components for building beautiful interfaces.

## Component Categories

### Input Components

Components for user input and interaction:

| Component | Description | Key Features |
|-----------|-------------|--------------|
| **[Buttons](input/buttons.md)** | Trigger actions | 5 variants, loading states, icons |
| **[Icon Button](input/icon-button.md)** | Icon-only actions | 3 variants, circular |
| **[Segmented Button](input/segmented-button.md)** | Mutually exclusive selection | Smooth animations, 2-5 segments |
| **[Text Fields](input/text-fields.md)** | Text input | Floating labels, animated underline |
| **[Switch](input/switch.md)** | Toggle states | Smooth animations, accessible |
| **[Checkbox](input/checkbox.md)** | Multiple selection | Clean design, indeterminate state |
| **[Radio Button](input/radio-button.md)** | Single selection | Group management |
| **[Slider](input/slider.md)** | Value selection | Range support, custom steps |

### Display Components

Components for showing content:

| Component | Description | Key Features |
|-----------|-------------|--------------|
| **[Cards](display/cards.md)** | Content containers | 5 variants, clickable |
| **[Chips](display/chips.md)** | Compact tags | Dismissible, icons |
| **[Badges](display/badges.md)** | Status indicators | Count badges, dot badges |
| **[Divider](display/divider.md)** | Visual separation | Horizontal, vertical |

### Navigation Components

Components for app navigation:

| Component | Description | Key Features |
|-----------|-------------|--------------|
| **[Top App Bar](navigation/top-app-bar.md)** | Screen headers | 3 variants, scrolling effects |
| **[Navigation Bar](navigation/navigation-bar.md)** | Bottom navigation | Icon + label, animations |
| **[Tabs](navigation/tabs.md)** | Content switching | Smooth indicator, scrollable |

### Feedback Components

Components for user feedback:

| Component | Description | Key Features |
|-----------|-------------|--------------|
| **[Dialogs](feedback/dialog.md)** | Modal messages | Customizable, accessible |
| **[Notifications](feedback/notify.md)** | Toast messages | Auto-dismiss, actions |
| **[Loading](feedback/loading.md)** | Progress indicators | Circular, linear, skeleton |
| **[Skeleton](feedback/skeleton.md)** | Content placeholders | Shimmer animation |
| **[Pull to Refresh](feedback/pull-to-refresh.md)** | Content refresh | Native feel, customizable |

### Action Components

Components for specific actions:

| Component | Description | Key Features |
|-----------|-------------|--------------|
| **[Menu](action/menu.md)** | Contextual options | Dropdown, animations |

## Design Principles

All Vetra components follow these principles:

### 🎨 Consistent Design

- Unified visual language across all components
- Semantic naming that makes sense
- Cohesive spacing and sizing

### ✨ Smooth Animations

- Natural, physics-based motion
- Purposeful transitions (200-400ms)
- Spring physics for playful interactions

### ♿ Accessible by Default

- WCAG AA contrast ratios
- Minimum 44×44dp touch targets
- Semantic roles for screen readers
- Keyboard navigation support

### 🔧 Easy to Customize

- Consistent API across components
- Modifier support for flexibility
- Theme-aware styling
- Composable architecture

## Usage Pattern

All components share a similar usage pattern:

```kotlin
import com.flyfishxu.vetraui.core.*

@Composable
fun MyScreen() {
    VetraTheme {  // (1)!
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Use components
            VetraButton(onClick = { /* */ }) {
                Text("Action")
            }
            
            VetraCard {
                Text("Content")
            }
            
            VetraTextField(
                value = text,
                onValueChange = { text = it },
                label = "Input"
            )
        }
    }
}
```

1. Wrap your app with VetraTheme to enable theming

## Component Selection Guide

### Choosing the Right Component

=== "For Actions"

    - **Primary action**: `VetraButton`
    - **Secondary action**: `VetraSecondaryButton` or `VetraOutlinedButton`
    - **Minimal action**: `VetraGhostButton`
    - **Icon-only action**: `VetraIconButton`
    - **Destructive action**: `VetraDangerButton`
    - **Mutually exclusive selection**: `VetraSegmentedButton`

=== "For Input"

    - **Text entry**: `VetraTextField`
    - **On/off toggle**: `VetraSwitch`
    - **Multi-select**: `VetraCheckbox`
    - **Single select (list)**: `VetraRadioButton`
    - **Single select (inline)**: `VetraSegmentedButton`
    - **Value range**: `VetraSlider`

=== "For Display"

    - **Grouping content**: `VetraCard`
    - **Compact labels**: `VetraChip`
    - **Status/counts**: `VetraBadge`
    - **Visual separation**: `VetraDivider`

=== "For Navigation"

    - **Screen title**: `VetraTopAppBar`
    - **Bottom tabs**: `VetraNavigationBar`
    - **Content tabs**: `VetraTab`

=== "For Feedback"

    - **Alert/confirm**: `VetraDialog`
    - **Brief message**: `VetraNotify`
    - **Loading state**: `VetraLoadingIndicator`
    - **Content loading**: `VetraSkeleton`

## Best Practices

!!! success "Do"
    - ✅ Use semantic component names
    - ✅ Follow the visual hierarchy (primary → secondary → tertiary)
    - ✅ Test in both light and dark modes
    - ✅ Ensure adequate spacing between components
    - ✅ Provide feedback for all user actions
    - ✅ Consider accessibility from the start

!!! failure "Don't"
    - ❌ Mix Material and Vetra components
    - ❌ Override component styles excessively
    - ❌ Use too many variants on one screen
    - ❌ Ignore component disabled states
    - ❌ Skip loading and error states
    - ❌ Forget to test on different screen sizes

## Next Steps

- Browse individual component documentation for detailed usage
- Check out the [Design System](../design-system/overview.md) to understand theming
- See [Quick Start](../introduction/quick-start/overview.md) for complete examples

---

**Ready to build?** Choose a component category above or start with [Buttons →](input/buttons.md)

