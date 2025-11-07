# Troubleshooting

Common issues and solutions when installing Vetra UI.

## Compose Version Conflicts

If you encounter version conflicts with Compose, ensure all your Compose dependencies use compatible versions:

```toml
[versions]
compose = "1.9.2"
vetraui = "1.0.0-alpha02"
```

Make sure all Compose dependencies reference the same version:

```kotlin
[versions]
compose = "1.9.2"

[libraries]
compose-ui = { module = "org.jetbrains.compose.ui:ui", version.ref = "compose" }
compose-foundation = { module = "org.jetbrains.compose.foundation:foundation", version.ref = "compose" }
compose-material3 = { module = "org.jetbrains.compose.material3:material3", version.ref = "compose" }
```

## Symbol Resolution Issues

If your IDE can't resolve Vetra UI imports:

1. **Sync Gradle Project**
   - Click "Sync Now" in Android Studio
   - Or run: `./gradlew build --refresh-dependencies`

2. **Invalidate Caches**
   - File → Invalidate Caches...
   - Select "Invalidate and Restart"

3. **Check Import Path**
   - Ensure you're using: `com.flyfishxu.vetraui.core.*`
   - Or specific imports: `com.flyfishxu.vetraui.core.VetraButton`

4. **Rebuild Project**
   - Build → Rebuild Project

## Platform-Specific Issues

### iOS

**Issue**: Build fails on iOS

**Solutions**:
- Make sure you've run `pod install` in the iosApp directory
- Check that iOS deployment target is set to 14.0 or higher
- Clean build folder: `./gradlew clean`

### Desktop

**Issue**: Desktop app won't run

**Solutions**:
- Ensure you have the correct JVM target (11 or higher recommended)
- Check that Java toolchain is configured correctly
- Try running: `./gradlew :composeApp:run`

### Web

**Issue**: Web build fails or doesn't load

**Solutions**:
- Clear your browser cache
- Rebuild the project: `./gradlew clean build`
- Check browser console for errors
- Ensure you're using a modern browser

## Build Errors

### Gradle Sync Fails

1. Check your `gradle.properties` for any conflicting settings
2. Update Gradle wrapper: `./gradlew wrapper --gradle-version=8.5`
3. Check Kotlin version compatibility

### Compilation Errors

1. Ensure Kotlin version is 2.0.0 or higher
2. Check Compose Multiplatform version compatibility
3. Clean and rebuild: `./gradlew clean build`

## Still Having Issues?

- Check [GitHub Issues](https://github.com/flyfishxu/vetra-ui/issues)
- Open a [new issue](https://github.com/flyfishxu/vetra-ui/issues/new)
- Review the [changelog](https://github.com/flyfishxu/vetra-ui/releases)

## Next Steps

Once resolved:
- [Verify Installation](dependency.md#verify-installation)
- [Quick Start](../quick-start/setup.md)

