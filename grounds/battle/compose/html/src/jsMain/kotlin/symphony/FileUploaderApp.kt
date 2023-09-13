@file:Suppress("NOTHING_TO_INLINE")

package symphony

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun FileUploaderApp() {
    H1 { Text("Compose Html Image Uploader") }

    Div({
        style {
            display(DisplayStyle.Grid)
            gridTemplateColumns("1fr 1fr 1fr 1fr")
        }
    }) {
        Div({ style { height(200.px) } }) {
            ImageUploader(scene = ImageViewerUploader())
        }

        Div({ style { height(300.px) } }) {
            ImageUploader(
                scene = ImageViewerUploader(),
                placeholder = { Span { Text("Drag image here to upload") } },
                onSave = {
                    val img = it.toFileBlob("jane.png").getOrThrow("Couldn't convert to image")
                    kotlinx.browser.window.location.href = img.path
                },
                save = {
                    Button({
                        style {
                            width(100.percent)
                            height(50.px)
                        }
                    }) { Text("Upload") }
                }
            )
        }

        Div({ style { height(400.px) } }) {
            ImageUploader(
                scene = ImageViewerUploader(),
                placeholder = {
                    Div({ style { display(DisplayStyle.Flex) } }) {
                        H1 { Text("Upload Image") }
                    }
                },
                color = "green",
                save = {}
            )
        }

        Div({ style { height(500.px) } }) {
            ImageUploader(
                scene = ImageViewerUploader(),
                placeholder = { Div { Text("Upload Image") } },
                color = "green",
            )
        }
    }
}