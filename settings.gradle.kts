/*
 * ISC License
 *
 * Copyright (c) 2022. Wolf-Martell Montwé (bitfunk)
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
 * LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
 * OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

rootProject.name = "BlueprintMobileKmp"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    includeBuild("gradlePlugin/blueprint-dependency")
    includeBuild("gradlePlugin/blueprint-configuration")
    includeBuild("gradlePlugin/blueprint-tools")
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Docs
include("docs")

// App
include(
    ":app-android",
    ":app-desktop",
    // ":app-ios",
    // ":app-web",
)

// Common
include(
    ":common:ui:foundation",
    ":common:ui:system",
    ":common:ui:theme",
    ":common:database",
)

// Feature
include(
    ":feature:home:component",
    ":feature:home:ui",
    ":feature:root:component",
    ":feature:root:resources",
    ":feature:root:ui",
    ":feature:splash:component",
    ":feature:splash:resources",
    ":feature:splash:ui",
)
