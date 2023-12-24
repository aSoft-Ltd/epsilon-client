package symphony

import epsilon.FileBlob
import koncurrent.Executor
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch

data class FakeFileBlob(
    override val path: String = "file://fake/test.fake",
    override val name: String = "test.fake"
) : FileBlob {
    override fun readBytes(executor: Executor) = Later(path.encodeToByteArray())
}