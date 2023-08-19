package leetcode

/**
 * Решение задач с сайта Leetcode.com
 * **See Also:** [Leetcode](https://leetcode.com/problemset/all/?difficulty=MEDIUM)
 *
 * @suppress Medium уровень
 *
 * @author Mr.Fox
 */


/**
 * 53. Maximum Subarray
 *
 * @info Решение перебором.
 * @suppress Временная сложность O(n)
 * */
fun maxSubArray(nums: IntArray): Int {
    var maxSumOfSubArray = nums[0]

    var currentSum = 0

    for (number in nums) {
        currentSum = maxOf(number, currentSum + number)
        maxSumOfSubArray = maxOf(maxSumOfSubArray, currentSum)
    }

    return maxSumOfSubArray
}

/**
 * 167. Two Sum II - Input Array Is Sorted
 *
 * @info Решение через two pointers
 * @suppress Временная сложность O(n)
 * */
fun twoSum3(numbers: IntArray, target: Int): IntArray {
    var start = 0
    var end = numbers.size - 1

    while(start <= end) {
        val sum = numbers[start] + numbers[end]
        if (sum == target) return intArrayOf(start+1, end+1)
        if (sum < target) start++ else end--
    }

    return intArrayOf()
}


/**
 * 287. Find the Duplicate Number
 *
 * @info Решение через hashSet
 * @suppress Временная сложность O(n)
 * */
fun findDuplicate(nums: IntArray): Int {
    val list = hashSetOf<Int>()
    for(x in 0 until nums.size) {
        if(!list.add(nums[x])) return nums[x]
    }
    return 0
}