@file:JsExport
package epsilon

import kotlinx.JsExport

data class FileOutput(
    var url: String? = null,
    var updated: Boolean = false,
    var info: RawFileInfo? = null,
    var file: RawFile? = null
)