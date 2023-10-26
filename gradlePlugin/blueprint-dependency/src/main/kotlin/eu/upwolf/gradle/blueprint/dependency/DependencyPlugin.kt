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

package eu.upwolf.gradle.blueprint.dependency

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import nl.littlerobots.vcu.plugin.versionCatalogUpdate
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.named

class DependencyPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(plugin = "com.github.ben-manes.versions")
        target.apply(plugin = "nl.littlerobots.version-catalog-update")

        target.tasks.named<DependencyUpdatesTask>("dependencyUpdates")
            .configure {
                // reject all non stable versions
                // and disallow release candidates as upgradable versions from stable versions
                resolutionStrategy {
                    componentSelection {
                        all {
                            if (isStable(currentVersion) && !isStable(candidate.version)) {
                                reject("Release candidate")
                            }
                        }
                    }
                }
            }

        target.versionCatalogUpdate {
            keepUnused = true
            sortByKey = true
        }
    }

    private fun isStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
        val regex = "^([0-9]+)\\.([0-9]+)\\.([0-9]+)\$".toRegex()
        return stableKeyword || regex.matches(version)
    }
}
