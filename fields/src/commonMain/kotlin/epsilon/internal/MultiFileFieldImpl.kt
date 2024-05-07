package epsilon.internal

import epsilon.FileOutput
import epsilon.MultiFileField
import epsilon.RawFile
import epsilon.RawFileInfo
import kollections.List
import kollections.MutableList
import kollections.forEach
import kollections.map
import neat.ValidationFactory
import symphony.Changer
import symphony.Visibility
import symphony.internal.FieldBacker
import symphony.internal.ListFieldImpl

@Suppress("NOTHING_TO_INLINE")
internal class MultiFileFieldImpl(
    backer: FieldBacker<MutableList<FileOutput>>,
    value: List<FileOutput>,
    label: String,
    visibility: Visibility,
    onChange: Changer<List<FileOutput>>?,
    factory: ValidationFactory<List<FileOutput>>?,
) : ListFieldImpl<FileOutput>(backer, value, label, visibility, onChange, factory), MultiFileField {

    override fun addFile(file: RawFile) {
        val info = RawFileInfo(file)
        add(FileOutput(url = info.url, updated = true, info = info, file = info.file))
    }

    override fun addAllFiles(file: List<RawFile>) {
        file.map { addFile(it) }
    }

    override fun addUrl(url: String) {
        add(url.toOutput())
    }

    override fun addAllUrls(url: List<String>) {
        addAll(url.map { it.toOutput() })
    }

    override fun remove(item: FileOutput) {
        item.info?.dispose()
        super.remove(item)
    }

    override fun clear() {
        state.value.output.forEach { it.info?.dispose() }
        super.clear()
    }

    private inline fun String.toOutput() = FileOutput(url = this, updated = true, null)
}