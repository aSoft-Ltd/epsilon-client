package epsilon

import koncurrent.Later
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