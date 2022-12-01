package patterns

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import io.qameta.allure.Allure
import io.qameta.allure.Allure.ThrowableRunnableVoid
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import steps.step

class BuilderTest {

    @DisplayName("base builder test")
    @Test
    fun builderTest() {
        lateinit var config: Configuration
        step("create builder class") {
            config = ConfigurationBuilder().build()
        }

        Allure.step("Открыть главную страницу М.Видео", ThrowableRunnableVoid {
            Assertions.assertEquals("Chrome", config.driver)
            Assertions.assertEquals(false, config.headless)
            Assertions.assertEquals(4_500, config.loadTime)
        })

    }

    @DisplayName("custom filed set test")
    @Test
    fun builderCustomTest() {
        val conf = ConfigurationBuilder()
            .driver("Safari")
            .build()

        step("step from custom allure") {
            Assertions.assertEquals("Safari", conf.driver)
        }
        step("another 2") {
            Assertions.assertTrue(false)
        }
        step("another step") {
            Assertions.assertTrue(true)
        }
    }
}