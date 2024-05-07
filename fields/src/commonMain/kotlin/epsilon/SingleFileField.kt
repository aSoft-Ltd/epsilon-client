@file:JsExport
package epsilon

import kotlinx.JsExport
import kotlinx.JsName
import symphony.BaseField

interface SingleFileField : BaseField<FileOutput> {
    @JsName("setFile")
    fun set(file: RawFile)

    @JsName("setUrl")
    fun set(url: String)
}