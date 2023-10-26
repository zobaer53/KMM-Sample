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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import eu.upwolf.mobile.blueprint.common.ui.theme.AppTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colorScheme.primary,
            contentColor = AppTheme.colorScheme.onPrimary,
        ),
        contentPadding = PaddingValues(
            start = AppTheme.dimension.spacingDouble,
            top = AppTheme.dimension.spacingSingle,
            end = AppTheme.dimension.spacingDouble,
            bottom = AppTheme.dimension.spacingSingle,
        ),
    ) {
        Text(
            text = text,
            style = AppTheme.typography.labelMedium,
        )
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colorScheme.secondary,
            contentColor = AppTheme.colorScheme.onSecondary,
        ),
        contentPadding = PaddingValues(
            start = AppTheme.dimension.spacingDouble,
            top = AppTheme.dimension.spacingSingle,
            end = AppTheme.dimension.spacingDouble,
            bottom = AppTheme.dimension.spacingSingle,
        ),
    ) {
        Text(
            text = text,
            style = AppTheme.typography.labelMedium,
        )
    }
}
