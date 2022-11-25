package android

fun main(){

}

fun fibonacci(num: Int = 10): Sequence<Int> {
    return generateSequence(Pair(0, 1)) {
        Pair(it.second, it.first + it.second)
    }.map { it.first }.take(num)
}

val fibSeq: (num: Int) -> Sequence<Pair<Int, Int>> = {
    generateSequence (Pair(0,1)) {
        Pair(it.second, it.first + it.second)
    }
}

fun fib(num: Int): Int {
    var start = Pair(0,1)
    val list = mutableListOf<Pair<Int, Int>>()
    list.add(start)
    repeat(num - 1) {
        start = Pair(start.second, start.first + start.second)
        list.add(start)
    }
    return list.last().first
}

