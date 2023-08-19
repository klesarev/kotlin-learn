package oop.solid

import kotlin.properties.Delegates

fun main(vararg string: String) {
    val car = Car()
    car.start()
}
class Car {
    private val engine = Engine()
    fun start() {
        engine.start()
    }
}

class CarB(private val engine: Engine) {
    fun start() {
        engine.start()
    }
}

open class Engine {
    open fun start(){
        println("Wroom Wroom!")
    }
}

class CarC {
    private var engine by Delegates.notNull<Engine>()
    fun start() {
        engine.start()
    }
}

