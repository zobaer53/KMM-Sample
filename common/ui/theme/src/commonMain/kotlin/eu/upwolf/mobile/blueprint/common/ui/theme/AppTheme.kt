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

package eu.upwolf.mobile.blueprint.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun AppThemeMain(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) {
        AppThemeColorSchemeDark
    } else {
        AppThemeColorSchemeLight
    }

    AppTheme(
        colorScheme = colorScheme,
        content = content,
    )
}

object AppTheme {
    val colorScheme: AppThemeColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: AppThemeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimension: AppThemeDimension
        @Composable
        @ReadOnlyComposable
        get() = LocalDimension.current

    val size: AppThemeSize
        @Composable
        @ReadOnlyComposable
        get() = LocalSize.current
}

@Composable
expect fun AppThemePlatformSetup()

@Composable
fun AppTheme(
    colorScheme: AppThemeColorScheme = AppTheme.colorScheme,
    typography: AppThemeTypography = AppTheme.typography,
    dimension: AppThemeDimension = AppTheme.dimension,
    size: AppThemeSize = AppTheme.size,
    content: @Composable () -> Unit,
) {
    AppThemePlatformSetup()

    ProvideAppTheme(
        colorScheme,
        dimension,
        typography,
        size,
    ) {
        MaterialTheme(
            colorScheme = DebugColorScheme,
            // shapes = Shapes,
            content = content,
        )
    }
}

@Composable
fun ProvideAppTheme(
    colorScheme: AppThemeColorScheme,
    dimension: AppThemeDimension,
    typography: AppThemeTypography,
    size: AppThemeSize,
    content: @Composable () -> Unit,
) {
    val rememberedColorScheme = remember {
        colorScheme.copy()
    }.apply {
        update(colorScheme)
    }

    CompositionLocalProvider(
        LocalColorScheme provides rememberedColorScheme,
        LocalDimension provides dimension,
        LocalTypography provides typography,
        LocalSize provides size,
    ) {
        content()
    }
}
