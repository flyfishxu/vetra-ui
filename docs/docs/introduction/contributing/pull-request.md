# Pull Request Process

How to submit your contributions to Vetra UI.

## 1. Create a Branch

```bash
git checkout -b feature/your-feature-name
# or
git checkout -b fix/bug-description
```

## 2. Make Changes

- Follow coding standards
- Write clear commit messages
- Add/update documentation
- Include previews

## 3. Test Thoroughly

- Run linter: `./gradlew lint`
- Build project: `./gradlew build`
- Test on target platforms
- Check both themes

## 4. Submit PR

- Push your branch
- Open a Pull Request
- Fill out the PR template
- Link related issues

## PR Requirements

!!! success "Good PR"
    - ✅ Clear description of changes
    - ✅ Screenshots/videos for UI changes
    - ✅ Tests passing
    - ✅ Documentation updated
    - ✅ Code follows style guide
    - ✅ Commit messages are clear

!!! failure "Needs Work"
    - ❌ No description
    - ❌ Failing tests
    - ❌ Missing documentation
    - ❌ Style violations
    - ❌ Unclear purpose

## Commit Messages

Write clear, descriptive commit messages:

```
feat: Add new VetraBadge component

- Add standard badge variant
- Add outlined badge variant
- Add dot badge variant
- Include previews for all variants
- Update documentation
```

## Review Process

1. Your PR will be reviewed by maintainers
2. Address any feedback
3. Once approved, your PR will be merged

## Questions?

- Open a [Discussion](https://github.com/flyfishxu/vetra-ui/discussions)
- Ask in [Issues](https://github.com/flyfishxu/vetra-ui/issues)

---

Thank you for contributing! 🎉

