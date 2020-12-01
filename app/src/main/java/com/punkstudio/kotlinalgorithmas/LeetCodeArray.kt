package com.punkstudio.kotlinalgorithmas

import java.util.*

/**
 * Date:2020/11/3-9:53 AM
 * @author Mason
 */

fun main(args: Array<String>) {
//    println(validMountainArray(intArrayOf(0, 3, 2, 1)))
//    println(
//        insert(
//            arrayOf(
//                intArrayOf(1, 5), intArrayOf(6, 8)
//            ), intArrayOf(0, 9)
//        )
//            .contentToString()
//    )

    println(
        canCompleteCircuit(
            intArrayOf(
                3, 3, 4
            ), intArrayOf(
                3, 4, 4
            )
        )
    )
}

// todo 941. 有效的山脉数组（简单）
//给定一个整数数组A，如果它是有效的山脉数组就返回true，否则返回 false。
//让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
//A.length >= 3
//在0 < i< A.length - 1条件下，存在i使得：
//A[0] < A[1] < ... A[i-1] < A[i]
//A[i] > A[i+1] > ... > A[A.length - 1]
//示例 1：
//输入：[2,1]
//输出：false
//示例 2：
//输入：[3,5,5]
//输出：false
//示例 3：
//输入：[0,3,2,1]
//输出：true
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-mountain-array
fun validMountainArray(A: IntArray): Boolean {
    val x = A.max() ?: return false
    val first = A.indexOfFirst { it == x }
    if (first == A.size - 1 || first == 0) return false

    for (i in 0 until first) {
        if (A[i] >= A[i + 1]) {
            return false
        }
    }
    for (i in first until A.size - 1) {
        if (A[i] <= A[i + 1]) {
            return false
        }
    }
    return true
}

// todo 1299. 将每个元素替换为右侧最大元素（简单）
//给你一个数组arr，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用-1 替换。
//完成所有替换操作后，请你返回这个数组。
//示例：
//输入：arr = [17,18,5,4,6,1]
//输出：[18,6,6,6,1,-1]
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/replace-elements-with-greatest-element-on-right-side
fun replaceElements(arr: IntArray): IntArray {
    val array = Array(arr.size) { 0 }
    for(i in 0 until (arr.size - 1)) {
        var max = arr[i]
        for (j in (i + 1) until arr.size) {
            if (max < arr[j]) {
                max = arr[j]
            }
        }
        array[i] = max
    }
    array[arr.size - 1] = -1
    return array.toIntArray()
}


// todo 57*. 插入区间（困难）
//给出一个无重叠的 ，按照区间起始端点排序的区间列表。
//在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
//示例 1：
//输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
//示例 2：
//输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    if (intervals.isEmpty()) {
        return Array(1) {
            newInterval
        }
    }
    val resultList = mutableListOf<IntArray>()
    if (intervals.last()[1] < newInterval[0]) {
        for (interval in intervals) {
            resultList.add(interval)
        }
        resultList.add(newInterval)
        return resultList.toTypedArray()
    } else if (intervals.first()[0] > newInterval[1]) {
        resultList.add(newInterval)
        for (interval in intervals) {
            resultList.add(interval)
        }
        return resultList.toTypedArray()
    }
    var indexLeft = 0
    var indexRight = 0
    for ((i, v) in intervals.withIndex()) {
        if (v[0] <= newInterval[0] && newInterval[0] <= v[1]) {
            indexLeft = i
        }
        if (v[0] <= newInterval[1] && newInterval[1] <= v[1]) {
            indexRight = i
        }
    }
    val shouldArea = intArrayOf(
        Math.min(intervals[indexLeft][0], newInterval[0]),
        Math.max(intervals[indexRight][1], newInterval[1])
    )
    val indexList = mutableListOf<Int>()
    if (indexLeft == indexRight) {
        resultList.add(indexLeft, shouldArea)
        return resultList.toTypedArray()
    }
    for (i in indexLeft .. indexRight) {
            indexList.add(i)
    }
    for ((i, v) in intervals.withIndex()) {
        if (!indexList.contains(i)) {
            resultList.add(v)
        }
    }
    resultList.add(indexLeft, shouldArea)
    return resultList.toTypedArray()
}

fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
    var currentWorld = ""
    var step = 0
    val currentWorldList = mutableListOf<List<String>>()

    return if (currentWorld == endWord) {
        step
    } else {
        0
    }
}

// todo 973. 最接近原点的 K 个点（中等）
//我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
//（这里，平面上两点之间的距离是欧几里德距离。）
//你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
//示例 1：
//输入：points = [[1,3],[-2,2]], K = 1
//输出：[[-2,2]]
//解释：
//(1, 3) 和原点之间的距离为 sqrt(10)，
//(-2, 2) 和原点之间的距离为 sqrt(8)，
//由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
//我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
//示例 2：
//输入：points = [[3,3],[5,-1],[-2,4]], K = 2
//输出：[[3,3],[-2,4]]
//（答案 [[-2,4],[3,3]] 也会被接受。）
//提示：
//1 <= K <= points.length <= 10000
//-10000 < points[i][0] < 10000
//-10000 < points[i][1] < 10000
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
//    val map = HashMap<IntArray, Int>()
//    points.map {
//        map.put(it, it[0] * it[0] + it[1] * it[1])
//    }
//    val list = map.toList().sortedBy {
//        it.second
//    }
//    val array = arrayListOf<IntArray>()
//    for (i in 0 until K) {
//        array.add(list[i].first)
//    }
//    return array.toTypedArray()
    points.sortWith(Comparator { o1, o2 -> if (o1[0] * o1[0] + o1[1] * o1[1] < o2[0] * o2[0] + o2[1] * o2[1]) 1 else -1 })
    return points.copyOfRange(0, K)
}

// todo 31. 下一个排列（中等）
//实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
//如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
//必须原地修改，只允许使用额外常数空间。
//以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
//1,2,3 → 1,3,2
//3,2,1 → 1,2,3
//1,1,5 → 1,5,1
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/next-permutation
fun nextPermutation(nums: IntArray): Unit {
    var i = nums.size - 2
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        -- i
    }
    if (i >= 0) {
        var j = nums.size - 1
        while (j >= 0 && nums[i] >= nums[j]) {
            -- j
        }
        swap(nums, i, j)
    }
    reverse(nums, i + 1)
}

fun swap(nums: IntArray, index1: Int, index2: Int) {
    val temp = nums[index1]
    nums[index1] = nums[index2]
    nums[index2] = temp
}

fun reverse(nums: IntArray, start: Int) {
    var left = start
    var right = nums.size - 1
    while (left < right) {
        swap(nums, left, right)
        left ++
        right --
    }
}

// todo 922. 按奇偶排序数组 II（简单）
//给定一个非负整数数组A， A 中一半整数是奇数，一半整数是偶数。
//对数组进行排序，以便当A[i] 为奇数时，i也是奇数；当A[i]为偶数时， i 也是偶数。
//你可以返回任何满足上述条件的数组作为答案。
//示例：
//输入：[4,2,5,7]
//输出：[4,5,2,7]
//解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
fun sortArrayByParityII(A: IntArray): IntArray {
    val length = A.size
    val array = Array(length) { 0 }
    val evenArray = mutableListOf<Int>()
    val oodArray = mutableListOf<Int>()
//    for (a in A) {
//        if (a % 2 == 0) {
//            evenArray.add(a)
//        } else {
//            oodArray.add(a)
//        }
//    }
//
//    for(i in 0 until length) {
//        if (i % 2 == 0) {
//            array[i] = evenArray[i / 2]
//        } else {
//            array[i] = oodArray[i / 2]
//        }
//    }
    val array1 = A
    for ((i, a) in array1.withIndex()) {
        if (i % 2 == 0) {
            if (a % 2 != 0) {
                if (evenArray.isEmpty()) {
                    oodArray.add(i)
                } else {
                    swap(array1, i, evenArray[0])
                    evenArray.removeAt(0)
                }
            }
        } else {
            if (a % 2 == 0) {
                if (oodArray.isEmpty()) {
                    evenArray.add(i)
                } else {
                    swap(array1, i, oodArray[0])
                    oodArray.removeAt(0)
                }
            }
        }
    }
    return array.toIntArray()
}

// todo 134. 加油站
//在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。
//你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
//如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
//说明:
//如果题目有解，该答案即为唯一答案。
//输入数组均为非空数组，且长度相同。
//输入数组中的元素均为非负数。
//示例1:
//输入:
//gas  = [1,2,3,4,5]
//cost = [3,4,5,1,2]
//输出: 3
//解释:
//从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
//开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
//开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
//开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
//开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
//开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
//因此，3 可为起始索引。
//示例 2:
//输入:
//gas  = [2,3,4]
//cost = [3,4,3]
//输出: -1
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/gas-station
fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
    val len = gas.size
    for ((i, v) in gas.withIndex()) {
        var sum = v
        var count = 0
        for (j in 0 until len) {
            val index = (i + j) % len
            if (sum - cost[index] < 0) {
                count = 0
                break
            } else {
                sum = sum - cost[index] + gas[(j + i + 1) % len]
                ++ count
            }
        }
        if (count == len) {
            return i
        }
    }
    return -1
}

// todo 34. 在排序数组中查找元素的第一个和最后一个位置（中等）
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//如果数组中不存在目标值 target，返回[-1, -1]。
//进阶：
//你可以设计并实现时间复杂度为O(log n) 的算法解决此问题吗？
//示例 1：
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//示例2：
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//示例 3：
//输入：nums = [], target = 0
//输出：[-1,-1]
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
fun searchRange(nums: IntArray, target: Int): IntArray {
    if (nums.isEmpty()) {
        return intArrayOf(-1, -1)
    }
    var low = 0
    var high = nums.size - 1
    var start = -1
    var end = -1
    for (i in nums.indices) {
        if (start == -1 && nums[low] == target) {
            start = low
        } else {
            low ++
        }
        if (end == -1 && nums[high] == target) {
            end = high
        } else {
            high --
        }
    }
    return intArrayOf(start, end)
}

//fun searchRange(nums: IntArray, target: Int): IntArray? {
//    val leftIdx = binarySearch(nums, target, true)
//    val rightIdx = binarySearch(nums, target, false) - 1
//    return if (leftIdx <= rightIdx && rightIdx < nums.size && nums[leftIdx] == target && nums[rightIdx] == target) {
//        intArrayOf(leftIdx, rightIdx)
//    } else intArrayOf(-1, -1)
//}
//
//fun binarySearch(nums: IntArray, target: Int, lower: Boolean): Int {
//    var left = 0
//    var right = nums.size - 1
//    var ans = nums.size
//    while (left <= right) {
//        val mid = (left + right) / 2
//        if (nums[mid] > target || lower && nums[mid] >= target) {
//            right = mid - 1
//            ans = mid
//        } else {
//            left = mid + 1
//        }
//    }
//    return ans
//}
