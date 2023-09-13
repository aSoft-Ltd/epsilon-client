@file:JsExport

package symphony

import epsilon.Blob
import epsilon.FileBlob
import kollections.JsExport

sealed interface ImageViewerUploaderState {
    val asAwaiting get() = this as? AwaitingImage
    val asViewing get() = this as? ViewingImage
    val asLoadingToEdit get() = this as? LoadingToEditImage
    val asEditing get() = this as? EditingImage
    val asUploading get() = this as? UploadingImage
}

object AwaitingImage : ImageViewerUploaderState

data class ViewingImage(val url: String) : ImageViewerUploaderState

data class LoadingToEditImage(val image: FileBlob) : ImageViewerUploaderState

data class EditingImage(val image: FileBlob) : ImageViewerUploaderState

data class UploadingImage(val image: Blob) : ImageViewerUploaderState