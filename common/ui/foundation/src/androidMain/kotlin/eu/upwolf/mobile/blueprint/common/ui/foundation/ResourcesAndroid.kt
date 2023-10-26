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

package eu.upwolf.mobile.blueprint.common.ui.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import dev.icerock.moko.resources.StringResource

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
fun drawableId(resourcePath: String, drawableClass: Class<*>): Int {
    val imageName = resourcePath.substringAfterLast("/").substringBefore(".")
    val field = drawableClass.getDeclaredField(imageName)
    val idValue = field.get(drawableClass) as Integer
    return idValue.toInt()
}

@Composable
actual fun painterResource(resourcePath: String): Painter =
    eu.upwolf.mobile.blueprint.common.ui.foundation.painter.painterResource(resourcePath)

@Composable
actual fun stringResource(id: StringResource): String = androidx.compose.ui.res.stringResource(id.resourceId)
