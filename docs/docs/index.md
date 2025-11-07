# Welcome to Vetra UI

<div align="center">
  <img src="https://raw.githubusercontent.com/flyfishxu/vetra-ui/main/images/vetra.svg" alt="Vetra UI Logo" width="120"/>
</div>

**A Modern, Elegant UI Design System for Compose Multiplatform**

Vetra UI is a complete design system built from the ground up for Compose Multiplatform. It brings together light, depth, and motion to create delightful user experiences across Android, iOS, Desktop, and Web platforms.

## Why Vetra UI?

### 🎨 Independent Design Language

Vetra UI is not a wrapper around Material Design. It's a complete, independent design system with:

- **Semantic Naming**: Intuitive color names like `brand`, `accent`, `canvas` instead of technical terms
- **Refined Motion**: Spring-based animations and carefully tuned easing curves
- **Adaptive Shadows**: Mode-aware shadow system that automatically adjusts for light and dark themes

### 🌍 Cross-Platform First

Built specifically for Compose Multiplatform with full support for:

- **Android** (primary platform)
- **iOS**
- **JVM Desktop**
- **Web** (JS & WASM)

### 🎯 Developer Friendly

- **Familiar API**: Design mirrors Material Design conventions for low learning curve
- **Type-Safe**: Leverages Kotlin's type system for compile-time safety
- **Comprehensive Previews**: Every component includes multiple preview examples
- **Well Documented**: Clear documentation with usage examples

## Core Principles

### Elegance Over Flash
No effects for effect's sake—every animation and visual element serves a purpose

### Unity Over Variety
Consistent design language reduces cognitive load and creates cohesive experiences

### Natural Over Mechanical
Motion follows physics, interactions follow intuition

### Clarity Over Abstraction
Intuitive naming and predictable APIs make development a joy

## What's Included

### Design System
- **Color System**: Semantic color roles with automatic dark mode support
- **Typography**: Harmonious type scale with clear hierarchy
- **Shape System**: Consistent corner radius scale
- **Shadow System**: Seven elevation levels with adaptive rendering
- **Motion**: Spring physics and refined easing curves

### Components
Over 20 production-ready components:

- **Buttons**: Primary, Secondary, Outlined, Ghost, Danger
- **Inputs**: TextField, Switch, Checkbox, RadioButton, Slider
- **Cards**: Standard, Flat, Elevated, Outlined, Brand variants
- **Navigation**: TopAppBar, NavigationBar, Tabs
- **Feedback**: Dialog, Menu, Notify, Loading, Skeleton
- **Layout**: Divider, Chip, Badge, PullToRefresh

## Quick Example

```kotlin
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.*

@Composable
fun App() {
    VetraTheme(darkMode = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(VetraTheme.colors.canvas)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Beautiful by default
            VetraButton(onClick = { /* action */ }) {
                Text("Get Started")
            }
            
            VetraCard {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Welcome to Vetra UI",
                        style = VetraTheme.typography.headingMd
                    )
                    Text(
                        "Building delightful experiences",
                        style = VetraTheme.typography.bodyMd,
                        color = VetraTheme.colors.textSecondary
                    )
                }
            }
        }
    }
}
```

## Next Steps

<div class="grid cards" markdown>

-   :material-download: **[Installation](introduction/installation/overview.md)**

    ---

    Get Vetra UI up and running in your project

-   :material-rocket-launch: **[Quick Start](introduction/quick-start/overview.md)**

    ---

    Build your first Vetra UI app in minutes

-   :material-palette: **[Design System](design-system/overview.md)**

    ---

    Learn about colors, typography, and more

-   :material-widgets: **[Components](components/overview.md)**

    ---

    Explore all available UI components

</div>

## Community

- **GitHub**: [flyfishxu/vetra-ui](https://github.com/flyfishxu/vetra-ui)
- **Issues**: [Report bugs or request features](https://github.com/flyfishxu/vetra-ui/issues)
- **Contributing**: [Read our contribution guide](introduction/contributing/overview.md)

## License

Vetra UI is [MIT licensed](https://github.com/flyfishxu/vetra-ui/blob/main/LICENSE).

---

**Ready to get started?** Head to the [Installation Guide](introduction/installation/overview.md) →

