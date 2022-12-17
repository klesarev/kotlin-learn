package patterns

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.AbstractDriverOptions

fun main(){
    val db = Database.MySql(MySqlConfig())
}

// Sealed class + Factory
abstract class DatabaseConfig()
class MySqlConfig(
    val port: Int = 8080,
    val schema: String = "sql",
) : DatabaseConfig()
class MongoDBConfig(
    val port: Int = 8080,
    val path: String = "/bin"
): DatabaseConfig()


sealed class Database(val config: DatabaseConfig) {
    class MySql(config: MySqlConfig): Database(config)
    class MongoDB(config: MongoDBConfig): Database(config)

    companion object {
        fun of(config: DatabaseConfig): Database {
            return when(config) {
                is MySqlConfig -> MySql(config)
                is MongoDBConfig -> MongoDB(config)
                else -> error("error")
            }
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