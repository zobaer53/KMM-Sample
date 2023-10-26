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
    id("com.squareup.sqldelight")
}

sqldelight {
    database("CommonSqlDatabase") {
        packageName = "eu.upwolf.mobile.blueprint.common.database"
    }
}

kotlin {
    sourceSets {
        commonMain {
            // No addition
        }
        commonTest {
            // No addition
        }

        androidMain {
            dependencies {
                implementation(libs.sqldelight.androidDriver)
            }
        }
        val androidUnitTest = maybeCreate("androidUnitTest")
        androidUnitTest.dependencies {
            implementation(libs.sqldelight.sqliteDriver)
        }
        val androidInstrumentedTest = maybeCreate("androidInstrumentedTest")
        androidInstrumentedTest.dependencies {
            implementation(libs.sqldelight.androidDriver)
        }

        jvmMain {
            dependencies {
                implementation(libs.sqldelight.sqliteDriver)
            }
        }
        jvmTest {
            // No addition
        }

        jsMain {
            dependencies {
                implementation(libs.sqldelight.jsDriver)
            }
        }

        jsTest {
            // No addition
        }

        iosMain {
            dependencies {
                implementation(libs.sqldelight.nativeDriver)
            }
        }
        iosTest {
            // No addition
        }
    }
}

android {
    namespace = "eu.upwolf.mobile.blueprint.common.database"
}
