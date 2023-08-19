package algorithms

import kotlin.math.floor

/**
 * Бинарный поиск. Временная сложность - O(logn)
 *
 * @author Mr.Fox
 *
 * @sample binarySearch -> бинарный поиск числа
 * @param [target] -> искомое число
 * @param [arr] -> отсортированный массив чисел
 * @return Boolean -> true если число найдено, false - если нет
 */

fun binarySearch(target: Int, arr: IntArray): Boolean {
    var low = 0
    var high = arr.size - 1

    // обработаем частный случай
    if (arr.size == 1 && arr[0] == target) return true

    while (low <= high) {
        val middle = floor((high + low).toDouble() / 2).toInt()

        when {
            arr[middle] == target -> return true
            arr[middle] > target -> high = middle - 1
            else -> low = middle + 1
        }
    }
    return false
}