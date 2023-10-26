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
    jacoco

    alias(libs.plugins.gradleBlueprintDependency)
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

// To make it available as direct dependency
group = "eu.upwolf.gradle.blueprint.tools"
version = "1.0.0-SNAPSHOT"

gradlePlugin {
    // Publish
    plugins.register("eu.upwolf.gradle.blueprint.publish") {
        id = "eu.upwolf.gradle.blueprint.publish"
        implementationClass = "eu.upwolf.gradle.blueprint.publish.PublishConfigurationPlugin"
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

    // Testing
    testImplementation(gradleTestKit())
    testImplementation(libs.test.junit.jupiter)
    testRuntimeOnly(libs.test.junit.jupiter.engine)
}

jacoco {
    version = libs.versions.jacoco.get()
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.named("test"))

    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.named("jacocoTestReport"))
    violationRules {
        rule {
            enabled = true
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = BigDecimal(0.99)
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.named("jacocoTestCoverageVerification"))
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "8.0.1"
    distributionType = Wrapper.DistributionType.ALL
}
