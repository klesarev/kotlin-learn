package patterns

import kotlin.random.Random

fun main() {
    val station = WeatherStation()

    // добавляем уже классы, которые реализуют интерфейс
    station.run {
        addClient(WeatherClient("Mary", "Jenkins"))
        addClient(WeatherClient("Penny", "Blossom"))
        addClient(WeatherClient("Ellen", "Mercier"))
    }
    station.updateWeather()
}

/*
 Почему добавляем именно интерфейс, потому что его могут реализовывать клиенты,
 которые по разному относятся к изменению погоды
 */

class WeatherStation {
    private var degrees = 0
    private val clients = arrayListOf<WeatherClientBehavior>()

    fun addClient(client: WeatherClientBehavior) {
        clients.add(client)
    }

    fun removeClient(client: WeatherClientBehavior) {
        clients.remove(client)
    }

    fun updateWeather(){
        degrees = Random.nextInt(0,35)
        clients.forEach {
            it.getWeather(degrees = degrees)
        }

        while(true) {
            Thread.sleep(4_000)
            updateWeather()
        }
    }
}

class WeatherClient(val name: String, val surname: String): WeatherClientBehavior {
    //private val TAG = WeatherClient::class.java

    override fun getWeather(degrees: Int) {
        when(degrees) {
            in 0..10 -> println("WINTER COMES!!!->> $name")
            in 11..25 -> println("Spring comes true! ->> $name")
            in 26..35 -> println("Summer! ->> $name")
        }
    }

}

interface WeatherClientBehavior {
    fun getWeather(degrees: Int)
}