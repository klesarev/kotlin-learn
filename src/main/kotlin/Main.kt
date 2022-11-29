import java.util.regex.Pattern
import javax.xml.crypto.Data
import kotlin.random.Random
import kotlin.reflect.KProperty
import kotlin.test.assertEquals

fun main(args: Array<String>) {
    val str = "keep calm and coding"
    println(
        Demo().classname()
    )
}
// тип Nothing вместо null моодет вернуть тип ошибки

// Если же мы отметим класс ключевым словом data, метод equals()
// будет переопределён автоматически. При этом работать будет точно также,
// как и в примере выше: будет проверять на равенство
// все значения, указанные в основном конструкторе.

class Demo():User(), Classable {
    override fun getName(): String {
        return this::class.java.simpleName
    }

    override fun classname(): Any {
        return this::class.java.simpleName
    }
}
abstract class User (var tag: String="") {
    abstract fun getName(): String

    init {
        tag = "new tag"
    }
}

interface Classable {
    fun classname(): Any
}

fun String.reverse(): String {
    return this.mapIndexed { index, _ ->
        this[(this.length - 1) - index]
    }.joinToString("")
}
fun strReverse(text: String): String {
    val size = text.length - 1

    return text.mapIndexed { index, _ ->
        text[size - index]
    }.joinToString("")
}

fun fizzbuzz(n: Int): Any = when {
    n % 15 == 0 -> "FizzBuzz"
    n % 3 == 0 -> "Fizz"
    n % 5 == 0 -> "Buzz"
    else -> n
}

fun fib(n: Int) = generateSequence(Pair(0,1)) {
    Pair(it.second, it.first + it.second)
}.take(n).toList().last().first

