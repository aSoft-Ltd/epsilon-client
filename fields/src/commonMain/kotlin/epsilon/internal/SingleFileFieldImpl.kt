package epsilon.internal

import epsilon.FileOutput
import epsilon.RawFile
import epsilon.RawFileInfo
import epsilon.SingleFileField
import neat.ValidationFactory
import symphony.Changer
import symphony.Visibility
import symphony.internal.FieldBacker
import symphony.internal.GenericBaseField

internal class SingleFileFieldImpl(
    backer: FieldBacker<FileOutput>,
    value: FileOutput?,
    label: String,
    visibility: Visibility,
    hint: String,
    onChange: Changer<FileOutput>?,
    factory: ValidationFactory<FileOutput>?,
) : GenericBaseField<FileOutput>(backer, value, label, visibility, hint, onChange, factory), SingleFileField {

    override fun set(file: RawFile) {
        state.value.output?.info?.dispose()
        val info = RawFileInfo(file)
        set(FileOutput(updated = true, url = info.url, info = info, file = info.file))
    }

    override fun set(url: String) {
        state.value.output?.info?.dispose()
        set(FileOutput(updated = true, url = url, info = null, file = null))
    }

    override fun finish() {
        state.value.output?.info?.dispose()
        super.finish()
    }

    override fun clear() {
        state.value.output?.info?.dispose()
        super.clear()
    }
}