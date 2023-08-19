package patterns

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.AbstractDriverOptions

fun main(){
    val db = DatabaseFactory.create(
        MySqlConfig(port = 4371, host = "192.168.1.1", params = mapOf(
            "name" to "SQL Base",
            "money" to CurrencyFactory.create(Country.Spain).code
        ))
    )

    println(db)

}

// Sealed class + Factory
interface Database {
    val config: DBConfig
}

data class MySql(override val config: MySqlConfig): Database
class MongoDB(override val config: MongoDBConfig): Database

sealed interface DBConfig {
    val params: Map<String, Any>
    val host: String get() = Default.host
    val port: Int get() = Default.port
    companion object Default {
        const val host: String = "8.8.8.8"
        const val port: Int = 8080
    }
}
data class MySqlConfig(
    override val host: String = DBConfig.host,
    override val port: Int = DBConfig.port,
    override val params: Map<String, Any>
): DBConfig
class MongoDBConfig (
    override val host: String = DBConfig.host,
    override val port: Int = DBConfig.port,
    override val params: Map<String, Any>
): DBConfig

object DatabaseFactory {
    fun create(config: DBConfig): Database {
        return when(config) {
            is MySqlConfig -> MySql(config)
            is MongoDBConfig -> MongoDB(config)
        }
    }
}



// Static Method
class StaticFactory private constructor(){
    companion object {
        fun getOption(tag: String, vararg opts: String = arrayOf("")): AbstractDriverOptions<*> {
            return when (tag.lowercase()) {
                "chrome" -> ChromeOptions().addArguments(opts.toList())
                "firefox" -> FirefoxOptions().addArguments(opts.toList())
                else -> error("incorrect select tag")
            }
        }
    }
}

// Classic Factory
interface ICurrency {
    val symbol: String get() = ""
    val code: String get() = ""
}

data class Euro(
    override val symbol: String = "€",
    override val code: String = "EUR"
): ICurrency

data class UnitedStatesDollar(
    override val symbol: String = "$",
    override val code: String = "USD"
): ICurrency
enum class Country {
    UnitedStates, Spain, UK, Greece
}

class CurrencyFactory private constructor(){
    companion object {
        fun create(country: Country): ICurrency {
            return when (country) {
                Country.Spain, Country.Greece -> Euro()
                Country.UnitedStates -> UnitedStatesDollar()
                else -> error("enter the currency code")
            }
        }
    }
}