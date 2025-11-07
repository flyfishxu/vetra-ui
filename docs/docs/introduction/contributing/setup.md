# Development Setup

Get your development environment ready for contributing to Vetra UI.

## Prerequisites

- Kotlin 2.0.0+
- Compose Multiplatform 1.6.0+
- Android Studio or IntelliJ IDEA

## Clone the Repository

```bash
git clone https://github.com/flyfishxu/vetra-ui.git
cd vetra-ui
```

## Build the Project

```bash
./gradlew build
```

## Run Sample App

### Android

```bash
./gradlew :composeApp:installDebug
```

### Desktop

```bash
./gradlew :composeApp:run
```

### Web

```bash
./gradlew :composeApp:jsBrowserDevelopmentRun
```

## Project Structure

```
vetra-ui/
├── core/                    # Core library
│   └── src/commonMain/
│       └── kotlin/com/flyfishxu/vetraui/core/
│           ├── Button.kt
│           ├── Card.kt
│           └── theme/
├── composeApp/             # Sample app
└── docs/                   # Documentation
```

## Next Steps

- [Development Guidelines](guidelines.md) - Learn coding standards
- [Pull Request Process](pull-request.md) - Submit your changes

