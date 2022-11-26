import java.util.regex.Pattern
import kotlin.random.Random
import kotlin.reflect.KProperty

fun main(args: Array<String>) {
    println(
        fib().take(10).toList().last().second
    )
}

fun fizzbuzz(n: Int): Any = when {
    n % 15 == 0 -> "FizzBuzz"
    n % 3 == 0 -> "Fizz"
    n % 5 == 0 -> "Buzz"
    else -> n
}

fun fib() = generateSequence(Pair(0,1)) {
    Pair(it.second, it.first + it.second)
}

