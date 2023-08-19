package algorithms

/**
 * Реверс (переворот строки). Сложность O(n)
 *
 * @author Mr.Fox
 *
 * @sample stringReverse -> переворот строки через map
 * @return String -> перевернутая строка
 *
 * @sample reverseString -> классический переворот строки через two pointers
 * @param s -> массив символов
 * @return Unit -> ничего не возвращает, просто переворачивает массив символов
 */

fun stringReverse(text: String): String {
    val size = text.length - 1

    return text.mapIndexed { index, _ ->
        text[size - index]
    }.joinToString("")
}

fun reverseString(s: CharArray): Unit {
    var left = 0
    var right = s.size - 1
    var buffer: Char

    while (left <= right) {
        buffer = s[left]
        s[left] = s[right]
        s[right] = buffer
        left++
        right--
    }
}