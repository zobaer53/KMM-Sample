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
    alias(libs.plugins.gradleBlueprintConfigurationAppAndroid)
}

android {
    namespace = "eu.upwolf.mobile.blueprint.android"

    defaultConfig {
        applicationId = "eu.upwolf.mobile.blueprint.android"

    }

    testOptions {
        managedDevices {
            devices {
                maybeCreate<com.android.build.api.dsl.ManagedVirtualDevice>("Pixel_2_API_30").apply {
                    device = "Pixel 2"
                    apiLevel = 30
                    systemImageSource = "google-atd"
                }
            }
            groups {
                maybeCreate("android").apply {
                    targetDevices.add(devices["Pixel_2_API_30"])
                }
            }
        }
    }
}

dependencies {
    implementation(projects.common.ui.system)

    implementation(projects.feature.root.component)
    implementation(projects.feature.root.ui)

    implementation(libs.decompose.core)
    implementation(libs.decompose.extensionJetpackCompose)

    // TODO check dependencies below
    implementation(libs.kotlin.android)

    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.activityCompose)

    implementation(libs.google.android.material)

    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.insetsUi)

    implementation(libs.androidx.compose.ui.uiToolingPreview)

    testImplementation(libs.test.junit)

    androidTestImplementation(libs.test.androidx.runner)
    androidTestImplementation(libs.test.androidx.rules)
    androidTestImplementation(libs.test.androidx.orchestrator)
    androidTestImplementation(libs.test.androidx.extJunitKtx)

    androidTestImplementation(libs.test.androidx.espresso.core)
    androidTestImplementation(libs.test.androidx.espresso.intents)
    androidTestImplementation(libs.test.androidx.espresso.web)

    androidTestImplementation(libs.test.androidx.uiautomator)

    androidTestImplementation(libs.test.androidx.compose.ui.junit4)
    androidTestImplementation(libs.test.android.kakaocup.compose)
}
