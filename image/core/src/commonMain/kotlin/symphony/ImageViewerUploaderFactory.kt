package symphony

import epsilon.FileBlob
import koncurrent.Later
import symphony.internal.ImageViewerUploaderImpl

fun ImageViewerUploader(
    onUpload: ((FileBlob) -> Later<String>)? = null,
    url: String? = null
): ImageViewerUploader {
    val state = if (url != null) {
        ViewingImage(url)
    } else {
        AwaitingImage
    }
    return ImageViewerUploaderImpl(state, onUpload)
}