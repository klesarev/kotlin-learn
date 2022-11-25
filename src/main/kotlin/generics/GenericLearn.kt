package generics

import android.fibonacci
import kotlin.random.Random
import kotlin.reflect.KProperty

fun main(){
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987,
   println(
       fibonacci(10).take(10).toList()
   )
}





val factorial = generateSequence(1) {
    it * (it + 1)
}

val numbers = listOf<Int>(16,21,28)
val names = listOf<String>("Sara", "Demi", "Julia")

interface Fullname {
    fun getname(): String
}


class TestUser: Fullname {
    val age by Rand(numbers)
    val name by Rand(names)

    override fun getname() = "$name, is $age"
}

class Rand<V>(private val list: List<V>) {
    operator fun <T> getValue(thisRef: T, property: KProperty<*>): V {
        return list[Random.nextInt(0,list.size)]
    }
}

