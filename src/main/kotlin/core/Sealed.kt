package core

import java.time.LocalDate

fun main(){
    println(Message.Image("img:/mnt/test.png"))
    val scheduled = Message.ScheduledMessage(
        LocalDate.of(2023, 2, 23),
        Message.Image("img:/febrary.png")
    )

    println(scheduled)

}

infix fun Any.prnt(r: Any) = when (r) {
    "\n" -> println(this)
    else -> print(this)
}

sealed class Message {
    data class Image(val imageUrl: String) : Message()
    data class ScheduledMessage(val date: LocalDate, val message: Message): Message()
    data class CombinedMessage(val message: List<Message>): Message()
    object ClearChart: Message()
    object DeleteContactInformation: Message()
}