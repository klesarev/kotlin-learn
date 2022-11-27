

fun main() {
    val digits = listOf<Int>(1,2,3,6,2,14,156,14)
    println(repeatElementsList(digits))
    println(repeatElementsListTwo(digits))

    val names = mutableListOf<String>("Jane", "Mary", "Violetta")
        .map { string ->
            "Name: $string = ${string.count()}"
        }

    val str = "aabbbccddddeefffff"

    val result = str.groupingBy { it }.eachCount()
        .toList()
        .map { "${it.first}${it.second}" }
        .joinToString("")
    println(result)


    // count возвращает размер
    val dig = digits.groupBy { it }
        .filter { it.value.count() > 1 }
        .map { it.key }
    println(dig)



}

fun <T> repeatElementsList(list: List<T>): Set<T> {
    val buffer = mutableSetOf<T>()

    return list.filter {element->
        !buffer.add(element)
    }.toSet()
}

fun repeatElementsListTwo(list: List<Int>): List<Int> {
    return list.filter { element ->
            list.count{ it == element } > 1
        }.distinct()
}