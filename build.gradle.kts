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

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven {
            url = uri("https://maven.pkg.github.com/bitfunk/gradle-git-version")
            credentials {
                username = project.findProperty("githubPackageDownload.user") as? String
                    ?: System.getenv("GITHUB_PACKAGE_DOWNLOAD_USER")
                password = project.findProperty("githubPackageDownload.key") as? String
                    ?: System.getenv("GITHUB_PACKAGE_DOWNLOAD_KEY")
            }
        }
    }

    dependencies {
        classpath(libs.gradleKotlinPlugin)
        classpath(libs.gradleAndroidPlugin)
        classpath(libs.gradleSqlDelightPlugin)
        classpath(libs.gradleMokoResourcesGeneratorPlugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintDependency)

    alias(libs.plugins.gradleBitfunkQuality)

    id("jacoco-report-aggregation")
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    configurations.all {
        resolutionStrategy.eachDependency {
            if ("org.jacoco" == requested.group) {
                useVersion(libs.versions.jacoco.get())
            }
        }
    }
}

reportConfig {
    sonarProjectKey.set("bitfunk_blueprint-mobile-kmp")
    sonarOrganization.set("bitfunk")
    coverageReportSourceDirs.set(
        listOf(
            "$projectDir/plugin-development/build/reports/jacoco/testCodeCoverageReport",
            "$projectDir/plugins/build/reports/jacoco/testCodeCoverageReport",
        ),
    )
}

tasks.maybeCreate("clean", Delete::class.java).delete("build")

tasks.named<Wrapper>("wrapper") {
    gradleVersion = libs.versions.gradle.get()
    distributionType = Wrapper.DistributionType.ALL
}

tasks.register("wrapperAll") {
    dependsOn("wrapper")
    dependsOn(gradle.includedBuilds.map { it.task(":wrapper") })
}

tasks.register("dependencyUpdatesAll") {
    dependsOn("dependencyUpdates")
    dependsOn(gradle.includedBuilds.map { it.task(":dependencyUpdates") })
}

tasks.register("versionCatalogUpdateAll") {
    dependsOn("versionCatalogUpdate")
    dependsOn(gradle.includedBuilds.map { it.task(":versionCatalogUpdate") })
}
