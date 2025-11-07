# Platform Setup

Platform-specific configuration for Vetra UI.

## Android

No additional setup required! Vetra UI works out of the box on Android.

### Minimum SDK Version

```kotlin
android {
    defaultConfig {
        minSdk = 21  // Android 5.0+
    }
}
```

## iOS

Vetra UI supports iOS 14.0 and above.

### Configuration

In your `iosApp` configuration:

```kotlin
ios.deploymentTarget = "14.0"
```

### CocoaPods

If using CocoaPods, make sure to run:

```bash
cd iosApp
pod install
```

## Desktop (JVM)

No additional setup required for Desktop targets.

### Configuration

```kotlin
jvm("desktop")
```

### Java Version

Java 11 or higher is recommended:

```kotlin
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}
```

## Web (JS/WASM)

Vetra UI supports both JS and WASM targets.

### JavaScript Target

```kotlin
js(IR) {
    browser()
}
```

### WebAssembly Target

```kotlin
wasmJs {
    browser()
}
```

## Next Steps

- [Troubleshooting](troubleshooting.md) - If you encounter platform-specific issues
- [Quick Start](../quick-start/setup.md) - Start building your app

