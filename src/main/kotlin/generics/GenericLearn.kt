package generics

import android.fibonacci
import kotlin.random.Random
import kotlin.reflect.KProperty

fun main(){

}

val numbers = listOf<Int>(16,21,28)
val names = listOf<String>("Sara", "Demi", "Julia")

class TestUser {
    val age by Rand(numbers)
    val name by Rand(names)
    val fullname: String
        get() = "$name, is $age"
}

class Rand<V>(private val list: List<V>) {
    operator fun <T> getValue(thisRef: T, property: KProperty<*>): V {
        return list[Random.nextInt(0,list.size)]
    }
}

