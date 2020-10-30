package com.punkstudio.kotlinalgorithmas

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * Date:2020/10/28-9:46 AM
 * @author Mason
 */

fun main(str: Array<String>) {
//    println(uniqueOccurrences(intArrayOf(1,2)))
    println(islandPerimeter(arrayOf(
        intArrayOf(0,1,0,0),
        intArrayOf(1,1,1,0),
        intArrayOf(0,1,0,0),
        intArrayOf(1,1,0,0))))
}

// todo 1365. 有多少小于当前数字的数字
//给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
//换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
//以数组形式返回答案。
//示例 1：
//输入：nums = [8,1,2,2,3]
//输出：[4,0,1,1,3]
//解释：
//对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
//对于 nums[1]=1 不存在比它小的数字。
//对于 nums[2]=2 存在一个比它小的数字：（1）。
//对于 nums[3]=2 存在一个比它小的数字：（1）。
//对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
//示例 2：
//输入：nums = [6,5,4,8]
//输出：[2,1,0,3]
//示例 3：
//输入：nums = [7,7,7,7]
//输出：[0,0,0,0]
//提示：
//2 <= nums.length <= 500
//0 <= nums[i] <= 100
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
    val n = nums.size
    val data = Array(n) {Array(2) {0} }
    // 记录数组的原始坐标及对应的值
    for (i in 0 until n) {
        data[i][0] = nums[i]
        data[i][1] = i
    }
    // 讲数组排序
    Arrays.sort(data) { data1, data2 ->
        data1[0] - data2[0]
    }
    val ret = Array(n) { 0 }
    var prev = -1;
    for (i in 0 until n) {
        // 找到第一个不等于自己（因为已经排过序了，所以相当于找到第一个比自己小的数，记录下标就是此数组元素比自己小的个数）
        if (prev == -1 || data[i][0] != data[i - 1][0]) {
            prev = i
        }
        // ret的记录要与原始坐标对应
        ret[data[i][1]] = prev
    }
    return ret.toIntArray()
}

// todo 1207. 独一无二的出现次数
//给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
//如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
//示例 1：
//输入：arr = [1,2,2,1,1,3]
//输出：true
//解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
//示例 2：
//输入：arr = [1,2]
//输出：false
//示例 3：
//输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
//输出：true
//提示：
//1 <= arr.length <= 1000
//-1000 <= arr[i] <= 1000
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
fun uniqueOccurrences(arr: IntArray): Boolean {
    val containerArray = HashMap<Int, Int>(arr.size)
    for (v in arr) {
        val count = containerArray[v] ?: 0
        containerArray[v] = count + 1
    }
    val set = HashSet<Int>()
    containerArray.map { set.add(it.value) }
    return containerArray.size == set.size
}

// todo 463. 岛屿的周长
//给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
//网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
//岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
//示例 :
//输入:
//[[0,1,0,0],
//[1,1,1,0],
//[0,1,0,0],
//[1,1,0,0]]
//输出: 16
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/island-perimeter
fun islandPerimeter(grid: Array<IntArray>): Int {
    if (grid.isEmpty()) {
        return 0
    }
    var res = 0
    val column = grid.size
    val row = grid[0].size
    for (i in 0 until column) {
        for (j in 0 until row) {
            if (grid[i][j] == 1) {
                if (i == 0 || grid[i - 1][j] == 0) res++
                if (i == (column - 1) || grid[i + 1][j] == 0) res++
                if (j == 0 || grid[i][j - 1] == 0) res++
                if (j == (row - 1) || grid[i][j + 1] == 0) res++
            }
        }
    }
    return res
}
