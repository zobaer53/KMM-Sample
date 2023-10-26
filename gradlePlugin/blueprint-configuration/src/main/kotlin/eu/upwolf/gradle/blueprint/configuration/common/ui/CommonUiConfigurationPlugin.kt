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

package eu.upwolf.gradle.blueprint.configuration.common.ui

import eu.upwolf.gradle.blueprint.configuration.androidLibrary
import eu.upwolf.gradle.blueprint.configuration.fixAndroidSourceSets
import eu.upwolf.gradle.blueprint.configuration.kotlin
import eu.upwolf.gradle.blueprint.configuration.setupKotlinCompatibility
import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import eu.upwolf.gradle.blueprint.dependency.VersionHelper
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.compose.ComposePlugin

@Suppress("UnstableApiUsage")
class CommonUiConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        target.pluginManager.apply("eu.upwolf.gradle.blueprint.configuration.android.library")
        target.pluginManager.apply("org.jetbrains.compose")

        target.repositories {
            mavenCentral()
            google()
            maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }

        setupTargets(target)

        target.setupKotlinCompatibility(
            listOf(
                "-opt-in=kotlin.RequiresOptIn",
            ),
        )
    }

    private fun setupTargets(project: Project) {
        setupCommonTarget(project)
        setupAndroidTarget(project)
        setupDesktopTarget(project)
    }

    private fun setupCommonTarget(project: Project) {
        val libs = DependencyHelper(project)

        project.kotlin {
            sourceSets {
                maybeCreate("commonMain").dependencies {
                    api(libs.jetbrains.compose.runtime)
                    api(libs.jetbrains.compose.foundation)
                    api(libs.jetbrains.compose.material)
                    api(libs.jetbrains.compose.material3)
                    api(libs.jetbrains.compose.animation)
                }

                maybeCreate("commonTest").dependencies {
                    implementation(libs.test.kotlin.core)
                    implementation(libs.test.kotlin.annotations)
                }
            }
        }
    }

    private fun setupAndroidTarget(project: Project) {
        val versionHelper = VersionHelper(project)
        val libs = DependencyHelper(project)

        project.kotlin {
            android {
                publishLibraryVariants("release")
            }

            sourceSets {
                maybeCreate("androidMain").dependencies {
                    implementation(libs.kotlin.android)
                    implementation(libs.kotlinx.coroutines.android)
                    implementation(libs.androidx.compose.compiler)
                    implementation(libs.androidx.compose.runtime)
                    implementation(libs.androidx.compose.foundation)
                    implementation(libs.androidx.compose.ui)
                    implementation(libs.androidx.compose.material)
                    implementation(libs.androidx.compose.material3)
                    implementation(libs.androidx.compose.uiToolingPreview)
                }
                val androidTest = maybeCreate("androidUnitTest")
                androidTest.dependencies {
                    implementation(libs.test.kotlin.junit)
                    implementation(libs.test.junit)
                }
                fixAndroidSourceSets(androidTest)
            }
        }

        project.androidLibrary {
            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = versionHelper.androidX.compose.compiler
            }
        }
    }

    private fun setupDesktopTarget(project: Project) {
        project.kotlin {
            jvm("desktop") {
                compilations.all {
                    kotlinOptions.jvmTarget = "11"
                }
            }

            sourceSets {
                maybeCreate("desktopMain").dependencies {
                    implementation(ComposePlugin.DesktopDependencies.currentOs)
                }
                maybeCreate("desktopTest").dependencies {
                    // nothing to add
                }
            }
        }
    }
}
