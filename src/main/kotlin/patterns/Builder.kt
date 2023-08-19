package patterns


data class Configuration (
    val driver: String,
    val headless: Boolean,
    val loadTime: Int
)

class ConfigurationBuilder {

    // default params
    private var driver = "Chrome"
    private var headless = false
    private var loadTime = 4_000

    fun driver(driver: String): ConfigurationBuilder {
        this.driver = driver
        return this
    }

    fun headless(headless: Boolean): ConfigurationBuilder {
        this.headless = headless
        return this
    }

    fun loadTime(loadTime: Int): ConfigurationBuilder {
        this.loadTime = loadTime
        return this
    }

    fun build(): Configuration {
        return Configuration(
            driver, headless, loadTime
        )
    }
}