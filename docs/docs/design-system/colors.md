# Color System

Vetra UI's color system uses intuitive, semantic naming that makes it easy to understand and use. Unlike Material Design's technical color roles, Vetra colors are named for their purpose, not their position in a hierarchy.

## Philosophy

- **Semantic Over Technical**: Colors are named for what they do, not abstract roles
- **Accessibility First**: All color combinations meet WCAG 2.1 AA standards
- **Mode-Aware**: Automatic adjustment between light and dark modes
- **Beautiful by Default**: Carefully chosen colors that work well together

## Color Roles

The color system is organized into semantic roles that describe how colors are used in real-world scenarios. Each color has a specific purpose and appears in predictable contexts throughout your application.

<table>
<thead>
<tr>
<th>Name</th>
<th>Preview</th>
<th>Use Case</th>
</tr>
</thead>
<tbody>
<tr><td colspan="3" class="table-section-header" style="text-align: center;"><strong>Brand Colors</strong></td></tr>
<tr>
<td><code>brand</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #2563EB;" title="Light: #2563EB"></span><span class="color-swatch-dark" style="background-color: #3B82F6;" title="Dark: #3B82F6"></span></span></td>
<td>Login page primary button, active navigation tab, "Save" action in forms</td>
</tr>
<tr>
<td><code>onBrand</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #FFFFFF;" title="Light: #FFFFFF"></span><span class="color-swatch-dark" style="background-color: #FFFFFF;" title="Dark: #FFFFFF"></span></span></td>
<td>White text on primary buttons, icons on brand-colored backgrounds</td>
</tr>
<tr>
<td><code>brandSubtle</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #EFF6FF;" title="Light: #EFF6FF"></span><span class="color-swatch-dark" style="background-color: #1E293B;" title="Dark: #1E293B"></span></span></td>
<td>Notification badge background, selected item highlight, info banner</td>
</tr>
<tr>
<td><code>onBrandSubtle</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #1E40AF;" title="Light: #1E40AF"></span><span class="color-swatch-dark" style="background-color: #93C5FD;" title="Dark: #93C5FD"></span></span></td>
<td>Text and icons on subtle brand backgrounds, badge labels</td>
</tr>
<tr><td colspan="3" class="table-section-header" style="text-align: center;"><strong>Accent Colors</strong></td></tr>
<tr>
<td><code>accent</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #7C3AED;" title="Light: #7C3AED"></span><span class="color-swatch-dark" style="background-color: #8B5CF6;" title="Dark: #8B5CF6"></span></span></td>
<td>Secondary action buttons, "Learn More" links, special feature highlights</td>
</tr>
<tr>
<td><code>onAccent</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #FFFFFF;" title="Light: #FFFFFF"></span><span class="color-swatch-dark" style="background-color: #FFFFFF;" title="Dark: #FFFFFF"></span></span></td>
<td>White text on secondary buttons, icons on accent-colored surfaces</td>
</tr>
<tr>
<td><code>accentSubtle</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #F5F3FF;" title="Light: #F5F3FF"></span><span class="color-swatch-dark" style="background-color: #2E1065;" title="Dark: #2E1065"></span></span></td>
<td>Chip backgrounds, tag highlights, subtle feature indicators</td>
</tr>
<tr>
<td><code>onAccentSubtle</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #5B21B6;" title="Light: #5B21B6"></span><span class="color-swatch-dark" style="background-color: #C4B5FD;" title="Dark: #C4B5FD"></span></span></td>
<td>Text on chips and tags, labels on accent backgrounds</td>
</tr>
<tr><td colspan="3" class="table-section-header" style="text-align: center;"><strong>Canvas Colors</strong></td></tr>
<tr>
<td><code>canvas</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #F8F8F8;" title="Light: #F8F8F8"></span><span class="color-swatch-dark" style="background-color: #0F0F0F;" title="Dark: #0F0F0F"></span></span></td>
<td>Main app background, screen root container, list view background</td>
</tr>
<tr>
<td><code>onCanvas</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #0A0A0A;" title="Light: #0A0A0A"></span><span class="color-swatch-dark" style="background-color: #FAFAFA;" title="Dark: #FAFAFA"></span></span></td>
<td>Primary headings on main background, main body text</td>
</tr>
<tr>
<td><code>canvasElevated</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #FFFFFF;" title="Light: #FFFFFF"></span><span class="color-swatch-dark" style="background-color: #1A1A1A;" title="Dark: #1A1A1A"></span></span></td>
<td>Card backgrounds, dialog surfaces, bottom sheets, floating panels</td>
</tr>
<tr>
<td><code>onCanvasElevated</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #0A0A0A;" title="Light: #0A0A0A"></span><span class="color-swatch-dark" style="background-color: #FAFAFA;" title="Dark: #FAFAFA"></span></span></td>
<td>Text content on cards, dialog titles and body text</td>
</tr>
<tr>
<td><code>canvasSubtle</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #F0F0F0;" title="Light: #F0F0F0"></span><span class="color-swatch-dark" style="background-color: #080808;" title="Dark: #080808"></span></span></td>
<td>List item backgrounds, grouped content areas, input field backgrounds</td>
</tr>
<tr>
<td><code>onCanvasSubtle</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #0A0A0A;" title="Light: #0A0A0A"></span><span class="color-swatch-dark" style="background-color: #FAFAFA;" title="Dark: #FAFAFA"></span></span></td>
<td>Text on list items, content in grouped sections</td>
</tr>
<tr><td colspan="3" class="table-section-header" style="text-align: center;"><strong>Text Colors</strong></td></tr>
<tr>
<td><code>textPrimary</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #0A0A0A;" title="Light: #0A0A0A"></span><span class="color-swatch-dark" style="background-color: #FAFAFA;" title="Dark: #FAFAFA"></span></span></td>
<td>Page titles, article headings, important labels, primary content</td>
</tr>
<tr>
<td><code>textSecondary</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #525252;" title="Light: #525252"></span><span class="color-swatch-dark" style="background-color: #A3A3A3;" title="Dark: #A3A3A3"></span></span></td>
<td>Subtitles, descriptions, metadata, supporting information</td>
</tr>
<tr>
<td><code>textTertiary</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #A3A3A3;" title="Light: #A3A3A3"></span><span class="color-swatch-dark" style="background-color: #525252;" title="Dark: #525252"></span></span></td>
<td>Placeholder text, hints, captions, less important labels</td>
</tr>
<tr>
<td><code>textDisabled</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #D4D4D4;" title="Light: #D4D4D4"></span><span class="color-swatch-dark" style="background-color: #404040;" title="Dark: #404040"></span></span></td>
<td>Disabled button labels, inactive menu items, unavailable content</td>
</tr>
<tr><td colspan="3" class="table-section-header" style="text-align: center;"><strong>Border Colors</strong></td></tr>
<tr>
<td><code>border</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #D6D6D6;" title="Light: #D6D6D6"></span><span class="color-swatch-dark" style="background-color: #2E2E2E;" title="Dark: #2E2E2E"></span></span></td>
<td>Input field borders, card outlines, divider lines, section separators</td>
</tr>
<tr>
<td><code>borderSubtle</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #E8E8E8;" title="Light: #E8E8E8"></span><span class="color-swatch-dark" style="background-color: #242424;" title="Dark: #242424"></span></span></td>
<td>Subtle dividers between list items, soft section boundaries</td>
</tr>
<tr>
<td><code>borderFocus</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #3B82F6;" title="Light: #3B82F6"></span><span class="color-swatch-dark" style="background-color: #60A5FA;" title="Dark: #60A5FA"></span></span></td>
<td>Focus rings on inputs, keyboard navigation indicators, active field outline</td>
</tr>
<tr><td colspan="3" class="table-section-header" style="text-align: center;"><strong>Semantic Colors</strong></td></tr>
<tr>
<td><code>success</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #10B981;" title="Light: #10B981"></span><span class="color-swatch-dark" style="background-color: #34D399;" title="Dark: #34D399"></span></span></td>
<td>Success notification backgrounds, checkmark icons, completion indicators</td>
</tr>
<tr>
<td><code>onSuccess</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #FFFFFF;" title="Light: #FFFFFF"></span><span class="color-swatch-dark" style="background-color: #052E16;" title="Dark: #052E16"></span></span></td>
<td>Text on success messages, icons on green backgrounds</td>
</tr>
<tr>
<td><code>warning</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #F59E0B;" title="Light: #F59E0B"></span><span class="color-swatch-dark" style="background-color: #FBBF24;" title="Dark: #FBBF24"></span></span></td>
<td>Warning alert backgrounds, caution badges, attention indicators</td>
</tr>
<tr>
<td><code>onWarning</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #FFFFFF;" title="Light: #FFFFFF"></span><span class="color-swatch-dark" style="background-color: #431407;" title="Dark: #431407"></span></span></td>
<td>Text on warning messages, icons on orange backgrounds</td>
</tr>
<tr>
<td><code>danger</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #EF4444;" title="Light: #EF4444"></span><span class="color-swatch-dark" style="background-color: #F87171;" title="Dark: #F87171"></span></span></td>
<td>Delete button backgrounds, error alert boxes, critical action indicators</td>
</tr>
<tr>
<td><code>onDanger</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #FFFFFF;" title="Light: #FFFFFF"></span><span class="color-swatch-dark" style="background-color: #450A0A;" title="Dark: #450A0A"></span></span></td>
<td>"Delete" button text, error message text, icons on red backgrounds</td>
</tr>
<tr>
<td><code>info</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #06B6D4;" title="Light: #06B6D4"></span><span class="color-swatch-dark" style="background-color: #22D3EE;" title="Dark: #22D3EE"></span></span></td>
<td>Information banners, help tooltips, informational badges</td>
</tr>
<tr>
<td><code>onInfo</code></td>
<td><span class="color-swatch-group"><span class="color-swatch-light" style="background-color: #FFFFFF;" title="Light: #FFFFFF"></span><span class="color-swatch-dark" style="background-color: #083344;" title="Dark: #083344"></span></span></td>
<td>Text on info messages, icons on cyan backgrounds</td>
</tr>
</tbody>
</table>

## Usage Examples

### Basic Usage

```kotlin
@Composable
fun MyComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VetraTheme.colors.canvas)
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome",
            style = VetraTheme.typography.displayMd,
            color = VetraTheme.colors.textPrimary
        )
        
        Text(
            text = "Get started with Vetra UI",
            style = VetraTheme.typography.bodyLg,
            color = VetraTheme.colors.textSecondary
        )
    }
}
```

### Custom Colors

Override default colors when creating your theme:

```kotlin
val CustomLightColors = VetraLightColorScheme.copy(
    brand = Color(0xFF1E40AF),      // Custom blue
    accent = Color(0xFF9333EA),      // Custom purple
    success = Color(0xFF059669),     // Custom green
)

@Composable
fun App() {
    VetraTheme(colors = CustomLightColors) {
        // Your app content
    }
}
```

### Dynamic Theme Colors

```kotlin
@Composable
fun DynamicThemedApp() {
    val brandColor = remember { mutableStateOf(Color(0xFF2563EB)) }
    
    VetraTheme(
        colors = VetraLightColorScheme.copy(
            brand = brandColor.value
        )
    ) {
        // Color picker
        ColorPicker(
            color = brandColor.value,
            onColorChange = { brandColor.value = it }
        )
        
        // Your app adapts automatically
        VetraButton(onClick = { }) {
            Text("Dynamic Color")
        }
    }
}
```

## Color Naming Comparison

### Vetra vs Material

| Vetra | Material Design |
|-------|----------------|
| `brand` | `primary` |
| `onBrand` | `onPrimary` |
| `brandSubtle` | `primaryContainer` |
| `accent` | `secondary` |
| `canvas` | `background` |
| `canvasElevated` | `surface` |
| `textPrimary` | `onSurface` |
| `textSecondary` | `onSurfaceVariant` |
| `border` | `outline` |
| `danger` | `error` |

## Accessibility

All color combinations in Vetra UI meet WCAG 2.1 AA standards:

- **Normal text**: Minimum 4.5:1 contrast ratio
- **Large text** (18sp+ or 14sp+ bold): Minimum 3:1 contrast ratio
- **UI components**: Minimum 3:1 contrast ratio

### Testing Contrast

```kotlin
// Built-in colors are already accessible
Text(
    text = "This text is accessible",
    color = VetraTheme.colors.textPrimary, // ✓ 4.5:1+ contrast
    modifier = Modifier.background(VetraTheme.colors.canvas)
)

// When using custom colors, verify contrast
Text(
    text = "Check contrast for custom colors",
    color = customColor, // ⚠️ Test this
    modifier = Modifier.background(customBackground)
)
```

## Best Practices

### ✅ Do

- Use semantic color names consistently
- Test your UI in both light and dark modes
- Maintain color hierarchies (primary → secondary → tertiary)
- Use `brandSubtle` and `accentSubtle` for backgrounds
- Leverage semantic colors for states

### ❌ Don't

- Use hardcoded hex colors
- Mix Vetra and Material color APIs
- Ignore disabled states
- Use `brand` for large background areas
- Skip contrast testing for custom colors

## Migration from Material

```kotlin
// Before (Material)
MaterialTheme.colorScheme.primary
MaterialTheme.colorScheme.onSecondaryContainer
MaterialTheme.colorScheme.surfaceVariant

// After (Vetra)
VetraTheme.colors.brand
VetraTheme.colors.onAccentSubtle
VetraTheme.colors.canvasSubtle
```

---

**Next:** Learn about [Typography →](typography.md)

