# Setup

Basic setup and configuration for Vetra UI.

## Apply the Theme

Wrap your app content with `VetraTheme`:

```kotlin
import androidx.compose.runtime.Composable
import com.flyfishxu.vetraui.core.theme.VetraTheme

@Composable
fun App() {
    VetraTheme(darkMode = false) {
        // Your app content here
        MyScreen()
    }
}
```

The theme provides access to:
- Colors
- Typography
- Shapes
- Shadows

## Accessing Theme Values

Access theme values anywhere inside `VetraTheme`:

```kotlin
@Composable
fun CustomComponent() {
    // Access colors
    val brandColor = VetraTheme.colors.brand
    val backgroundColor = VetraTheme.colors.canvas
    
    // Access typography
    val headingStyle = VetraTheme.typography.headingLg
    val bodyStyle = VetraTheme.typography.bodyMd
    
    // Access shapes
    val cardShape = VetraTheme.shapes.md
    val buttonShape = VetraTheme.shapes.sm
    
    // Access shadows
    val cardShadow = VetraTheme.shadows.sm
    val dialogShadow = VetraTheme.shadows.xl
    
    // Use them
    Box(
        modifier = Modifier
            .background(backgroundColor, cardShape)
            .vetraShadow(elevation = cardShadow, shape = cardShape)
            .padding(16.dp)
    ) {
        Text(
            text = "Custom Component",
            style = headingStyle.copy(color = brandColor)
        )
    }
}
```

## Dark Mode

Enable dark mode by passing `darkMode = true`:

```kotlin
@Composable
fun App() {
    // You can control this with a state variable
    var isDarkMode by remember { mutableStateOf(false) }
    
    VetraTheme(darkMode = isDarkMode) {
        Column {
            VetraSwitch(
                checked = isDarkMode,
                onCheckedChange = { isDarkMode = it }
            )
            Text("Dark Mode")
            
            // Your app content
            MyScreen()
        }
    }
}
```

The theme automatically switches color schemes and adjusts shadows for optimal visibility in both modes.

## Preview Components

All components include preview functions for quick development:

```kotlin
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun MyScreenPreview() {
    VetraTheme {
        MyScreen()
    }
}

@Preview
@Composable
private fun MyScreenDarkPreview() {
    VetraTheme(darkMode = true) {
        MyScreen()
    }
}
```

## Next Steps

- [Using Components](components.md) - Learn how to use Vetra UI components
- [Theming](theming.md) - Customize your theme

