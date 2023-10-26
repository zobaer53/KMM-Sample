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

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `kotlin-dsl`
    `java-gradle-plugin`

    alias(libs.plugins.gradleBlueprintDependency)
}

// To make it available as direct dependency
group = "eu.upwolf.gradle.blueprint.configuration"
version = "1.0.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

gradlePlugin {
    // Apps
    plugins.register("eu.upwolf.gradle.blueprint.configuration.app.android") {
        id = "eu.upwolf.gradle.blueprint.configuration.app.android"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.app.android.AndroidAppConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.app.desktop") {
        id = "eu.upwolf.gradle.blueprint.configuration.app.desktop"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.app.desktop.DesktopAppConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.app.ios") {
        id = "eu.upwolf.gradle.blueprint.configuration.app.ios"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.app.ios.IosAppConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.app.web") {
        id = "eu.upwolf.gradle.blueprint.configuration.app.web"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.app.web.WebAppConfigurationPlugin"
    }

    // Common
    plugins.register("eu.upwolf.gradle.blueprint.configuration.common.ui") {
        id = "eu.upwolf.gradle.blueprint.configuration.common.ui"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.common.ui.CommonUiConfigurationPlugin"
    }

    // Feature
    plugins.register("eu.upwolf.gradle.blueprint.configuration.feature.resource") {
        id = "eu.upwolf.gradle.blueprint.configuration.feature.resource"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.feature.resource.FeatureResourceConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.feature.ui") {
        id = "eu.upwolf.gradle.blueprint.configuration.feature.ui"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.feature.ui.FeatureUiConfigurationPlugin"
    }

    // OLD
    // Android

    plugins.register("eu.upwolf.gradle.blueprint.configuration.android.library") {
        id = "eu.upwolf.gradle.blueprint.configuration.android.library"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.android.library.AndroidLibraryConfigurationPlugin"
    }

    // KMP - Common
    plugins.register("eu.upwolf.gradle.blueprint.configuration.kmp.common") {
        id = "eu.upwolf.gradle.blueprint.configuration.kmp.common"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.kmp.common.CommonConfigurationPlugin"
    }
    // KMP - Feature
    plugins.register("eu.upwolf.gradle.blueprint.configuration.kmp.feature") {
        id = "eu.upwolf.gradle.blueprint.configuration.kmp.feature"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.kmp.feature.FeatureConfigurationPlugin"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "11"

        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
        )
    }
}

dependencies {
    implementation(libs.gradleBlueprintDependencyPlugin)

    implementation(libs.gradleAndroidPlugin)

    implementation(libs.gradleKotlinPlugin)
    implementation(libs.gradleKotlinSerializationPlugin)

    implementation(libs.gradleJetbrainsComposePlugin)

    implementation(libs.gradleJetbrainsApplePlugin)
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "8.0.1"
    distributionType = Wrapper.DistributionType.ALL
}
