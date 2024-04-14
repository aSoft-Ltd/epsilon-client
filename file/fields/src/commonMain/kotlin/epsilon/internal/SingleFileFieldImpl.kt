package epsilon.internal

import epsilon.FileOutput
import epsilon.RawFile
import epsilon.RawFileInfo
import epsilon.SingleFileField
import koncurrent.later.catch
import koncurrent.later.then
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

    private val initial = FileOutput(url = value?.url, updated = false, loading = false, file = value?.file)

    override fun set(file: RawFile) {
        val current = state.value.output ?: initial
        val loading = current.copy(loading = true)
        set(loading)
        RawFileInfo(file).path().then {
            loading.copy(updated = true, loading = false, url = it, file = file)
        }.catch {
            loading.copy(loading = false)
        }.then {
            set(it)
        }
    }

    override fun set(url: String) {
        val current = state.value.output ?: initial
        set(current.copy(url = url, updated = true, file = null))
    }
}