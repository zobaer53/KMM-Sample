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

@file:Suppress("UnstableApiUsage")

package eu.upwolf.gradle.blueprint.dependency

import eu.upwolf.gradle.blueprint.dependency.Deps.Test.AndroidX
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal interface VersionCatalogHelper {
    val project: Project

    val catalogs: VersionCatalogsExtension
        get() = project.extensions.getByType()
    val libs: VersionCatalog
        get() = catalogs.named("libs")
}

class DependencyHelper(
    override val project: Project,
) : VersionCatalogHelper, Deps {

    private fun findLibrary(name: String): String {
        return libs.findLibrary(name).get().get().toString()
    }

    override val kotlin: Deps.Kotlin
        get() = object : Deps.Kotlin {
            override val core: String
                get() = findLibrary("kotlin-core")
            override val android: String
                get() = findLibrary("kotlin-android")
        }

    override val kotlinx: Deps.KotlinX
        get() = object : Deps.KotlinX {
            override val coroutines: Deps.KotlinX.Coroutines
                get() = object : Deps.KotlinX.Coroutines {
                    override val core: String
                        get() = findLibrary("kotlinx-coroutines-core")
                    override val android: String
                        get() = findLibrary("kotlinx-coroutines-android")
                }
        }

    override val androidx: Deps.AndroidX
        get() = object : Deps.AndroidX {
            override val compose: Deps.AndroidX.Compose
                get() = object : Deps.AndroidX.Compose {
                    override val compiler: String
                        get() = findLibrary("androidx-compose-compiler")
                    override val runtime: String
                        get() = findLibrary("androidx-compose-runtime")
                    override val foundation: String
                        get() = findLibrary("androidx-compose-foundation")
                    override val ui: String
                        get() = findLibrary("androidx-compose-ui")
                    override val uiTooling: String
                        get() = findLibrary("androidx-compose-ui-uiTooling")
                    override val uiToolingPreview: String
                        get() = findLibrary("androidx-compose-ui-uiToolingPreview")
                    override val material: String
                        get() = findLibrary("androidx-compose-material")
                    override val material3: String
                        get() = findLibrary("androidx-compose-material3")
                }
            override val lifecycle: Deps.AndroidX.Lifecycle
                get() = object : Deps.AndroidX.Lifecycle {
                    override val viewmodel: String
                        get() = findLibrary("androidx-lifecycle-viewmodel")
                    override val viewmodelKtx: String
                        get() = findLibrary("androidx-lifecycle-viewmodelKtx")
                }
        }

    override val jetbrains: Deps.JetBrains
        get() = object : Deps.JetBrains {
            override val compose: Deps.JetBrains.Compose
                get() = object : Deps.JetBrains.Compose {
                    override val compiler: String
                        get() = findLibrary("jetbrains-compose-compiler")
                    override val runtime: String
                        get() = findLibrary("jetbrains-compose-runtime")
                    override val foundation: String
                        get() = findLibrary("jetbrains-compose-foundation")
                    override val material: String
                        get() = findLibrary("jetbrains-compose-material")
                    override val materialIconsExtended: String
                        get() = findLibrary("jetbrains-compose-material-icons-extended")
                    override val material3: String
                        get() = findLibrary("jetbrains-compose-material3")
                    override val animation: String
                        get() = findLibrary("jetbrains-compose-animation")
                }
        }

    override val decompose: Deps.Decompose
        get() = object : Deps.Decompose {
            override val core: String
                get() = findLibrary("decompose-core")
        }

    override val koin: Deps.Koin
        get() = object : Deps.Koin {
            override val core: String
                get() = findLibrary("koin-core")
            override val test: String
                get() = findLibrary("koin-test")
        }

    override val moko: Deps.Moko
        get() = object : Deps.Moko {
            override val resources: String
                get() = findLibrary("moko-resources")
        }

    override val test: Deps.Test
        get() = object : Deps.Test {
            override val junit: String
                get() = findLibrary("test-junit")
            override val kotlin: Deps.Test.Kotlin
                get() = object : Deps.Test.Kotlin {
                    override val core: String
                        get() = findLibrary("test-kotlin-core")
                    override val annotations: String
                        get() = findLibrary("test-kotlin-annotations")
                    override val junit: String
                        get() = findLibrary("test-kotlin-junit")
                }
            override val androidx: AndroidX
                get() = object : Deps.Test.AndroidX {
                    override val runner: String
                        get() = findLibrary("test-androidx-runner")
                }
        }
}

class VersionHelper(
    override val project: Project,
) : VersionCatalogHelper, Versions {

    private fun findVersion(name: String): String {
        return libs.findVersion(name).get().requiredVersion
    }

    override val androidX: Versions.AndroidX
        get() = object : Versions.AndroidX {
            override val compose: Versions.AndroidX.Compose
                get() = object : Versions.AndroidX.Compose {
                    override val compiler: String
                        get() = findVersion("androidx-compose-compiler")
                }
        }
}

interface Deps {
    val kotlin: Kotlin

    interface Kotlin {
        val core: String
        val android: String
    }

    val kotlinx: KotlinX

    interface KotlinX {

        val coroutines: Coroutines

        interface Coroutines {
            val core: String
            val android: String
        }
    }

    val androidx: AndroidX

    interface AndroidX {

        val compose: Compose

        interface Compose {
            val compiler: String
            val runtime: String
            val foundation: String
            val ui: String
            val uiTooling: String
            val uiToolingPreview: String
            val material: String
            val material3: String
        }

        val lifecycle: Lifecycle

        interface Lifecycle {
            val viewmodel: String
            val viewmodelKtx: String
        }
    }

    val jetbrains: JetBrains

    interface JetBrains {

        val compose: Compose

        interface Compose {
            val compiler: String
            val runtime: String
            val foundation: String
            val material: String
            val materialIconsExtended: String
            val material3: String
            val animation: String
        }
    }

    val decompose: Decompose

    interface Decompose {
        val core: String
    }

    val koin: Koin

    interface Koin {
        val core: String
        val test: String
    }

    val moko: Moko

    interface Moko {
        val resources: String
    }

    val test: Test

    interface Test {
        val junit: String

        val kotlin: Kotlin

        val androidx: AndroidX

        interface Kotlin {
            val core: String
            val annotations: String
            val junit: String
        }

        interface AndroidX {
            val runner: String
        }
    }
}

interface Versions {

    val androidX: AndroidX

    interface AndroidX {

        val compose: Compose

        interface Compose {
            val compiler: String
        }
    }
}
