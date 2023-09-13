package symphony

import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable("root") {
        FileUploaderApp()
    }
}