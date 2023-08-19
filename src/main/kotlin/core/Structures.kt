package core

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {

    val count = 20_000_000

    val linkedList = LinkedList<Int>()
    val arrayList = arrayListOf<Int>()
    val hashSet = hashMapOf<Int,Int>()

    for (index in 0..count) {
        linkedList.add(index)
        arrayList.add(index)
        hashSet.put(index, index)
    }



    val timeLinked = measureTimeMillis {
        linkedList.get(count/2)
        //linkedList.remove(0)
        //linkedList.get(0)
    }

    val timeArray = measureTimeMillis {
        arrayList.get(count/2)
        //arrayList.remove(0)
        //arrayList.get(0)
    }

    val timeHash = measureTimeMillis {
        hashSet.get(count/2)
        //hashSet.remove(0)
        //hashSet.get(0)
    }

    println(
        "LinkedList Time: $timeLinked ms \n" +
        "ArrayList Time: $timeArray ms \n" +
        "HashSet Time: $timeHash ms"
    )
}
