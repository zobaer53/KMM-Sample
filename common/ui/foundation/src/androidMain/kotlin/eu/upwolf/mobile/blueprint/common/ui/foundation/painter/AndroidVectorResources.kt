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

internal object AndroidVectorResources {
    val STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY = intArrayOf(
        android.R.attr.name,
        android.R.attr.tint,
        android.R.attr.height,
        android.R.attr.width,
        android.R.attr.alpha,
        android.R.attr.autoMirrored,
        android.R.attr.tintMode,
        android.R.attr.viewportWidth,
        android.R.attr.viewportHeight,
    )
    val STYLEABLE_VECTOR_DRAWABLE_VIEWPORT_HEIGHT = 8
    val STYLEABLE_VECTOR_DRAWABLE_VIEWPORT_WIDTH = 7
    val STYLEABLE_VECTOR_DRAWABLE_HEIGHT = 2
    val STYLEABLE_VECTOR_DRAWABLE_WIDTH = 3
    val STYLEABLE_VECTOR_DRAWABLE_GROUP = intArrayOf(
        android.R.attr.name,
        android.R.attr.pivotX,
        android.R.attr.pivotY,
        android.R.attr.scaleX,
        android.R.attr.scaleY,
        android.R.attr.rotation,
        android.R.attr.translateX,
        android.R.attr.translateY,
    )
    val STYLEABLE_VECTOR_DRAWABLE_GROUP_NAME = 0
    val STYLEABLE_VECTOR_DRAWABLE_GROUP_PIVOT_X = 1
    val STYLEABLE_VECTOR_DRAWABLE_GROUP_PIVOT_Y = 2
    val STYLEABLE_VECTOR_DRAWABLE_GROUP_ROTATION = 5
    val STYLEABLE_VECTOR_DRAWABLE_GROUP_SCALE_X = 3
    val STYLEABLE_VECTOR_DRAWABLE_GROUP_SCALE_Y = 4
    val STYLEABLE_VECTOR_DRAWABLE_GROUP_TRANSLATE_X = 6
    val STYLEABLE_VECTOR_DRAWABLE_GROUP_TRANSLATE_Y = 7
    val STYLEABLE_VECTOR_DRAWABLE_TINT = 1
    val STYLEABLE_VECTOR_DRAWABLE_TINT_MODE = 6
    val STYLEABLE_VECTOR_DRAWABLE_PATH = intArrayOf(
        android.R.attr.name,
        android.R.attr.fillColor,
        android.R.attr.pathData,
        android.R.attr.strokeColor,
        android.R.attr.strokeWidth,
        android.R.attr.trimPathStart,
        android.R.attr.trimPathEnd,
        android.R.attr.trimPathOffset,
        android.R.attr.strokeLineCap,
        android.R.attr.strokeLineJoin,
        android.R.attr.strokeMiterLimit,
        android.R.attr.strokeAlpha,
        android.R.attr.fillAlpha,
        // android.R.attr.fillType is in API level 24+ use hardcoded value to extract the attribute if it exists
        0x101051E,
    )
    val STYLEABLE_VECTOR_DRAWABLE_PATH_FILL_ALPHA = 12
    val STYLEABLE_VECTOR_DRAWABLE_PATH_FILL_COLOR = 1
    val STYLEABLE_VECTOR_DRAWABLE_PATH_NAME = 0
    val STYLEABLE_VECTOR_DRAWABLE_PATH_PATH_DATA = 2
    val STYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_LINE_CAP = 8
    val STYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_LINE_JOIN = 9
    val STYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_MITER_LIMIT = 10
    val STYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_COLOR = 3
    val STYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_ALPHA = 11
    val STYLEABLE_VECTOR_DRAWABLE_PATH_STROKE_WIDTH = 4
    val STYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_END = 6
    val STYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_OFFSET = 7
    val STYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_START = 5
    val STYLEABLE_VECTOR_DRAWABLE_PATH_TRIM_PATH_FILLTYPE = 13
    val STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH =
        intArrayOf(android.R.attr.name, android.R.attr.pathData)
    val STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH_NAME = 0
    val STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH_PATH_DATA = 1
}
