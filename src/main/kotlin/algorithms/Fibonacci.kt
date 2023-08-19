package algorithms

/**
 * Вычисление Чисел Фибоначчи
 *
 * @author Mr.Fox
 *
 * @sample fibonacciSequence -> вычисление Чисел Фибоначчи через sequence
 * @return Int -> последовательность Чисел Фибоначчи до n-числа
 *
 * @sample fibonacciArray -> вычисляем факториал обычным способом
 * @return Int -> последовательность Чисел Фибоначчи до n-числа
 */

fun fibonacciSequence(num: Int = 10): Sequence<Int> {
    return generateSequence(Pair(0, 1)) {
        Pair(it.second, it.first + it.second)
    }.map { it.first }.take(num)
}

fun fibonacciArray(n: Int): IntArray {

    // обработаем исключительные ситуации
    when(n){
        0 -> return intArrayOf(0)
        1 -> return intArrayOf(0,1)
    }

    // fb[0] - первый индекс, fb[1] - второй индекс
    val fbArray = IntArray(n)
    fbArray[0] = 0
    fbArray[1] = 1

    // строим последовательность
    (2 until n).forEach { index->
        fbArray[index] = fbArray[index - 1] + fbArray[index - 2]
    }

    return fbArray
}
