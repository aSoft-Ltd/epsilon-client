@file:JsExport
package epsilon

import kotlinx.JsExport

data class FileOutput(
    var url: String? = null,
    var updated: Boolean = false,
    var loading: Boolean = false,
    var file: RawFile? = null
)