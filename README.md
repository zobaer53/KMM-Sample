

![Logo](docs/src/assets/images/logo.png)

# Blueprint for Mobile KMP Projects

A blueprint to kickstart Mobile KMP projects.

[![Quality](https://sonarcloud.io/api/project_badges/measure?project=bitfunk_blueprint-mobile-kmp&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=bitfunk_blueprint-mobile-kmp)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bitfunk_blueprint-mobile-kmp&metric=coverage)](https://sonarcloud.io/summary/new_code?id=bitfunk_blueprint-mobile-kmp)
[![Tech debt](https://sonarcloud.io/api/project_badges/measure?project=bitfunk_blueprint-mobile-kmp&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=bitfunk_blueprint-mobile-kmp)

## About the project

This is a blueprint for Mobile KMP projects to start right away reducing initial setup cost.


### Features

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform-get-started.html)
- Android App
- iOS App
- Desktop App
- Jetpack Compose & Jetbrains Compose
- Material 3
- CI Setup with GitHub Actions
- Dependency versions managed via gradlePlugin and version catalogs
- Kotlin Static Analysis via ktlint and detekt
- Issues (bug report + feature request) and Pull Request Template
- Documentation with [MkDocs](https://www.mkdocs.org/) and [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/)

## Getting Started

### Prerequisites

- Android 5.0.1 (API 21) to Android 13 (API 33)
- Kotlin 1.7.20
- Java 11
- Gradle 7.5
- Android Studio Dolphin | 2021.3.1
- Jetpack Compose Compiler 1.3.2

## Usage

Use this template for creating a new repository.

Remove README.md and CHANGELOG.md and replace them by TEMPLATE_README.md and TEMPLATE_CHANGELOG.md. Change the placeholders to your own definitions:

- PROJECT_TITLE
- PROJECT_DESCRIPTION
- PROJECT_GITHUB_LINK
- PROJECT_WEBPAGE_URL
- PROJECT_SONARCLOUD_NAME

Assets could be placed under `docs/src/assets/` folder and images should be placed in `docs/src/assets/images/`.
