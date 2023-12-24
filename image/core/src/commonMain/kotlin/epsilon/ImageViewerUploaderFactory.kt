package epsilon

import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
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