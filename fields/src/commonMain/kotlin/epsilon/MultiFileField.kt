@file:JsExport
package epsilon

import kollections.List
import kotlinx.JsExport
import symphony.ListField

interface MultiFileField : ListField<FileOutput> {
    fun addFile(file: RawFile)
    fun addAllFiles(file: List<RawFile>)

    fun addUrl(url: String)
    fun addAllUrls(url: List<String>)
}