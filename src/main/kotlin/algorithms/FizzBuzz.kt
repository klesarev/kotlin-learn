import java.io.Serializable
import java.util.stream.IntStream

fun main() {

    // решение через цикл
    fizzBuzz()

    // решение через when
    (1..100).map {
        println( fizzBuzzWhen(it))
    }

    // решение через потоки - Java IntStream
    fizzBuzzJava()

}

fun fizzBuzz() {
    for (n in 1..100){
        if(n % 15 == 0) {
            println("Fizz Buzz = $n")
            continue
        } else if (n % 5 == 0) {
            println("Buzz = $n")
            continue
        } else if (n % 3 == 0) {
            println("Fizz = $n")
            continue
        }
        println("$n")
    }
}

fun fizzBuzzWhen(n: Int) = when {
        n % 15 == 0 -> "FizzBuzz"
        n % 5 == 0 -> "Buzz"
        n % 3 ==0 -> "Fizz"
        else -> n
    }

fun fizzBuzzJava() = IntStream.rangeClosed(1, 100)
    .mapToObj { n: Int -> if (n % 3 == 0)
        if (n % 15 == 0) "Fizz Buzz = $n"
        else "Fizz = $n"
    else if (n % 5 == 0) "Buzz = $n"
    else n
    }
    .forEach { x: Serializable? -> println(x) }