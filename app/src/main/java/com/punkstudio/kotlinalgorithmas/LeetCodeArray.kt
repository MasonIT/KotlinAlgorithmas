package com.punkstudio.kotlinalgorithmas

/**
 * Date:2020/11/3-9:53 AM
 * @author Mason
 */

fun main(args: Array<String>) {
    println(validMountainArray(intArrayOf(0, 3, 2, 1)))
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