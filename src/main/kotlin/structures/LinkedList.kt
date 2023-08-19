package structures

fun main(){

    val head = ListNode(1, ListNode(1, ListNode(2)))

    val head2 = ListNode(null,null)

    var cursor = head
    var next: ListNode? = null

    while (cursor.next != null) {
        next = cursor
        if (cursor.value == cursor.next!!.value) {
            cursor.next = cursor.next!!.next
        } else cursor = cursor.next!!
    }

    println(head)

}

data class ListNode(
    var value: Int?,
    var next: ListNode? = null
)

fun reverseList(head: ListNode?): ListNode? {
    var curr: ListNode? = head
    var prev: ListNode? = null

    repeat(5) {

    }

    return  ListNode(77)
}