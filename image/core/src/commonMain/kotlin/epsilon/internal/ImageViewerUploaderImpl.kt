package epsilon.internal

import cinematic.mutableLiveOf
import epsilon.EditingImage
import epsilon.ImageViewerUploader
import epsilon.ImageViewerUploaderState
import epsilon.RawFile
import epsilon.RawFileInfo
import epsilon.UploadingImage
import epsilon.ViewingImage
import kase.Failure
import kase.Success
import koncurrent.FailedLater
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import koncurrent.later.finally

internal class ImageViewerUploaderImpl(
    start: ImageViewerUploaderState,
    override val uploader: ((RawFile) -> Later<String>)? = null
) : ImageViewerUploader {
    override val state = mutableLiveOf(start)

    private var name: String? = null
    override fun view(url: String) {
        state.value = ViewingImage(url)
    }

    override fun edit(image: RawFile) {
        name = RawFileInfo(image).nameWithExtension
        state.value = EditingImage(image)
    }

    override fun upload(image: RawFile): Later<String> {
        state.value = UploadingImage(image)
        val handler = uploader ?: { FailedLater("Can't upload without an uploader") }
        return handler(image).finally {
            when (it) {
                is Success -> view(it.data)
                is Failure -> edit(image)
            }
        } as Later<String>
    }
}