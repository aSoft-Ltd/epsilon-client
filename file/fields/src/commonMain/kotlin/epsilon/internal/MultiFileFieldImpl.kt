package epsilon.internal

import epsilon.FileOutput
import epsilon.MultiFileField
import epsilon.RawFile
import epsilon.RawFileInfo
import kollections.List
import kollections.MutableList
import kollections.map
import koncurrent.later.catch
import koncurrent.later.finally
import koncurrent.later.then
import neat.ValidationFactory
import symphony.Changer
import symphony.Visibility
import symphony.internal.FieldBacker
import symphony.internal.ListFieldImpl

internal class MultiFileFieldImpl(
    backer: FieldBacker<MutableList<FileOutput>>,
    value: List<FileOutput>,
    label: String,
    visibility: Visibility,
    onChange: Changer<List<FileOutput>>?,
    factory: ValidationFactory<List<FileOutput>>?,
) : ListFieldImpl<FileOutput>(backer, value, label, visibility, onChange, factory), MultiFileField {

    override fun addFile(file: RawFile) {
        val output = FileOutput(url = null, updated = true, loading = true, file)
        add(output)
        RawFileInfo(file).path().then {
            output.url = it
        }.finally {
            output.loading = false
            state.value = state.value
        }
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

    private fun String.toOutput() = FileOutput(url = this, updated = true, loading = false, null)
}