# Add Dependency

Add Vetra UI to your Compose Multiplatform project.

## Using Version Catalog (Recommended)

Add Vetra UI to your `libs.versions.toml`:

```toml
[versions]
vetraui = "1.0.0-alpha02"

[libraries]
vetraui-core = { module = "com.flyfishxu.vetraui:core", version.ref = "vetraui" }
```

Then in your module's `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.vetraui.core)
        }
    }
}
```

## Direct Dependency

Alternatively, add the dependency directly in `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("com.flyfishxu.vetraui:core:1.0.0-alpha02")
        }
    }
}
```

## Verify Installation

Create a simple composable to verify everything is set up correctly:

```kotlin
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.flyfishxu.vetraui.core.VetraButton
import com.flyfishxu.vetraui.core.theme.VetraTheme

@Composable
fun App() {
    VetraTheme {
        VetraButton(onClick = { println("It works!") }) {
            Text("Hello Vetra UI")
        }
    }
}
```

If you can see the button, congratulations! You've successfully installed Vetra UI. 🎉

## Version Updates

Vetra UI follows semantic versioning. Check the [GitHub releases](https://github.com/flyfishxu/vetra-ui/releases) for the latest version and changelog.

### Alpha Releases

Current versions are alpha releases. The API may change between versions. We recommend:

- Pinning to a specific version in production
- Testing thoroughly when upgrading
- Checking the changelog for breaking changes

## Next Steps

- [Platform Setup](platforms.md) - Configure platform-specific settings
- [Troubleshooting](troubleshooting.md) - If you encounter issues

