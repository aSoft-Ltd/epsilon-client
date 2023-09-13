package symphony

import react.create
import react.dom.client.createRoot
import web.dom.document

fun main() {
    val root = document.getElementById("root") ?: error("No root component")
    createRoot(root).render(FileUploaderApp.create())
}