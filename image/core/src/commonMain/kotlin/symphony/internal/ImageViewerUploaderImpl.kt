package symphony.internal

import cinematic.mutableLiveOf
import epsilon.FileBlob
import kase.Failure
import kase.Success
import koncurrent.Later
import koncurrent.later.finally
import symphony.EditingImage
import symphony.ImageViewerUploader
import symphony.ImageViewerUploaderState
import symphony.UploadingImage
import symphony.ViewingImage

internal class ImageViewerUploaderImpl(
    start: ImageViewerUploaderState,
    override val uploader: ((FileBlob) -> Later<String>)? = null
) : ImageViewerUploader {
    override val state = mutableLiveOf(start)

    private var name: String? = null
    override fun view(url: String) {
        state.value = ViewingImage(url)
    }

    override fun edit(image: FileBlob) {
        name = image.name
        state.value = EditingImage(image)
    }

    override fun upload(image: FileBlob): Later<String> {
        state.value = UploadingImage(image)
        val handler = uploader ?: { Later(image.path) }
        return handler(image).finally {
            when (it) {
                is Success -> view(it.data)
                is Failure -> edit(image)
            }
        }
    }
}