package qa.extensions

import io.qameta.allure.TmsLink
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestRailExtension::class)
class CallbackExtensionTest {

    @Test
    @TmsLink("1595")
    fun `demo test one`() = Assertions.assertTrue(false)

    @Test
    @TmsLink("1598")
    fun `demo test two`() = Assertions.assertTrue(true)

}