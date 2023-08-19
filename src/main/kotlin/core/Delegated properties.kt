package core

import kotlin.random.Random
import kotlin.reflect.KProperty

fun main() {

}

class TestUser(val id: String ="delegate") {
    val age by Rand(16,23,45,true)
    val name by Rand("Sara", "Demi", "Julia")
}

class Rand<V>(private vararg val list: V) {
    operator fun <T> getValue(thisRef: T, property: KProperty<*>): V {
        return list[Random.nextInt(0,list.size)]
    }
}