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

import android.content.res.Configuration
import android.content.res.Resources
import androidx.compose.ui.graphics.vector.ImageVector
import java.lang.ref.WeakReference

internal class ImageVectorCache {
    /**
     * Key that binds the corresponding theme with the resource identifier for the vector asset
     */
    data class Key(
        val theme: Resources.Theme,
        val id: String,
    )

    /**
     * Tuple that contains the [ImageVector] as well as the corresponding configuration flags
     * that the [ImageVector] depends on. That is if there is a configuration change that updates
     * the parameters in the flag, this vector should be regenerated from the current configuration
     */
    data class ImageVectorEntry(
        val imageVector: ImageVector,
        val configFlags: Int,
    )

    private val map = HashMap<Key, WeakReference<ImageVectorEntry>>()

    operator fun get(key: Key): ImageVectorEntry? = map[key]?.get()

    fun prune(configChanges: Int) {
        val it = map.entries.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            val imageVectorEntry = entry.value.get()
            if (imageVectorEntry == null ||
                Configuration.needNewResources(configChanges, imageVectorEntry.configFlags)
            ) {
                it.remove()
            }
        }
    }

    operator fun set(key: Key, imageVectorEntry: ImageVectorEntry) {
        map[key] = WeakReference<ImageVectorEntry>(imageVectorEntry)
    }

    fun clear() {
        map.clear()
    }
}
