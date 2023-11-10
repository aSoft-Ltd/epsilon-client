package symphony

import androidx.compose.runtime.Composable
import epsilon.ImageUploader
import epsilon.ImageViewerUploader

@Composable
fun ImageUploaderApp() {
    ImageUploader(scene = ImageViewerUploader())
}