@file:Suppress("NOTHING_TO_INLINE")

package symphony

import web.html.Image
import web.uievents.MouseEvent

inline fun Position.update(e: MouseEvent) {
    x = e.clientX
    y = e.clientY
}

inline fun MouseEvent.toPosition() = Position(clientX, clientY)

inline val Image.size get() = Position(width, height)