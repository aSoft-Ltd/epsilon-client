@file:JsExport

package epsilon

import cinematic.Live
import kotlinx.JsExport
import koncurrent.Later
import koncurrent.awaited.then
import koncurrent.awaited.andThen
import koncurrent.awaited.andZip
import koncurrent.awaited.zip
import koncurrent.awaited.catch

interface ImageViewerUploader {
    val state: Live<ImageViewerUploaderState>
    val uploader: ((RawFile) -> Later<String>)?
    fun view(url: String)
    fun edit(image: RawFile)

    fun upload(image: RawFile): Later<String>
}