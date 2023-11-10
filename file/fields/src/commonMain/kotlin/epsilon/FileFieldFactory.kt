package epsilon

import kollections.List
import kollections.MutableList
import neat.ValidationFactory
import symphony.internal.BaseFieldImpl
import symphony.Fields
import symphony.Visibility
import symphony.Changer
import symphony.ListField
import symphony.list
import symphony.BaseField
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0
import symphony.Visibilities

fun Fields<*>.files(
    name: KProperty0<MutableList<RawFile>>,
    label: String = name.name,
    visibility: Visibility = Visibilities.Visible,
    onChange: Changer<List<RawFile>>? = null,
    factory: ValidationFactory<List<RawFile>>? = null
): ListField<RawFile> = list(name, label, visibility, onChange, factory)

fun Fields<*>.file(
    name: KMutableProperty0<RawFile?>,
    label: String = name.name,
    hint: String = label,
    visibility: Visibility = Visibilities.Visible,
    onChange: Changer<RawFile>? = null,
    factory: ValidationFactory<RawFile>? = null
): BaseField<RawFile> = getOrCreate(name) {
    BaseFieldImpl(name, label, visibility, hint, onChange, factory)
}