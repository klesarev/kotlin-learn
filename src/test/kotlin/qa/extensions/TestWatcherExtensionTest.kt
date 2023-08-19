package qa.extensions

import io.qameta.allure.TmsLink
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertTrue

@ExtendWith(TestWatcherExtension::class)
class TestWatcherExtensionTest {

    @Test
    @TmsLink("914")
    fun `successful test`() = assertTrue(true)

    @Test
    @TmsLink("1499")
    fun `failed test`() = assertTrue(false)

    @Test
    @Disabled
    @TmsLink("1505")
    fun `disabled test`() = assertTrue(true)

}