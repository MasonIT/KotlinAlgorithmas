package com.punkstudio.kotlinalgorithmas

import java.util.*
import kotlin.collections.ArrayList

/**
 * Date:2020/11/17-9:50 上午
 * @author Mason
 */


fun main(args: Array<String>) {
    println(moveZeroes(intArrayOf(0, 1, 0 ,3, 12)))
}
// todo 1030. 距离顺序排列矩阵单元格
//给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
//另外，我们在该矩阵中给出了一个坐标为(r0, c0) 的单元格。
//返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
//示例 1：
//输入：R = 1, C = 2, r0 = 0, c0 = 0
//输出：[[0,0],[0,1]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
//示例 2：
//输入：R = 2, C = 2, r0 = 0, c0 = 1
//输出：[[0,1],[0,0],[1,1],[1,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
//[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
//示例 3：
//输入：R = 2, C = 3, r0 = 1, c0 = 2
//输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
//其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
//提示：
//1 <= R <= 100
//1 <= C <= 100
//0 <= r0 < R
//0 <= c0 < C
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
fun allCellsDistOrder(R: Int, C: Int, r0: Int, c0: Int): Array<IntArray> {
    val matrixArray = ArrayList<IntArray>()
    for (r in 0 until R) {
        for (c in 0 until C) {
            matrixArray.add(intArrayOf(r, c))
        }
    }
    matrixArray.sortBy { (Math.abs(r0 - it[0]) + Math.abs(c0 - it[1])) }
    return matrixArray.toTypedArray()
}

// todo 283. 移动零
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//示例:
//输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//说明:
//必须在原数组上操作，不能拷贝额外的数组。
//尽量减少操作次数。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/move-zeroes
fun moveZeroes(nums: IntArray): Unit {
    var left = 0
    var right = 0
    val len = nums.size
    while (right < len) {
        if (nums[right] != 0) {
            swap(nums, left, right)
            left ++
        }
        right ++
    }
}