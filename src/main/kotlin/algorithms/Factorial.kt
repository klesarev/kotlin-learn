package algorithms

/**
 * Вычисление факториала числа различными способами
 *
 * @author Mr.Fox
 *
 * @sample factorialSequence -> вычисление факториала через sequence
 * @return Int -> факториал числа
 *
 * @sample factorialClassic -> вычисляем факториал обычным способом
 * @return Int -> факториал числа
 */

fun factorialSequence(num: Int) = sequence<Int> {
    var sum = 1
    var result = 1
    repeat (num) {
        result *= sum
        sum += 1
        yield(result)
    }
}.take(num).last()

fun factorialClassic(n: Int): Int {

    // обработаем исключительные случаи
    if (n == 0 || n == 1) return 1

    var fact = 1

    for (x in 1..n) {
        fact *= x
    }

    return fact
}