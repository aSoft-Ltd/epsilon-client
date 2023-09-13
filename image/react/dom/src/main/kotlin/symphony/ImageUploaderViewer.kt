package symphony

import js.core.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import web.cssom.AlignItems
import web.cssom.BackgroundPosition
import web.cssom.BackgroundRepeat
import web.cssom.BackgroundSize
import web.cssom.Display
import web.cssom.JustifyContent
import web.cssom.pct
import web.cssom.url

@JsExport
external interface ImageUploaderViewerProps : Props {
    var url: String
}

internal val ImageUploaderViewer = FC<ImageUploaderViewerProps> { props ->
    div {
        style = jso {
            width = 100.pct
            height = 100.pct
            backgroundImage = url(props.url)
            backgroundPosition = "center".unsafeCast<BackgroundPosition>()
            backgroundSize = BackgroundSize.contain
            backgroundRepeat = BackgroundRepeat.noRepeat
        }
    }
}