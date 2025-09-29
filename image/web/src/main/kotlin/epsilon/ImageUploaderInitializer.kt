package epsilon

import web.canvas.CanvasRenderingContext2D
//import web.canvas.RenderingContextId
import web.html.HTMLCanvasElement
import web.html.HTMLDivElement
import web.html.Image
import web.timers.Timeout
import web.timers.setInterval
import koncurrent.awaited.then
import web.events.EventHandler
import web.timers.setTimeout
import kotlin.math.max
import kotlin.math.min
import kotlin.time.Duration.Companion.milliseconds

fun initialize(
    canvas: HTMLCanvasElement?,
    saveWrapper: HTMLDivElement?,
    state: ImageViewerUploaderState,
    color: String
): Timeout? {
    val fileBlobAsImage = (state as? EditingImage)?.image ?: return null
    val parent = canvas?.parentElement ?: return null
    val saveElement = saveWrapper ?: return null
    val canvasWidth = parent.offsetWidth
    val canvasHeight = parent.offsetHeight - saveElement.offsetHeight
    canvas.width = parent.offsetWidth
    canvas.height = canvasHeight

//    val context = canvas.getContext(RenderingContextId.canvas) ?: return null
    val context = canvas.getContext(CanvasRenderingContext2D.ID) ?: return null
    val image = fileBlobAsImage.toImage()
    val full = Position(canvasWidth, canvasHeight)

    var renderer = 0.unsafeCast<Timeout>()
    image.then { i-> i.onload = EventHandler { renderer = initialize(canvas, context, i, full, color) } }
    return renderer
}

fun initialize(
    canvas: HTMLCanvasElement,
    context: CanvasRenderingContext2D,
    image: Image,
    full: Position,
    color: String
): Timeout {
    var dragging = false
    val dragStart = Position()
    var dragRef = Position()

    var scale = 1.0
    val size = fit(image.size, full)

    val startPoint = Position(
        x = (full.x - size.x) / 2,
        y = (full.y - size.y) / 2
    )

    var imageAnchor = startPoint

    canvas.onmousedown = EventHandler {event->
        dragging = true
        dragStart.update(event)
        dragRef = imageAnchor
    }

    canvas.onwheel = EventHandler {event->
        event.preventDefault()
        scale += event.deltaY * -0.01

        // Restrict scale
        scale = min(max(0.125, scale), 4.0)
        imageAnchor = startPoint + size * (1 - scale) / 2
    }

    canvas.onmousemove = EventHandler { event->
        if (dragging) {
            val dragEnd = event.toPosition()
            val dDrag = dragEnd - dragStart
            imageAnchor = dragRef + dDrag
        }
    }

    canvas.onmouseup = EventHandler { dragging = false }

    return setTimeout(10.milliseconds) { // 100 frames per seconds
        context.clearRect(0.0, 0.0, full.x.toDouble(), full.y.toDouble())
        context.fillStyle = color
        context.fillRect(0.0, 0.0, full.x.toDouble(), full.y.toDouble())
        val s = size * scale
        context.drawImage(image, imageAnchor.x.toDouble(), imageAnchor.y.toDouble(), s.x.toDouble(), s.y.toDouble())
    }

//    return setInterval(10.milliseconds) { // 100 frames per seconds
//        context.clearRect(0, 0, full.x, full.y)
//        context.fillStyle = color
//        context.fillRect(0, 0, full.x, full.y)
//        val s = size * scale
//        context.drawImage(image, imageAnchor.x, imageAnchor.y, s.x, s.y)
//    }
}