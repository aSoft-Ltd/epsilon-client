package symphony

import js.core.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import web.cssom.pct

internal val ImageUploaderSave = FC<Props> {
    button {
        style = jso { width = 100.pct }
        +"Save"
    }
}