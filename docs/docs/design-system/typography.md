# Typography

Vetra UI's typography system is designed for clarity, readability, and visual hierarchy. It uses a harmonious scale based on the major second ratio (1.125×) with carefully tuned line heights and letter spacing.

## Type Scale

The type scale progresses naturally:
- **11sp, 12sp, 14sp, 16sp, 18sp, 20sp, 24sp, 28sp, 32sp, 40sp, 48sp**

## Text Styles

### Display Styles

Large, bold styles for major headings and hero sections.

#### Display XL - 48sp
```kotlin
Text(
    text = "Hero Headline",
    style = VetraTheme.typography.displayXl
)
```
- **Size**: 48sp
- **Weight**: Bold (700)
- **Line Height**: 56sp
- **Letter Spacing**: -0.5sp
- **Usage**: Hero sections, landing page headers

#### Display LG - 40sp
```kotlin
Text(
    text = "Page Title",
    style = VetraTheme.typography.displayLg
)
```
- **Size**: 40sp
- **Weight**: Bold (700)
- **Line Height**: 48sp
- **Letter Spacing**: -0.5sp
- **Usage**: Main page titles, major sections

#### Display MD - 32sp
```kotlin
Text(
    text = "Section Header",
    style = VetraTheme.typography.displayMd
)
```
- **Size**: 32sp
- **Weight**: Bold (700)
- **Line Height**: 40sp
- **Letter Spacing**: -0.25sp
- **Usage**: Prominent section headings

#### Display SM - 28sp
```kotlin
Text(
    text = "Subsection",
    style = VetraTheme.typography.displaySm
)
```
- **Size**: 28sp
- **Weight**: Bold (700)
- **Line Height**: 36sp
- **Letter Spacing**: 0sp
- **Usage**: Section headings, category titles

### Heading Styles

Medium-weight styles for subsections and component titles.

#### Heading XL - 24sp
```kotlin
Text(
    text = "Card Title",
    style = VetraTheme.typography.headingXl
)
```
- **Size**: 24sp
- **Weight**: SemiBold (600)
- **Line Height**: 32sp
- **Letter Spacing**: 0sp
- **Usage**: Card titles, dialog headers

#### Heading LG - 20sp
```kotlin
Text(
    text = "List Header",
    style = VetraTheme.typography.headingLg
)
```
- **Size**: 20sp
- **Weight**: SemiBold (600)
- **Line Height**: 28sp
- **Letter Spacing**: 0sp
- **Usage**: List section headers, form titles

#### Heading MD - 18sp
```kotlin
Text(
    text = "Item Title",
    style = VetraTheme.typography.headingMd
)
```
- **Size**: 18sp
- **Weight**: SemiBold (600)
- **Line Height**: 26sp
- **Letter Spacing**: 0sp
- **Usage**: List item titles, sidebar headers

#### Heading SM - 16sp
```kotlin
Text(
    text = "Small Header",
    style = VetraTheme.typography.headingSm
)
```
- **Size**: 16sp
- **Weight**: SemiBold (600)
- **Line Height**: 24sp
- **Letter Spacing**: 0sp
- **Usage**: Small headings, emphasized text

### Body Styles

Regular-weight styles for content and descriptions.

#### Body LG - 16sp
```kotlin
Text(
    text = "Main paragraph text that is easy to read...",
    style = VetraTheme.typography.bodyLg
)
```
- **Size**: 16sp
- **Weight**: Normal (400)
- **Line Height**: 24sp (1.5 ratio)
- **Letter Spacing**: 0.15sp
- **Usage**: Main content, article paragraphs

#### Body MD - 14sp
```kotlin
Text(
    text = "Secondary descriptive text...",
    style = VetraTheme.typography.bodyMd
)
```
- **Size**: 14sp
- **Weight**: Normal (400)
- **Line Height**: 20sp
- **Letter Spacing**: 0.1sp
- **Usage**: Secondary content, descriptions, list items

#### Body SM - 12sp
```kotlin
Text(
    text = "Caption or helper text",
    style = VetraTheme.typography.bodySm
)
```
- **Size**: 12sp
- **Weight**: Normal (400)
- **Line Height**: 16sp
- **Letter Spacing**: 0.1sp
- **Usage**: Captions, helper text, metadata

### Label Styles

Bold styles for interactive elements and labels.

#### Label LG - 14sp
```kotlin
VetraButton(onClick = { }) {
    Text("Button Text") // Uses labelLg automatically
}
```
- **Size**: 14sp
- **Weight**: SemiBold (600)
- **Line Height**: 20sp
- **Letter Spacing**: 0.1sp
- **Usage**: Buttons, tabs, prominent labels

#### Label MD - 12sp
```kotlin
Text(
    text = "FORM LABEL",
    style = VetraTheme.typography.labelMd
)
```
- **Size**: 12sp
- **Weight**: SemiBold (600)
- **Line Height**: 16sp
- **Letter Spacing**: 0.5sp
- **Usage**: Form labels, tags, chips

#### Label SM - 11sp
```kotlin
VetraBadge(text = "NEW") // Uses labelSm
```
- **Size**: 11sp
- **Weight**: SemiBold (600)
- **Line Height**: 16sp
- **Letter Spacing**: 0.5sp
- **Usage**: Badges, small labels, counts

## Complete Example

```kotlin
@Composable
fun TypographyShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VetraTheme.colors.canvas)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Display
        Text(
            text = "Display XL",
            style = VetraTheme.typography.displayXl,
            color = VetraTheme.colors.textPrimary
        )
        
        Text(
            text = "Display MD",
            style = VetraTheme.typography.displayMd,
            color = VetraTheme.colors.textPrimary
        )
        
        Divider(color = VetraTheme.colors.borderSubtle)
        
        // Heading
        Text(
            text = "Heading LG",
            style = VetraTheme.typography.headingLg,
            color = VetraTheme.colors.textPrimary
        )
        
        Text(
            text = "Heading MD",
            style = VetraTheme.typography.headingMd,
            color = VetraTheme.colors.textPrimary
        )
        
        // Body
        Text(
            text = "Body LG - Main paragraph text with comfortable " +
                  "line height for easy reading.",
            style = VetraTheme.typography.bodyLg,
            color = VetraTheme.colors.textPrimary
        )
        
        Text(
            text = "Body MD - Secondary content and descriptions.",
            style = VetraTheme.typography.bodyMd,
            color = VetraTheme.colors.textSecondary
        )
        
        Text(
            text = "Body SM - Captions and helper text.",
            style = VetraTheme.typography.bodySm,
            color = VetraTheme.colors.textTertiary
        )
        
        // Labels
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            VetraButton(onClick = { }) {
                Text("Label LG")
            }
            VetraChip(text = "Label MD")
            VetraBadge(text = "SM")
        }
    }
}
```

## Customization

### Modifying Styles

```kotlin
Text(
    text = "Custom Style",
    style = VetraTheme.typography.bodyLg.copy(
        color = VetraTheme.colors.brand,
        fontWeight = FontWeight.Bold
    )
)
```

### Custom Typography Theme

```kotlin
val CustomTypography = DefaultVetraTypography.copy(
    displayXl = TextStyle(
        fontSize = 56.sp,
        fontWeight = FontWeight.Black,
        lineHeight = 64.sp
    ),
    bodyLg = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp
    )
)

VetraTheme(typography = CustomTypography) {
    // Your app with custom typography
}
```

### Custom Font Family

```kotlin
@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppWithCustomFont() {
    val customFont = FontFamily(
        Font(resource = "fonts/custom_regular.ttf", weight = FontWeight.Normal),
        Font(resource = "fonts/custom_bold.ttf", weight = FontWeight.Bold)
    )
    
    val customTypography = DefaultVetraTypography.copy(
        displayXl = DefaultVetraTypography.displayXl.copy(fontFamily = customFont),
        headingLg = DefaultVetraTypography.headingLg.copy(fontFamily = customFont),
        bodyLg = DefaultVetraTypography.bodyLg.copy(fontFamily = customFont)
        // ... apply to all styles
    )
    
    VetraTheme(typography = customTypography) {
        // Your app
    }
}
```

## Usage Guidelines

### Hierarchy

Use typography to create clear visual hierarchy:

```kotlin
Column {
    // Primary emphasis
    Text(
        text = "Important Title",
        style = VetraTheme.typography.displayMd,
        color = VetraTheme.colors.textPrimary
    )
    
    // Secondary emphasis
    Text(
        text = "Supporting subtitle",
        style = VetraTheme.typography.headingLg,
        color = VetraTheme.colors.textSecondary
    )
    
    // Content
    Text(
        text = "Body content that provides details...",
        style = VetraTheme.typography.bodyMd,
        color = VetraTheme.colors.textSecondary
    )
    
    // Metadata
    Text(
        text = "Additional information",
        style = VetraTheme.typography.bodySm,
        color = VetraTheme.colors.textTertiary
    )
}
```

### Responsive Typography

Adjust typography based on screen size:

```kotlin
@Composable
fun ResponsiveHeading(text: String) {
    val configuration = LocalConfiguration.current
    val isLargeScreen = configuration.screenWidthDp >= 600
    
    Text(
        text = text,
        style = if (isLargeScreen) {
            VetraTheme.typography.displayLg
        } else {
            VetraTheme.typography.displayMd
        },
        color = VetraTheme.colors.textPrimary
    )
}
```

### Multiline Text

```kotlin
Text(
    text = "Long paragraph text that wraps to multiple lines...",
    style = VetraTheme.typography.bodyLg,
    color = VetraTheme.colors.textPrimary,
    maxLines = 3,
    overflow = TextOverflow.Ellipsis
)
```

## Accessibility

### Minimum Sizes

For accessibility, follow these minimum sizes:
- **Body text**: 14sp minimum (bodyMd)
- **Interactive labels**: 14sp minimum (labelLg)
- **Small text**: 11sp minimum (labelSm)

### Line Height

Vetra UI provides generous line heights for readability:
- **Body text**: 1.4-1.5 ratio
- **Headings**: 1.2-1.3 ratio
- **Display**: 1.15-1.2 ratio

### Contrast

Pair typography with appropriate text colors:

```kotlin
// ✅ Good contrast
Text(
    text = "Readable text",
    style = VetraTheme.typography.bodyLg,
    color = VetraTheme.colors.textPrimary // High contrast
)

// ❌ Poor contrast
Text(
    text = "Hard to read",
    style = VetraTheme.typography.bodyLg,
    color = VetraTheme.colors.textTertiary.copy(alpha = 0.3f) // Too low contrast
)
```

## Best Practices

### ✅ Do

- Use display styles for major headings
- Use heading styles for section titles
- Use body styles for content
- Use label styles for interactive elements
- Maintain consistent hierarchy
- Provide adequate line height
- Test readability on different devices

### ❌ Don't

- Mix too many different sizes on one screen
- Use display styles for body text
- Create custom sizes outside the scale
- Use tiny text sizes (< 11sp)
- Ignore line height
- Use all caps for long text
- Skip color contrast testing

## Type Scale Reference

| Style | Size | Weight | Line Height | Use Case |
|-------|------|--------|-------------|----------|
| displayXl | 48sp | Bold | 56sp | Hero sections |
| displayLg | 40sp | Bold | 48sp | Page titles |
| displayMd | 32sp | Bold | 40sp | Section headers |
| displaySm | 28sp | Bold | 36sp | Subsection headers |
| headingXl | 24sp | SemiBold | 32sp | Card titles |
| headingLg | 20sp | SemiBold | 28sp | List headers |
| headingMd | 18sp | SemiBold | 26sp | Item titles |
| headingSm | 16sp | SemiBold | 24sp | Small headings |
| bodyLg | 16sp | Normal | 24sp | Main content |
| bodyMd | 14sp | Normal | 20sp | Secondary content |
| bodySm | 12sp | Normal | 16sp | Captions |
| labelLg | 14sp | SemiBold | 20sp | Buttons |
| labelMd | 12sp | SemiBold | 16sp | Tags, chips |
| labelSm | 11sp | SemiBold | 16sp | Badges |

---

**Next:** Learn about [Shapes →](shapes.md)


