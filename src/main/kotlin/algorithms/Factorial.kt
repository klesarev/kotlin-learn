package algorithms

fun main() {

}


fun factorialTwo(num: Int) = sequence<Int> {
    var sum = 1
    var result = 1
    repeat (num) {
        result *= sum
        sum += 1
        yield(result)
    }
}