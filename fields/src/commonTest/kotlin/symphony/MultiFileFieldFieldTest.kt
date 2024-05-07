package symphony

import epsilon.FilesField
import kollections.contains
import kollections.listOf
import kollections.map
import kommander.expect
import kotlin.test.Test

class MultiFileFieldFieldTest {

    @Test
    fun should_be_able_to_start_with_a_default_value() {
        val file = FilesField(
            name = "test-file-field",
            value = listOf("https://test.com/image")
        )
        expect(file.output.map { it.url }.contains("https://test.com/image")).toBe(true)
    }

    @Test
    fun should_be_able_to_add_a_url() {
        val file = FilesField(
            name = "test-file-field",
        )
        file.addUrl("https://test.com/image")
        expect(file.output.map { it.url }.contains("https://test.com/image")).toBe(true)
    }
}