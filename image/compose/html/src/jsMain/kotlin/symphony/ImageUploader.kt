package symphony

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cinematic.rememberLive
import epsilon.BrowserBlob
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.gridTemplateRows
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.outline
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Canvas
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.FileInput
import org.w3c.files.Blob
import web.html.HTMLCanvasElement
import web.html.HTMLDivElement
import web.html.HTMLInputElement
import web.timers.clearInterval

@Composable
fun ImageUploader(
    scene: ImageViewerUploader,
    placeholder: (@Composable () -> Unit)? = null,
    save: (@Composable () -> Unit)? = null,
    onSave: ((BrowserBlob) -> Unit)? = null,
    color: String? = null
) {

    val state = rememberLive(scene.state)

    var canvas by remember { mutableStateOf<HTMLCanvasElement?>(null) }
    var input by remember { mutableStateOf<HTMLInputElement?>(null) }
    var saveWrapper by remember { mutableStateOf<HTMLDivElement?>(null) }

    val primaryColor = Color(color ?: "gray")

    DisposableEffect(canvas, saveWrapper, state, color) {
        val renderer = initialize(canvas, saveWrapper, state, color ?: "gray")
        onDispose {
            if (renderer != null) clearInterval(renderer)
        }
    }

    Div({
        style {
            display(DisplayStyle.Block)
            outline(width = 2.px, style = "solid", color = primaryColor)
            width(100.percent)
            height(100.percent)
            cursor("pointer")
        }

        onDrop {
            it.preventDefault()
            scene.editFirst(it.dataTransfer?.files)
        }

        onDragOver { it.preventDefault() }

        onClick { if (state is AwaitingImage) input?.click() }

        onDoubleClick { input?.click() }

    }) {
        FileInput {
            style { display(DisplayStyle.None) }
            ref {
                input = it.unsafeCast<HTMLInputElement>()
                onDispose { input = null }
            }
            onChange { scene.editFirst(it.target.files) }
        }

        Div({
            style {
                display(DisplayStyle.Grid)
                height(100.percent)
                gridTemplateColumns("1fr")
                gridTemplateRows("1fr auto")
            }
        }) {
            when (state) {
                is AwaitingImage -> placeholder?.invoke() ?: ImageUploaderPlaceholder()
                is EditingImage -> Canvas({
                    ref {
                        canvas = it.unsafeCast<HTMLCanvasElement>()
                        onDispose { canvas = null }
                    }
                    style {
                        width(100.percent)
                        height(100.percent)
                        backgroundColor(primaryColor)
                        cursor("move")
                    }
                }) { }

                is LoadingToEditImage -> TODO()
                is UploadingImage -> TODO()
                is ViewingImage -> TODO()
            }
        }

        Div({
            ref {
                saveWrapper = it.unsafeCast<HTMLDivElement>()
                onDispose { saveWrapper = null }
            }
            style { width(100.percent) }
            onClick {
                canvas?.toBlob({
                    val b = it?.unsafeCast<Blob>() ?: return@toBlob
                    val blob = BrowserBlob(b)
                    onSave?.invoke(blob)
                }, "image/png", 1)
            }
        }) { save?.invoke() ?: ImageUploaderSave() }
    }
}