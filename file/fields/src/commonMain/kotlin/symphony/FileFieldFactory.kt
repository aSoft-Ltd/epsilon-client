package symphony

import epsilon.FileBlob
import kollections.List
import kollections.MutableList
import neat.ValidationFactory
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0
import epsilon.file as eFile
import epsilon.files as eFiles

@Deprecated(
    message = "use epsilon.files instead",
    replaceWith = ReplaceWith("files(name,label,visibility,onChange,factory)", "epsilon.files")
)
fun Fields<*>.files(
    name: KProperty0<MutableList<FileBlob>>,
    label: String = name.name,
    visibility: Visibility = Visibility.Visible,
    onChange: Changer<List<FileBlob>>? = null,
    factory: ValidationFactory<List<FileBlob>>? = null
): ListField<FileBlob> = eFiles(name, label, visibility, onChange, factory)

@Deprecated(
    message = "use epsilon.files instead",
    replaceWith = ReplaceWith("files(name,label,visibility,onChange,factory)", "epsilon.files")
)
fun Fields<*>.file(
    name: KMutableProperty0<FileBlob?>,
    label: String = name.name,
    hint: String = label,
    visibility: Visibility = Visibility.Visible,
    onChange: Changer<FileBlob>? = null,
    factory: ValidationFactory<FileBlob>? = null
): BaseField<FileBlob> = eFile(name, label, hint, visibility, onChange, factory)