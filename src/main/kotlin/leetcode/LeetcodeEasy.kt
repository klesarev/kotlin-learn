package leetcode

/**
 * Решение задач с сайта Leetcode.com
 * **See Also:** [Leetcode](https://leetcode.com/problemset/all/?difficulty=EASY)
 *
 * @suppress Easy уровень
 *
 * @author Mr.Fox
 */

/**
 * 1. Two Sum
 *
 * @info Bruteforce method. Простое решение перебором.
 * @suppress Временная сложность O(n°2)
 * */
fun twoSum(nums: IntArray, target: Int): IntArray {

    for (x in 0 until nums.size) {
        for (y in x + 1 until nums.size) {
            if (nums[x] + nums[y] == target) {
                return intArrayOf(x,y)
                break
            }
        }
    }

    return intArrayOf()
}
/**
 * 1. Two Sum
 *
 * @info Решение через HashMap
 * @suppress Временная сложность O(n)
 * */
fun twoSum2(nums: IntArray, target: Int): IntArray {
    if(nums.size < 2) return intArrayOf()

    val hashMap = hashMapOf<Int, Int>()

    for(n in nums.indices) {
        val current = target - nums[n]

        if(hashMap.containsKey(current)) {
            return intArrayOf(n, hashMap[current] as Int)
        }

        hashMap[nums[n]] = n
    }
    return intArrayOf()
}

/**
 * 9. Palindrome Number
 *
 * @info Решение через деление с остатком
 * @suppress Временная сложность O(n)
 * */
fun isPalindrome(x: Int): Boolean {
    var num = x
    var reverseNum = 0

    while(num > 0) {
        reverseNum = reverseNum * 10 + num % 10
        num /= 10
    }
    return x == reverseNum
}

/**
 * 58. Length of Last Word
 *
 * @info Решение через RegExp
 * @suppress Временная сложность O(n)
 * */
fun lengthOfLastWord(s: String): Int {
    val str = s.split("\\s+".toRegex())
    return str.last { it.isNotEmpty() }.length
}

/**
 * 125. Valid Palindrome
 *
 * @info Решение через two pointers
 * @suppress Временная сложность O(n)
 * */
fun isPalindrome(s: String): Boolean {
    var start = 0
    var end = s.length - 1

    while (start < end) {
        while (start < end && !s[start].isLetterOrDigit()) start++
        while (end > start && !s[end].isLetterOrDigit()) end--
        if (Character.toLowerCase(s[start]) != Character.toLowerCase(s[end])) return false
        start++
        end--
    }

    return true
}

/**
 * 217. Contains Duplicate
 *
 * @info Решение через hashSet
 * @suppress Временная сложность в худшем случае O(n)
 * */
fun containsDuplicate(nums: IntArray): Boolean {
    val set = hashSetOf<Int>()
    loop@ for(element in nums) {
        if(!set.add(element)) {
            return true
            break@loop
        }
    }
    return false
}


/**
 * 268. Missing Number
 *
 * @info Решение через разницу сумм массивов
 * @suppress Временная сложность O(n)
 * */
fun missingNumber(nums: IntArray): Int {
    val n = nums.size
    return n * (n + 1) / 2 - nums.sum()
}

/**
 * 344. Reverse String
 *
 * @info Решение через two pointers
 * @suppress Временная сложность O(n)
 * */
fun reverseString(s: CharArray): Unit {
    var left = 0
    var right = s.size - 1
    var buffer: Char

    while (left <= right) {
        buffer = s[left]
        s[left] = s[right]
        s[right] = buffer
        left++
        right--
    }
}
/**
 * 412. Fizz Buzz
 *
 * @info Решение перебором
 * @suppress Временная сложность O(n)
 * */
fun fizzBuzz(n: Int): List<String> {
    val list = arrayListOf<String>()
    for(x in 1..n) {
        when{
            x % 15 == 0 -> list.add("FizzBuzz")
            x % 5 == 0 -> list.add("Buzz")
            x % 3 == 0 -> list.add("Fizz")
            else -> list.add(x.toString())
        }
    }
    return list.toList()
}

/**
 * 643. Maximum Average Subarray
 *
 * @info Решение перебором
 * @suppress Временная сложность O(n)
 * */
fun findMaxAverage(nums: IntArray, k: Int): Double {
    var max = 0
    for (i in 0 until k) max += nums[i]

    var temp = max
    for (i in k until nums.size) {
        temp = temp + nums[i] - nums[i - k]
        max = Math.max(temp, max)
    }

    return max * 1.0 / k
}

/**
 * 704. Binary Search
 *
 * @info Решение через two pointers
 * @suppress Временная сложность O(logn)
 * */
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1

    if (nums.size == 1 && nums[0] == target) return 0

    while (right >= left) {
        val mid = (left + right) / 2
        if (nums[mid] == target) {
            return mid
        } else if (nums[mid] > target) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    return -1
}