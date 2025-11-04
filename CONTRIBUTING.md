# Contributing to Vetra UI

First off, thank you for considering contributing to Vetra UI! It's people like you that make Vetra UI such a great UI components.

## Code of Conduct

By participating in this project, you are expected to uphold our code of conduct of being respectful and constructive.

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the existing issues to avoid duplicates. When you are creating a bug report, please include as many details as possible:

- **Use a clear and descriptive title**
- **Describe the exact steps to reproduce the problem**
- **Provide specific examples to demonstrate the steps**
- **Describe the behavior you observed and what behavior you expected**
- **Include screenshots or animated GIFs if possible**
- **Specify which platform(s) you're using** (Android, iOS, Desktop)
- **Include your Kotlin and Compose Multiplatform versions**

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, please include:

- **Use a clear and descriptive title**
- **Provide a detailed description of the suggested enhancement**
- **Explain why this enhancement would be useful**
- **List some examples of where this enhancement could be used**

### Pull Requests

Please follow these steps to have your contribution considered:

1. **Fork the repo** and create your branch from `main`
2. **Follow the coding standards** described below
3. **Add `@Preview` annotations** for any new components you create
4. **Write clear documentation** including KDoc comments for public APIs
5. **Test cross-platform compatibility** on Android, iOS, and Desktop if possible
6. **Keep components independent and reusable**
7. **Update the README.md** if you add new features
8. **Make sure your code lints** without errors

## Development Guidelines

### Code Style

- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Keep functions focused and small
- Add comments for complex logic
- Use English for all code, comments, and documentation

### Component Development

- **Follow MVI architecture** or other mainstream Compose patterns
- **Design for extensibility** - think long-term
- **API design should mirror Material Design** conventions to reduce learning curve
- **Avoid overly long files** - split into multiple files when appropriate
- **Don't wrap Material components** - create components from scratch
- **Consider cross-platform compatibility** from the start

### File Organization

- Place files in appropriate packages
- Keep related functionality together
- Use clear and consistent naming conventions
- Follow the existing project structure

### Testing

- Add `@Preview` functions for visual testing
- Test components in both light and dark themes
- Verify behavior on multiple platforms when possible
- Ensure no linter errors exist

### Documentation

- Write clear KDoc comments for public APIs
- Include usage examples in documentation
- Update relevant documentation files when making changes
- Keep examples simple and focused

## Platform Support

Vetra UI supports:
- **Android** (primary platform)
- **iOS**
- **JVM Desktop**
- **Web**

When contributing, ensure your changes work on at least Android. Cross-platform testing is highly appreciated.

## Design Principles

Remember Vetra UI's core design principles:

- **Elegance over Flash** - No effects for effect's sake
- **Unity over Variety** - Consistent design language
- **Natural over Mechanical** - Motion follows physics
- **Clarity over Abstraction** - Intuitive and predictable APIs

## Questions?

Feel free to open an issue with your question or reach out to me.

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

Thank you for contributing to Vetra UI! 🎉
