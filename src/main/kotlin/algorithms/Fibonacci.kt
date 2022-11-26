package android

import kotlin.math.sqrt

fun main(){
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
    val digit = 10
    run {
        println("Fibonacci - Sequence($digit) is ${fibonacci(digit).toList().last()}")
        println("Fibonacci - Simple($digit) is ${fibSimple(digit)}")
        println("Fibonacci - Array($digit) is ${fibArray(digit)}")
    }
}

fun fibonacci(num: Int = 10): Sequence<Int> {
    return generateSequence(Pair(0, 1)) {
        Pair(it.second, it.first + it.second)
    }.map { it.first }.take(num)
}

fun fibSimple(num: Int): Int {
    var start = Pair(0,1)
    val list = mutableListOf<Pair<Int, Int>>()
    list.add(start)
    repeat(num - 1) {
        start = Pair(start.second, start.first + start.second)
        list.add(start)
    }
    return list.last().first
}

fun fibArray(n: Int): Int {

    // массив с будующими числами
    // fb[0] - первый индекс, fb[1] - второй индекс
    val fb: IntArray = IntArray(n)
    fb[0] = 0
    fb[1] = 1

    // строим последовательность
    (2 until n).forEach { index->
        fb[index] = fb[index - 1] + fb[index - 2]
    }

    // возвращаем искомый элемент
    return fb[n-1]
}