package com.punkstudio.kotlinalgorithmas

import java.util.*
import kotlin.Comparator
import kotlin.collections.HashMap

/**
 * Date:2020/11/6-1:53 PM
 * @author Mason
 */


// todo 1356. 根据数字二进制下 1 的数目排序（简单）
//给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
//如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
//请你返回排序后的数组。
//示例 1：
//输入：arr = [0,1,2,3,4,5,6,7,8]
//输出：[0,1,2,4,8,3,5,6,7]
//解释：[0] 是唯一一个有 0 个 1 的数。
//[1,2,4,8] 都有 1 个 1 。
//[3,5,6] 有 2 个 1 。
//[7] 有 3 个 1 。
//按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
//示例 2：
//输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
//输出：[1,2,4,8,16,32,64,128,256,512,1024]
//解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
//示例 3：
//输入：arr = [10000,10000]
//输出：[10000,10000]
//示例 4：
//输入：arr = [2,3,5,7,11,13,17,19]
//输出：[2,3,5,17,7,11,13,19]
//示例 5：
//输入：arr = [10,100,1000,10000]
//输出：[10,100,10000,1000]
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits
fun sortByBits(arr: IntArray) : IntArray {
    val map = HashMap<Int, Int>()
    for ((i, v) in arr.withIndex()) {
        var count = 0
        var m = v
        while (m != 0) {
            count += m % 2
            m = m shr 1
        }
        map[i] = count
    }
    val list = map.toList().sortedBy { it.second }.toMutableList()
    for (i in list.indices) {
        for (j in i + 1 until list.size) {
            if (list[i].second == list[j].second && arr[list[i].first] > arr[list[j].first]) {
                val temp = list[i]
                list[i] = list[j]
                list[j] = temp
            }
        }
    }
    val result = Array(list.size) { 0 }
    for ((index, pair) in list.withIndex()) {
        result[index] = arr[pair.first]
    }
    return result.toIntArray()
}
// 改进
fun sortByBitsUpdate(arr: IntArray) : IntArray {
    val bit = Array(10001) { 0 }
    val list = mutableListOf<Int>()
    for (v in arr) {
        list.add(v)
        var count = 0
        var m = v
        while (m != 0) {
            count += m % 2
            m = m shr 1
        }
        bit[v] = count
    }
    list.sortWith(Comparator { o1, o2 -> if (bit[o1] != bit[o2]) bit[o1] - bit[o2] else o1 - o2 })
    return list.toIntArray()
}

fun sortByBitsUpdate1(arr: IntArray) : IntArray {
    val map = Array(arr.size) { 0 }
    for ((i, v) in arr.withIndex()) {
        map[i] = Integer.bitCount(v) * 10000000 + v
    }
    Arrays.sort(map)
    for (i in map.indices) {
        map[i] = map[i] % 10000000
    }
    return map.toIntArray()
}