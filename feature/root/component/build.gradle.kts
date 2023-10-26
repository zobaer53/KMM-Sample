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
    alias(libs.plugins.gradleBlueprintConfigurationKmpCommon)
    id("org.jetbrains.kotlin.plugin.parcelize")
}

kotlin {
    ios {
        binaries {
            framework {
                baseName = "FeatureRoot"
                transitiveExport = true
                linkerOpts.add("-lsqlite3")
                export(projects.common.database)
                export(libs.decompose.core)
                export(libs.essenty.lifecycle)

                export(projects.feature.splash.component)
                export(projects.feature.home.component)
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.database)
                implementation(libs.decompose.core)

                implementation(projects.feature.splash.component)
                implementation(projects.feature.home.component)
            }
        }

        iosMain {
            dependencies {
                api(projects.common.database)
                implementation(libs.decompose.core)
                implementation(libs.essenty.lifecycle)

                api(projects.feature.splash.component)
                api(projects.feature.home.component)
            }
        }
    }
}

android {
    namespace = "eu.upwolf.mobile.blueprint.feature.root.component"
}
