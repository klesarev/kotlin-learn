

fun main(){
    val digits = listOf<Int>(1,2,6,2,14,156,14,132,156)
    println(repeatElementsList(digits))
    println(repeatElementsListTwo(digits))
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
