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

package eu.upwolf.mobile.blueprint.common.ui.foundation.painter

import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.util.Xml
import org.xmlpull.v1.XmlPullParserException

/**
 * Taken from androidx.compose.ui.res.VectorResources.android.kt
 *
 * Modified to load from assets by file name
 */

@Throws(XmlPullParserException::class)
@SuppressWarnings("RestrictedApi")
internal fun loadVectorResourceInner(
    theme: Resources.Theme? = null,
    res: Resources,
    parser: XmlResourceParser,
): ImageVectorCache.ImageVectorEntry {
    val attrs = Xml.asAttributeSet(parser)
    val resourceParser = AndroidVectorParser(parser)
    val builder = resourceParser.createVectorImageBuilder(res, theme, attrs)

    var nestedGroups = 0
    while (!parser.isAtEnd()) {
        nestedGroups = resourceParser.parseCurrentVectorNode(
            res,
            attrs,
            theme,
            builder,
            nestedGroups,
        )
        parser.next()
    }
    return ImageVectorCache.ImageVectorEntry(builder.build(), resourceParser.config)
}
