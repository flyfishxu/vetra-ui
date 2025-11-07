# Quick Start Overview

Build your first Vetra UI application in minutes.

## Quick Links

- **[Setup](setup.md)** - Basic theme setup and configuration
- **[Using Components](components.md)** - How to use Vetra UI components
- **[Theming](theming.md)** - Customizing colors, typography, and more
- **[Common Patterns](patterns.md)** - Reusable patterns and examples

## Getting Started

1. [Apply the theme](setup.md#apply-the-theme)
2. [Start using components](components.md)
3. [Customize your theme](theming.md)
4. [Explore patterns](patterns.md)

## Complete Example

Here's a quick example to get you started:

```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.flyfishxu.vetraui.core.*
import com.flyfishxu.vetraui.core.theme.VetraTheme

@Composable
fun App() {
    VetraTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            VetraButton(onClick = { }) {
                Text("Click Me")
            }
            
            VetraCard {
                Text("Hello Vetra UI", modifier = Modifier.padding(16.dp))
            }
        }
    }
}
```

## Next Steps

- **[Setup Guide](setup.md)** - Detailed setup instructions
- **[Component Documentation](../../components/overview.md)** - Full component reference

