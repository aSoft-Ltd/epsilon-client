package symphony

import epsilon.FileField
import kommander.expect
import kotlin.test.Test

class SingleFileFieldTest {

    @Test
    fun should_be_able_to_start_with_a_default_value() {
        val file = FileField(
            name = "test-file-field",
            value = "https://test.com/image"
        )
        file.state.watchEagerly { println(it) }
        expect(file.output?.url).toBe("https://test.com/image")
    }

    @Test
    fun should_be_able_to_set_a_raw_file() {
        val file = FileField(
            name = "test-file-field",
            value = "https://test.com/image"
        )
        file.state.watchEagerly { println(it) }
        file.set("https://test.com/flask")
        expect(file.output?.url).toBe("https://test.com/flask")
        expect(file.output?.updated).toBe(true)
    }
}