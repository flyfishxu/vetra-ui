# Using Components

How to use Vetra UI components in your app.

## Import Components

Import components and start building:

```kotlin
import com.flyfishxu.vetraui.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        VetraButton(onClick = { /* Handle click */ }) {
            Text("Click Me")
        }
        
        VetraCard {
            Text(
                text = "This is a card",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
```

## Component Variants

Most components have multiple variants:

### Buttons

```kotlin
VetraButton(onClick = { }) { Text("Primary") }
VetraSecondaryButton(onClick = { }) { Text("Secondary") }
VetraOutlinedButton(onClick = { }) { Text("Outlined") }
VetraGhostButton(onClick = { }) { Text("Ghost") }
VetraDangerButton(onClick = { }) { Text("Danger") }
```

### Cards

```kotlin
VetraCard { /* Standard with shadow */ }
VetraFlatCard { /* No elevation */ }
VetraElevatedCard { /* Higher elevation */ }
VetraOutlinedCard { /* Border only */ }
VetraBrandCard { /* Subtle brand color */ }
```

### Icon Buttons

```kotlin
VetraIconButton(onClick = { }) { Icon(...) }
VetraFilledIconButton(onClick = { }) { Icon(...) }
VetraOutlinedIconButton(onClick = { }) { Icon(...) }
```

## Complete Example

Here's a complete example showing common components:

```kotlin
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.*
import com.flyfishxu.vetraui.core.theme.VetraTheme

@Composable
fun App() {
    VetraTheme(darkMode = false) {
        WelcomeScreen()
    }
}

@Composable
fun WelcomeScreen() {
    var userName by remember { mutableStateOf("") }
    var agreeToTerms by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VetraTheme.colors.canvas)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Welcome to Vetra UI",
            style = VetraTheme.typography.displayMd,
            color = VetraTheme.colors.textPrimary
        )
        
        Text(
            text = "Create beautiful apps with ease",
            style = VetraTheme.typography.bodyLg,
            color = VetraTheme.colors.textSecondary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Card with input
        VetraCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Get Started",
                    style = VetraTheme.typography.headingMd,
                    color = VetraTheme.colors.textPrimary
                )
                
                VetraTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    placeholder = "Enter your name",
                    modifier = Modifier.fillMaxWidth()
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    VetraCheckbox(
                        checked = agreeToTerms,
                        onCheckedChange = { agreeToTerms = it }
                    )
                    Text(
                        text = "I agree to the terms and conditions",
                        style = VetraTheme.typography.bodyMd,
                        color = VetraTheme.colors.textSecondary
                    )
                }
            }
        }
        
        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VetraOutlinedButton(
                onClick = { /* Cancel action */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancel")
            }
            
            VetraButton(
                onClick = { /* Submit action */ },
                enabled = userName.isNotEmpty() && agreeToTerms,
                modifier = Modifier.weight(1f)
            ) {
                Text("Continue")
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Footer chips
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            VetraChip(
                label = "Android",
                onClick = { }
            )
            VetraChip(
                label = "iOS",
                onClick = { }
            )
            VetraChip(
                label = "Desktop",
                onClick = { }
            )
        }
    }
}
```

## Best Practices

### ✅ Do

- Use `VetraTheme` at the root of your app
- Access theme values through `VetraTheme.colors`, `VetraTheme.typography`, etc.
- Use semantic color names for clarity
- Test your UI in both light and dark modes
- Follow the elevation hierarchy for shadows
- Use appropriate button variants for visual hierarchy

### ❌ Don't

- Mix Vetra UI and Material Design components
- Use arbitrary colors outside the theme system
- Create custom shadow values
- Nest multiple `VetraTheme` composables
- Ignore disabled states
- Skip accessibility considerations

## Next Steps

- [Theming](theming.md) - Customize your theme
- [Common Patterns](patterns.md) - Reusable patterns
- [Component Documentation](../../components/overview.md) - Full component reference

