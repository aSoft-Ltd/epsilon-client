package epsilon

import js.array.asList
//import js.core.asList
import org.w3c.dom.asList
import koncurrent.awaited.then
import org.w3c.files.File
import org.w3c.files.FileList as W3CFileList
import web.file.FileList as WebFileList
import web.html.Image

fun RawFile.toImage() = RawFileInfo(this).path().then { Image().apply { src = it } }

fun ImageViewerUploader.editFirst(files: WebFileList?) {
    val file = files?.asList()?.firstOrNull() ?: return
    edit(file as File)
}

fun ImageViewerUploader.editFirst(files: W3CFileList?) {
    val file = files?.asList()?.firstOrNull() ?: return
    edit(file)
}