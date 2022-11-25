package patterns

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class BuilderTest {

    @DisplayName("base builder test")
    @Test
    fun builderTest() {
        val config = ConfigurationBuilder().build()

        Assertions.assertEquals("Chrome", config.driver)
        Assertions.assertEquals(false, config.headless)
        Assertions.assertEquals(4_000, config.loadTime)
        Assertions.assertInstanceOf(Configuration::class.java, config)
    }

    @DisplayName("custom filed set test")
    @Test
    fun builderCustomTest() {
        val conf = ConfigurationBuilder()
            .driver("Safari")
            .build()
        Assertions.assertEquals("Safari", conf.driver)
    }
}