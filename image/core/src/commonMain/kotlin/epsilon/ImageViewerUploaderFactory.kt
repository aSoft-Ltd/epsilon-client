package epsilon

import koncurrent.Later
import koncurrent.awaited.then
import koncurrent.awaited.andThen
import koncurrent.awaited.andZip
import koncurrent.awaited.zip
import koncurrent.awaited.catch
import epsilon.internal.ImageViewerUploaderImpl

fun ImageViewerUploader(
    onUpload: ((RawFile) -> Later<String>)? = null,
    url: String? = null
): ImageViewerUploader {
    val state = if (url != null) {
        ViewingImage(url)
    } else {
        AwaitingImage
    }
    return ImageViewerUploaderImpl(state, onUpload)
}