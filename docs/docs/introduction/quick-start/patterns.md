# Common Patterns

Reusable patterns and examples for common use cases.

## Form Layout

```kotlin
Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    VetraTextField(
        value = name,
        onValueChange = { name = it },
        label = "Name"
    )
    VetraTextField(
        value = email,
        onValueChange = { email = it },
        label = "Email"
    )
    VetraButton(onClick = { submit() }) {
        Text("Submit")
    }
}
```

## Card Grid

```kotlin
LazyVerticalGrid(
    columns = GridCells.Adaptive(minSize = 150.dp),
    contentPadding = PaddingValues(16.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    items(items) { item ->
        VetraCard(onClick = { /* ... */ }) {
            // Card content
        }
    }
}
```

## Dialog

```kotlin
var showDialog by remember { mutableStateOf(false) }

VetraDialog(
    visible = showDialog,
    onDismissRequest = { showDialog = false },
    title = { Text("Confirm Action") },
    content = {
        Text("Are you sure you want to continue?")
    },
    confirmButton = {
        VetraButton(onClick = { showDialog = false }) {
            Text("Confirm")
        }
    },
    dismissButton = {
        VetraOutlinedButton(onClick = { showDialog = false }) {
            Text("Cancel")
        }
    }
)
```

## List with Dividers

```kotlin
Column {
    items.forEachIndexed { index, item ->
        ListItem(item = item)
        if (index < items.size - 1) {
            VetraInsetDivider(startPadding = 16.dp)
        }
    }
}
```

## Loading State

```kotlin
if (isLoading) {
    VetraFullScreenLoading(message = "Loading...")
} else {
    Content()
}
```

## Error State

```kotlin
if (error != null) {
    VetraCard {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "Error",
                style = VetraTheme.typography.headingSm,
                color = VetraTheme.colors.danger
            )
            Text(error.message ?: "Unknown error")
            VetraButton(onClick = { retry() }) {
                Text("Retry")
            }
        }
    }
} else {
    Content()
}
```

## Next Steps

- [Component Documentation](../../components/overview.md) - Explore all components
- [Design System](../../design-system/overview.md) - Learn design principles

