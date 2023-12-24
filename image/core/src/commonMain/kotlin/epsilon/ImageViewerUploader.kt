@file:JsExport

package epsilon

import cinematic.Live
import kotlinx.JsExport
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch

interface ImageViewerUploader {
    val state: Live<ImageViewerUploaderState>
    val uploader: ((RawFile) -> Later<String>)?
    fun view(url: String)
    fun edit(image: RawFile)

    fun upload(image: RawFile): Later<String>
}