@file:JsExport

package epsilon

import cinematic.Live
import kotlinx.JsExport
import koncurrent.Later

interface ImageViewerUploader {
    val state: Live<ImageViewerUploaderState>
    val uploader: ((RawFile) -> Later<String>)?
    fun view(url: String)
    fun edit(image: RawFile)

    fun upload(image: RawFile): Later<String>
}