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

package eu.upwolf.mobile.blueprint.feature.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.replaceCurrent
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import eu.upwolf.mobile.blueprint.feature.home.component.HomeComponent
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract.Child
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract.Config
import eu.upwolf.mobile.blueprint.feature.splash.component.SplashComponent

class RootComponent(
    componentContext: ComponentContext,
) : RootComponentContract.Component, ComponentContext by componentContext {

    private val router = router<Config, Child>(
        initialConfiguration = Config.Splash,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.Splash -> splash(componentContext)
            is Config.Home -> home(componentContext)
        }

    private fun splash(componentContext: ComponentContext): Child.Splash {
        return Child.Splash(
            SplashComponent(
                componentContext = componentContext,
                onFinishedAction = {
                    router.replaceCurrent(Config.Home)
                },
            ),
        )
    }

    private fun home(componentContext: ComponentContext): Child.Home {
        return Child.Home(
            HomeComponent(
                componentContext = componentContext,
                onFinishedAction = {
                    router.replaceCurrent(Config.Splash)
                },
            ),
        )
    }
}
