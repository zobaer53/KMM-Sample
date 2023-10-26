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

package eu.upwolf.mobile.blueprint.feature.root.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.childAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.fade
import eu.upwolf.mobile.blueprint.feature.home.ui.HomeContent
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract.Child
import eu.upwolf.mobile.blueprint.feature.splash.ui.SplashContent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(component: RootComponentContract.Component) {
    Children(
        routerState = component.routerState,
        animation = childAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is Child.Splash -> SplashContent(component = child.component)
            is Child.Home -> HomeContent()
        }
    }
}
