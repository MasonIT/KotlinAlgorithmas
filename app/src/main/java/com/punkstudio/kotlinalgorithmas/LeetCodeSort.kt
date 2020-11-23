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

// todo 147. 对链表进行插入排序
//对链表进行插入排序。
//插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
//每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
//插入排序算法：
//插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
//每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
//重复直到所有输入数据插入完为止。
//示例 1：
//输入: 4->2->1->3
//输出: 1->2->3->4
//示例2：
//输入: -1->5->3->4->0
//输出: -1->0->3->4->5
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/insertion-sort-list
fun insertionSortList(head: ListNode?): ListNode? {
    if (head == null) {
        return head
    }
    val dummyHead = ListNode(0)
    dummyHead.next = head
    var lastSorted = head
    var curr = head.next
    while (curr != null) {
        if (lastSorted!!.`val` <= curr.`val`) {
            lastSorted = lastSorted.next
        } else {
            var pre = dummyHead
            while (pre.next!!.`val` <= curr.`val`) {
                pre = pre.next!!
            }
            lastSorted.next = curr.next
            curr.next = pre.next
            pre.next = curr
        }
        curr = lastSorted!!.next
    }
    return dummyHead.next
}

// todo 452*. 用最少数量的箭引爆气球（中等）
//在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
//一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
//给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
//示例 1：
//输入：points = [[10,16],[2,8],[1,6],[7,12]]
//输出：2
//解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
//示例 2：
//输入：points = [[1,2],[3,4],[5,6],[7,8]]
//输出：4
//示例 3：
//输入：points = [[1,2],[2,3],[3,4],[4,5]]
//输出：2
//示例 4：
//输入：points = [[1,2]]
//输出：1
//示例 5：
//输入：points = [[2,3],[2,3]]
//输出：1
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
fun findMinArrowShots(points: Array<IntArray>): Int {
    if (points.isEmpty()) {
        return 0
    }
    Arrays.sort(points) { o1, o2 ->
        when {
            o1!![1] > o2!![1] -> {
                1
            }
            o1[1] < o2[1] -> {
                -1
            }
            else -> {
                0
            }
        }
    }
    var pos = points[0][1]
    var ans = 1
    for (balloon in points) {
        if (balloon[0] > pos) {
            pos = balloon[1]
            ++ans
        }
    }
    return ans
}