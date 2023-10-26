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

package eu.upwolf.gradle.blueprint.configuration.app.android

import eu.upwolf.gradle.blueprint.configuration.AndroidConfig
import eu.upwolf.gradle.blueprint.configuration.androidApp
import eu.upwolf.gradle.blueprint.configuration.implementation
import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import eu.upwolf.gradle.blueprint.dependency.VersionHelper
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

@Suppress("UnstableApiUsage")
class AndroidAppConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("com.android.application")
        target.pluginManager.apply("kotlin-android")
        target.pluginManager.apply("kotlin-parcelize")

        setupAndroidApplication(target)
        setupDependencies(target)
        setupAndroidKotlinCompatibility(target)
    }

    private fun setupAndroidApplication(project: Project) {
        val versionHelper = VersionHelper(project)

        project.androidApp {
            compileSdk = AndroidConfig.compileSdkVersion

            defaultConfig {
                minSdk = AndroidConfig.minSdkVersion
                targetSdk = AndroidConfig.targetSdkVersion

                vectorDrawables.useSupportLibrary = true

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testInstrumentationRunnerArguments += mapOf(
                    "clearPackageData" to "true",
                )
            }

            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = versionHelper.androidX.compose.compiler
            }

            buildTypes {
                getByName("debug") {
                    applicationIdSuffix = ".debug"
                    versionNameSuffix = "-DEBUG"
                    enableUnitTestCoverage = true
                    enableAndroidTestCoverage = true
                    matchingFallbacks += listOf("release")
                }
                getByName("release") {
                    isMinifyEnabled = true
                    isShrinkResources = true

                    proguardFiles(
                        getDefaultProguardFile("proguard-android.txt"),
                        "proguard-rules.pro",
                    )
                    matchingFallbacks += listOf("release")
                }
            }

            lint {
                baseline = File("lint-baseline.xml")

                disable += setOf("Typos")

                warningsAsErrors = true
                abortOnError = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            testOptions {
                animationsDisabled = true
            }

            packagingOptions {
                resources {
                    excludes += setOf(
                        "META-INF/AL2.0",
                        "META-INF/LGPL2.1",
                    )
                }
            }
        }
    }

    private fun setupAndroidKotlinCompatibility(project: Project) {
        project.tasks.withType(KotlinCompile::class.java).all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }

    private fun setupDependencies(project: Project) {
        val libs = DependencyHelper(project)

        project.dependencies {
            implementation(libs.androidx.compose.material)
            implementation(libs.androidx.compose.material3)
        }
    }
}
