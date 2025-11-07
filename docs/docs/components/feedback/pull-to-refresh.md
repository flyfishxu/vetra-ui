# Pull to Refresh

Pull to refresh allows users to refresh content by pulling down from the top.

## Overview

VetraPullToRefresh provides a modern pull-to-refresh component with:

- Smooth gesture recognition and animations
- Elegant loading indicator with rotation
- Customizable trigger threshold
- Support for custom indicators
- State-based architecture
- Cross-platform compatibility

```kotlin
VetraPullToRefresh(
    refreshing = isRefreshing,
    onRefresh = { refreshData() }
) {
    Content()
}
```

## Basic Usage

=== "Standard"

    ```kotlin
    VetraPullToRefresh(
        refreshing = isRefreshing,
        onRefresh = { refreshData() }
    ) {
        LazyColumn {
            items(items) { item ->
                ItemRow(item)
            }
        }
    }
    ```

=== "Custom Indicator"

    ```kotlin
    VetraPullToRefresh(
        refreshing = isRefreshing,
        onRefresh = { refreshData() },
        indicator = { state ->
            CustomIndicator(state)
        }
    ) {
        Content()
    }
    ```

=== "Custom Color"

    ```kotlin
    VetraPullToRefresh(
        refreshing = isRefreshing,
        onRefresh = { refreshData() },
        indicatorColor = VetraTheme.colors.accent
    ) {
        Content()
    }
    ```

## Common Patterns

### List Refresh

```kotlin
var isRefreshing by remember { mutableStateOf(false) }

VetraPullToRefresh(
    refreshing = isRefreshing,
    onRefresh = {
        isRefreshing = true
        viewModel.refreshData {
            isRefreshing = false
        }
    }
) {
    LazyColumn {
        items(items) { item ->
            ItemRow(item)
        }
    }
}
```

### Disabled State

```kotlin
VetraPullToRefresh(
    refreshing = false,
    onRefresh = { },
    enabled = canRefresh
) {
    Content()
}
```

## Best Practices

!!! success "Do"
    - ✅ Use pull-to-refresh for refreshable content
    - ✅ Provide clear visual feedback
    - ✅ Refresh data when triggered
    - ✅ Show loading state during refresh
    - ✅ Use appropriate trigger threshold

!!! failure "Don't"
    - ❌ Use pull-to-refresh for navigation
    - ❌ Block user interaction unnecessarily
    - ❌ Make trigger too sensitive
    - ❌ Refresh too frequently

## Accessibility

- **Screen Readers**: Proper loading announcements
- **Gesture**: Clear and discoverable
- **Visual Feedback**: Clear indication

## API Reference

### VetraPullToRefresh

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `refreshing` | `Boolean` | Required | Whether refresh is in progress |
| `onRefresh` | `() -> Unit` | Required | Callback when refresh is triggered |
| `modifier` | `Modifier` | `Modifier` | Modifier for customization |
| `enabled` | `Boolean` | `true` | Whether pull-to-refresh is enabled |
| `indicatorColor` | `Color?` | `null` | Color of the refresh indicator |
| `indicator` | `@Composable (VetraPullToRefreshState) -> Unit` | Default | Custom refresh indicator |
| `content` | `@Composable () -> Unit` | Required | Scrollable content |

### VetraPullToRefreshState

State object providing information about pull-to-refresh state.

---

**See Also:**

- [Loading](loading.md) - For loading indicators
- [Skeleton](skeleton.md) - For content placeholders

