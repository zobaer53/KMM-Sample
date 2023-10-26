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

package eu.upwolf.mobile.blueprint.common.ui.system

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import eu.upwolf.mobile.blueprint.common.ui.theme.AppTheme

@Composable
fun ThemeOverview() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ColorOverview()

        Spacer(modifier = Modifier.height(AppTheme.dimension.spacingTriple))

        SpacingOverview()

        Spacer(modifier = Modifier.height(AppTheme.dimension.spacingTriple))

        SizeOverview()

        Spacer(modifier = Modifier.height(AppTheme.dimension.spacingTriple))

        TypographyOverview()
    }
}

@Composable
private fun ColorOverview() {
    HeadlineMedium("Colors")

    Spacer(modifier = Modifier.height(AppTheme.dimension.spacingDouble))

    Row(
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimension.spacingSingle),
    ) {
        // TODO colors
        ColorSquare(color = AppTheme.colorScheme.primary, "Primary")
        ColorSquare(color = AppTheme.colorScheme.secondary, "Secondary")
        ColorSquare(color = AppTheme.colorScheme.tertiary, "Tertiary")
    }
}

@Composable
private fun SpacingOverview() {
    HeadlineMedium("Dimension")

    Spacer(modifier = Modifier.height(AppTheme.dimension.spacingDouble))

    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimension.spacingSingle),
    ) {
        SizedLine(size = AppTheme.dimension.spacingQuarter, "Spacing Quarter")
        SizedLine(size = AppTheme.dimension.spacingHalf, "Spacing Half")
        SizedLine(size = AppTheme.dimension.spacingSingle, "Spacing Single")
        SizedLine(size = AppTheme.dimension.spacingOneHalf, "Spacing One Half")
        SizedLine(size = AppTheme.dimension.spacingDouble, "Spacing Double")
        SizedLine(size = AppTheme.dimension.spacingTriple, "Spacing Triple")
        SizedLine(size = AppTheme.dimension.spacingQuadruple, "Spacing Quadruple")
    }
}

@Composable
private fun SizeOverview() {
    HeadlineMedium("Size")

    Spacer(modifier = Modifier.height(AppTheme.dimension.spacingDouble))

    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimension.spacingSingle),
    ) {
        SizedLine(size = AppTheme.size.smaller, "Smaller")
        SizedLine(size = AppTheme.size.small, "Small")
        SizedLine(size = AppTheme.size.medium, "Medium")
        SizedLine(size = AppTheme.size.large, "Large")
        SizedLine(size = AppTheme.size.larger, "Larger")
    }
}

@Composable
private fun TypographyOverview() {
    HeadlineMedium("Typography")

    Spacer(modifier = Modifier.height(AppTheme.dimension.spacingDouble))

    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimension.spacingSingle),
    ) {
        TypographyText(AppTheme.typography.displayLarge, "Display Large")
        TypographyText(AppTheme.typography.displayMedium, "Display Medium")
        TypographyText(AppTheme.typography.displaySmall, "Display Small")

        TypographyText(AppTheme.typography.headlineLarge, "Headline Large")
        TypographyText(AppTheme.typography.headlineMedium, "Headline Medium")
        TypographyText(AppTheme.typography.headlineSmall, "Headline Small")

        TypographyText(AppTheme.typography.titleLarge, "Title Large")
        TypographyText(AppTheme.typography.titleMedium, "Title Medium")
        TypographyText(AppTheme.typography.titleSmall, "Title Small")

        TypographyText(AppTheme.typography.labelLarge, "Label Large")
        TypographyText(AppTheme.typography.labelMedium, "Label Medium")
        TypographyText(AppTheme.typography.labelSmall, "Label Small")

        TypographyText(AppTheme.typography.bodyLarge, "Body Large")
        TypographyText(AppTheme.typography.bodyMedium, "Body Medium")
        TypographyText(AppTheme.typography.bodySmall, "Body Small")
    }
}

@Composable
private fun ColorSquare(
    color: Color,
    name: String,
) {
    val borderColor = if (AppTheme.colorScheme.background == color) AppTheme.colorScheme.onBackground else color

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(AppTheme.size.medium)
                .clip(RoundedCornerShape(AppTheme.size.smaller))
                .border(1.dp, borderColor, shape = RoundedCornerShape(AppTheme.size.smaller))
                .background(color),
        )

        Spacer(modifier = Modifier.height(AppTheme.dimension.spacingHalf))

        Text(
            text = name,
            color = AppTheme.colorScheme.onBackground,
            style = AppTheme.typography.labelMedium,
        )
    }
}

@Composable
private fun SizedLine(
    size: Dp,
    name: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            modifier = Modifier.widthIn(min = 144.dp),
            color = AppTheme.colorScheme.onBackground,
            style = AppTheme.typography.labelMedium,
        )

        Box(
            modifier = Modifier
                .width(size)
                .height(AppTheme.dimension.spacingHalf)
                .background(AppTheme.colorScheme.primary),
        )
    }
}

@Composable
private fun TypographyText(
    typography: TextStyle,
    name: String,
) {
    Text(
        text = name,
        color = AppTheme.colorScheme.primary,
        style = typography,
    )
}
