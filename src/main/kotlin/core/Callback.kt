package core

fun main () {
    val result = callback("Some string") {
       it.reversed()
    }
    println(result)

    callback("Other text") {
        it.toCharArray().toList().forEach { char->
            print("$char = ")
        }
    }
}

inline fun<T,V> callback(data: T, block: (T) -> V?): V? {
    return block(data)
}