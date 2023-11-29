package epsilon

import kollections.List
import kollections.MutableList
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0
import neat.ValidationFactory
import symphony.BaseField
import symphony.Changer
import symphony.Fields
import symphony.ListField
import symphony.Visibilities
import symphony.Visibility
import symphony.internal.FieldBacker
import symphony.internal.GenericBaseField
import symphony.list

fun Fields<*>.files(
    name: KProperty0<MutableList<RawFile>>,
    label: String = name.name,
    visibility: Visibility = Visibilities.Visible,
    onChange: Changer<List<RawFile>>? = null,
    factory: ValidationFactory<List<RawFile>>? = null
): ListField<RawFile> = list(name, label, visibility, onChange, factory)

fun FileField(
    name: String,
    label: String = name,
    hint: String = label,
    visibility: Visibility = Visibilities.Visible,
    onChange: Changer<RawFile>? = null,
    factory: ValidationFactory<RawFile>? = null
): BaseField<RawFile> = GenericBaseField(FieldBacker.Name(name), null, label, visibility, hint, onChange, factory)

fun Fields<*>.file(
    name: KMutableProperty0<RawFile?>,
    label: String = name.name,
    hint: String = label,
    visibility: Visibility = Visibilities.Visible,
    onChange: Changer<RawFile>? = null,
    factory: ValidationFactory<RawFile>? = null
): BaseField<RawFile> = getOrCreate(name) {
    GenericBaseField(FieldBacker.Prop(name), null, label, visibility, hint, onChange, factory)
}