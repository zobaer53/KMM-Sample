<!--local-files-->

[changelog]: CHANGELOG.md
[code of conduct]: CODE_OF_CONDUCT.md
[contributing]: CONTRIBUTING.md
[license]: LICENSE

<!--readme-start-->
<!--local-links-overwrite-->

[changelog]: docs/src/changelog.md
[code of conduct]: docs/src/develop/codeOfConduct.md
[contributing]: docs/src/develop/contributing.md
[license]: docs/src/license.md

<!--docs-links-->

[plugins]: docs/src/plugins/index.md
[contributing]: docs/src/develop/contributing.md
[releasing]: docs/src/develop/releasing.md

<!--github-links-->

[webpage]: https://bitfunk.github.io/blueprint-mobile-kmp/
[repository]: https://github.com/bitfunk/blueprint-mobile-kmp
[issues]: https://github.com/bitfunk/blueprint-mobile-kmp/issues
[releases]: https://github.com/bitfunk/blueprint-mobile-kmp/releases

![Logo](docs/src/assets/images/logo.png)

# Blueprint for Mobile KMP Projects

A blueprint to kickstart Mobile KMP projects.

[![Latest release](docs/src/assets/images/badge-release-latest.svg)][releases]
[![CI - Build Snapshot Version](https://github.com/bitfunk/blueprint-mobile-kmp/actions/workflows/ci-build-snapshot-version.yml/badge.svg)](https://github.com/bitfunk/blueprint-mobile-kmp/actions/workflows/ci-build-snapshot-version.yml)
[![License](docs/src/assets/images/badge-license.svg)](LICENSE)

[![Quality](https://sonarcloud.io/api/project_badges/measure?project=bitfunk_blueprint-mobile-kmp&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=bitfunk_blueprint-mobile-kmp)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bitfunk_blueprint-mobile-kmp&metric=coverage)](https://sonarcloud.io/summary/new_code?id=bitfunk_blueprint-mobile-kmp)
[![Tech debt](https://sonarcloud.io/api/project_badges/measure?project=bitfunk_blueprint-mobile-kmp&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=bitfunk_blueprint-mobile-kmp)

## About the project

This is a blueprint for Mobile KMP projects to start right away reducing initial setup cost.

### Warning

This project is work in progress. It contains several experiements and will change heavily over time.

It is currently undergoing architectural and organisational changes that focus on renewal of the current approach and removal of experiments.

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

If you like to use badges, have a look at [badges how to](docs/src/develop/badges.md).

## Roadmap

This project is work in progress. We are working on adding more functionality, guidelines,
documentation and other improvements.

See the open [issues] for a list of proposed improvements and known issues.

## Changelog

All notable changes to this project will be documented in the [changelog].

## Versioning

We use [Semantic Versioning](http://semver.org/) as a guideline for our versioning.

## Contributing

You want to help or share a proposal? You have a specific problem? [Report a bug][issues] or [request a feature][issues].

You want to fix or change code? Read the [Code of Conduct] and [contributing guide][contributing].

## Releasing

See [releasing].

## Copyright and license

Copyright (c) 2019-2022 Wolf-Martell Montwé.

Please refer to the [ISC License][license] for more information.

## Acknowledgements

- [Best README Template](https://github.com/othneildrew/Best-README-Template)
- [Issue and Pull Request Template](https://www.talater.com/open-source-templates/#/)
- [Reaktive - Project configuration with Gradle Plugins](https://github.com/badoo/Reaktive)
- [Jetpack Compose Theme by Jetsnack](https://github.com/android/compose-samples/tree/main/Jetsnack)

<!--readme-end-->
