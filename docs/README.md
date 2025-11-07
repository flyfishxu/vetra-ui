# Vetra UI Documentation

This module contains documentation for the Vetra UI framework, including user guides, component references, design system guidelines, and other static files.

## Module Structure

The docs module is organized as follows:

| Folder         | Description                                                                          |
|----------------|--------------------------------------------------------------------------------------|
| **docs/**      | Contains Markdown files with user documentation.                                     |
| **docs/assets/** | Contains static assets (images, CSS) for the documentation.                     |
| **mkdocs.yml** | MkDocs configuration file that defines navigation structure and theme settings.     |

## Local Development

To run the documentation website locally, you need to have [uv](https://docs.astral.sh/uv/getting-started/installation/) installed.

1. Sync the project (this will create proper .venv and install dependencies, no manual Python setup required):

   ```bash
   uv sync --frozen --all-extras
   ```

2. Start the local documentation server:

   ```bash
   uv run mkdocs serve
   ```

The documentation will be available at the URL printed in the output and will automatically reload when you make changes to the documentation files.

## Documentation System

### MkDocs

The documentation is built using [MkDocs](https://www.mkdocs.org/) with the Material theme. The configuration is defined in `mkdocs.yml`, which specifies:

- Navigation structure
- Theme configuration
- Markdown extensions
- Repository links

The documentation is available at [https://flyfishxu.github.io/vetra-ui/](https://flyfishxu.github.io/vetra-ui/).

### Build Documentation

To build the static site:

```bash
uv run mkdocs build
```

The output will be in the `site/` directory.

### Deployment

The documentation can be deployed to GitHub Pages using GitHub Actions or manually:

```bash
uv run mkdocs gh-deploy
```

## Writing Documentation

- Use Markdown format
- Follow the existing structure
- Include code examples
- Add screenshots when helpful
- Keep content clear and concise
