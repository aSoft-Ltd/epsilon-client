package epsilon

import epsilon.internal.MultiFileFieldImpl
import epsilon.internal.SingleFileFieldImpl
import kollections.List
import kollections.MutableList
import kollections.emptyList
import kollections.map
import kollections.mapNotNull
import neat.ValidationFactory
import symphony.Changer
import symphony.Fields
import symphony.Visibilities
import symphony.Visibility
import symphony.internal.FieldBacker
import kotlin.reflect.KMutableProperty0

fun Fields<*>.files(
    name: KMutableProperty0<MutableList<FileOutput>>,
    label: String = name.name,
    value: List<String>? = name.get().mapNotNull { it.url },
    visibility: Visibility = Visibilities.Visible,
    onChange: Changer<List<FileOutput>>? = null,
    factory: ValidationFactory<List<FileOutput>>? = null
): MultiFileField {
    val backer = FieldBacker.Prop(name as KMutableProperty0<MutableList<FileOutput>?>)
    return MultiFileFieldImpl(backer, value?.toOutput() ?: name.get(), label, visibility, onChange, factory)
}

fun FilesField(
    name: String,
    label: String = name,
    value: List<String> = emptyList(),
    visibility: Visibility = Visibilities.Visible,
    onChange: Changer<List<FileOutput>>? = null,
    factory: ValidationFactory<List<FileOutput>>? = null
): MultiFileField {
    val backer = FieldBacker.Name(name)
    return MultiFileFieldImpl(backer, value.toOutput(), label, visibility, onChange, factory)
}

fun FileField(
    name: String,
    label: String = name,
    hint: String = label,
    value: String? = null,
    visibility: Visibility = Visibilities.Visible,
    onChange: Changer<FileOutput>? = null,
    factory: ValidationFactory<FileOutput>? = null
): SingleFileField = SingleFileFieldImpl(FieldBacker.Name(name), value.toOutput(), label, visibility, hint, onChange, factory)

fun Fields<*>.file(
    name: KMutableProperty0<FileOutput?>,
    label: String = name.name,
    hint: String = label,
    value: String? = name.get()?.url,
    visibility: Visibility = Visibilities.Visible,
    onChange: Changer<FileOutput>? = null,
    factory: ValidationFactory<FileOutput>? = null
): SingleFileField = getOrCreate(name) {
    SingleFileFieldImpl(FieldBacker.Prop(name), value.toOutput(), label, visibility, hint, onChange, factory)
}

private fun String?.toOutput() = FileOutput(url = this, updated = false, info = null, file = null)

private fun List<String>.toOutput() = map { it.toOutput() }