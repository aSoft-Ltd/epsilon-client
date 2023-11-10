@file:JsExport

package epsilon

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

data class LoadingToEditImage(val image: RawFile) : ImageViewerUploaderState

data class EditingImage(val image: RawFile) : ImageViewerUploaderState

data class UploadingImage(val image: RawFile) : ImageViewerUploaderState