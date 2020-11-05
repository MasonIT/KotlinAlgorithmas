package com.punkstudio.kotlinalgorithmas

/**
 * Date:2020/11/3-9:53 AM
 * @author Mason
 */

fun main(args: Array<String>) {
//    println(validMountainArray(intArrayOf(0, 3, 2, 1)))
    println(
        insert(
            arrayOf(
                intArrayOf(1, 5), intArrayOf(6, 8)
            ), intArrayOf(0, 9)
        )
            .contentToString()
    )
}

// todo 941. 有效的山脉数组
//给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
//让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
//A.length >= 3
//在 0 < i < A.length - 1 条件下，存在 i 使得：
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

// todo 1299. 将每个元素替换为右侧最大元素
//给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
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


// todo 57. 插入区间
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

