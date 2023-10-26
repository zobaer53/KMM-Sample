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

package eu.upwolf.gradle.blueprint.configuration.kmp.feature

import eu.upwolf.gradle.blueprint.configuration.fixAndroidSourceSets
import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import java.util.Locale

class FeatureConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("eu.upwolf.gradle.blueprint.configuration.kmp.common")
        target.pluginManager.apply("kotlinx-serialization")

        setupMultiplatformLibrary(target)
        setupTargets(target)
    }

    private fun setupMultiplatformLibrary(project: Project) {
        val libs = DependencyHelper(project)

        project.kotlin {
            sourceSets {
                maybeCreate("commonMain").dependencies {
                    implementation(libs.decompose.core)
                    implementation(libs.koin.core)
                }

                maybeCreate("commonTest").dependencies {
                    implementation(libs.koin.test)
                }
            }
        }
    }

    private fun setupTargets(project: Project) {
        setupAndroidTarget(project)
        setupIosTarget(project)
    }

    private fun setupAndroidTarget(project: Project) {
        project.kotlin {
            sourceSets {
                maybeCreate("androidMain").dependencies {
                    // Nothing to add
                }
                val androidUnitTest = maybeCreate("androidUnitTest")
                androidUnitTest.dependencies {
                    // Nothing to add
                }
                fixAndroidSourceSets(androidUnitTest)
            }
        }
    }

    private fun setupIosTarget(project: Project) {
        project.kotlin {
            ios {
                binaries {
                    framework("Feature${project.name.capitalize(Locale.ENGLISH)}")
                }
            }

            sourceSets {
                maybeCreate("iosMain").dependencies {
                    // Nothing to add
                }
                maybeCreate("iosTest").dependencies {
                    // Nothing to add
                }
            }
        }
    }

    private fun Project.kotlin(action: Action<KotlinMultiplatformExtension>) {
        extensions.configure(KotlinMultiplatformExtension::class.java, action)
    }
}
