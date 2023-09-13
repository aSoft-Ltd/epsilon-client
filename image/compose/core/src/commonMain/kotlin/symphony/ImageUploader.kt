package symphony

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cinematic.rememberLive
import epsilon.FileBlob

@Composable
fun ImageUploader(
    scene: ImageViewerUploader,
    placeholder: (@Composable () -> Unit)? = null,
    save: (@Composable () -> Unit)? = null,
    onSave: ((FileBlob) -> Unit)? = null,
    color: String? = null
) {
    val state = rememberLive(scene.state)

    Canvas(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        drawCircle(Color.Red)
    }

    Box(modifier = Modifier.fillMaxSize()) {

    }
}