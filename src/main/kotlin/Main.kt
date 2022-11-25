import java.util.regex.Pattern
import kotlin.random.Random
import kotlin.reflect.KProperty

fun main(args: Array<String>) {

}

val intlist = listOf<Int>(16,21,28)
class TestUser() {
    val name by Rand(intlist)
}
class Rand<V>(private vararg val list: V) {
    operator fun <T> getValue(thisRef: T, property: KProperty<*>): V {
        return list[Random.nextInt(0,list.size)]
    }
}




sealed class City2(val id: Int) {
    class Donetsk: City2(1)
    class Moscow: City2(2)
    class Lissabon(val favor: Boolean): City2(3)
}
sealed class MetaTag (private val pattern: String) {
    class Title: MetaTag("<title.*?>(.*?)</title>")
    class Description: MetaTag("<meta name=\"description\" content=(.*?)/>")

    fun get(data: String): String {
        val res = Pattern.compile(pattern).matcher(data)
        return if (res.find()) res.group(1) else "${this::class.simpleName} not found"
    }
}

fun countC(text: String?) {
    text?.let {
        println(it.length)
    } ?: println("null!!!")
}

fun countC2(text: String?) {
    println(text?.length ?: "NULL")
}


