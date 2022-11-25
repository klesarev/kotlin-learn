package patterns

import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path
import java.util.*


fun main(){
}

object Singleton {
    private var props: Properties = Properties()
    private val appConfig = "application.properties"

    fun getProperty(name: String): String {
        return let { props.getProperty(name) } ?: ""
    }
    @Deprecated("Properties files should be for read")
    fun setProperty(name: String, value: String) {
        val path = Path.of("src/main/resources/application.properties")
        val stream = Files.newOutputStream(path)
        props.setProperty(name, value)
        try {
            props.store(stream,null)
        } catch (e: Exception) {
            println(e.stackTrace)
        } finally {
            stream.close()
        }
    }

    init {
        props.load(Singleton::class.java.classLoader.getResourceAsStream(appConfig))
    }
}