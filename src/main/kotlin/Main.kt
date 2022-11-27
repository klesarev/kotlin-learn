import java.util.regex.Pattern
import kotlin.random.Random
import kotlin.reflect.KProperty

fun main(args: Array<String>) {
    val str = "keep calm and coding"

    println(
        str.reverse()
    )

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

