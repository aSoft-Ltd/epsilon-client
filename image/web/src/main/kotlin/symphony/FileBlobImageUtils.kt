package symphony

import epsilon.FileBlob
import epsilon.fileBlobOf
import js.core.asList
import org.w3c.dom.asList
import org.w3c.files.FileList as W3CFileList
import web.file.FileList as WebFileList
import web.html.Image

fun FileBlob.toImage() = Image().apply { src = path }

fun ImageViewerUploader.editFirst(files: WebFileList?) {
    val file = files?.asList()?.firstOrNull() ?: return
    edit(fileBlobOf(file.unsafeCast<org.w3c.files.File>()))
}

fun ImageViewerUploader.editFirst(files: W3CFileList?) {
    val file = files?.asList()?.firstOrNull() ?: return
    edit(fileBlobOf(file))
}