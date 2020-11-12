package com.punkstudio.kotlinalgorithmas

import kotlin.math.max

/**
 * Date:2020/10/20-10:28 AM
 * @author Mason
 */


fun main(args: Array<String>) {
//    val nums = intArrayOf(2, 7, 11, 15)
//    val target = 9
//
//    println(twoSum1(nums, target).contentToString())
//    println(reverse(15342))

//    println(isLongPressedName("mason", "mmaasoon"))
//    println(partitionLabels("ababcbacadefegdehijhklij"))
//    println(videoStitching(arrayOf(intArrayOf(0,2),
//        intArrayOf(4,6), intArrayOf(8,10), intArrayOf(1,9), intArrayOf(1,5), intArrayOf(5,9)), 10))
//    println(smallerNumbersThanCurrent(intArrayOf(6,5,4,8)).contentToString())

    println(getSum(5, 7))
}

// todo 1. 两数之和（简单）
//给定一个整数数组 nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
//你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//示例:
//给定 nums = [2, 7, 11, 15], target = 9
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/two-sum
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        val offset = target - num
        if (map.containsKey(offset)) {
            return intArrayOf(map[offset]!!, index)
        } else {
            map[num] = index
        }
    }
    return intArrayOf(0)
}

// todo 167. 两数之和 II - 输入有序数组（简单）
//给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
//函数应该返回这两个下标值 index1 和 index2，其中 index1必须小于index2。
//说明:
//返回的下标值（index1 和 index2）不是从零开始的。
//你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
//示例:
//输入: numbers = [2, 7, 11, 15], target = 9
//输出: [1,2]
//解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
fun twoSum1(nums: IntArray, target: Int): IntArray {
    // 双指针
//    var low = 0
//    var high = nums.size - 1
//    while (low < high) {
//        val sum = nums[low] + nums[high]
//        when {
//            target == sum -> {
//                return intArrayOf(low + 1, high + 1)
//            }
//            target > sum -> {
//                low ++
//            }
//            else -> {
//                high --
//            }
//        }
//    }
//    return intArrayOf(-1, -1)

    // 二分法
    for (index in nums.indices) {
        var low = index + 1
        var high = nums.size - 1
        while (low <= high) {
            val mid = (high - low) / 2 + low
            when {
                nums[mid] == target - nums[index] -> {
                    return intArrayOf(index + 1, mid + 1)
                }
                nums[mid] > target - nums[index] -> {
                    high = mid - 1
                }
                else -> {
                    low = mid + 1
                }
            }
        }
    }
    return intArrayOf(-1, -1)
}

// todo 653. 两数之和 IV - 输入 BST（递归）（简单）
//给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
//案例 1:
//输入:
//    5
//   / \
//  3   6
// / \ / \
//2   4   7
//
//Target = 9
//输出: True
//
//案例 2:
//输入:
//    5
//   / \
//  3   6
// / \ / \
//2   4   7
//
//Target = 28
//
//输出: False
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
fun findTarget(root: TreeNode?, k: Int): Boolean {
    val set = HashSet<Int>()
    return find(root, set, k)
}

fun find(root: TreeNode?, set: HashSet<Int>, k: Int): Boolean {
    if (root == null)
        return false
    if (set.contains(k - root.`val`))
        return true
    set.add(k - root.`val`)
    return find(root.left, set, k) || find(root.right, set, k)
}

// todo 7. 整数反转（简单）
//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//示例1:
//输入: 123
//输出: 321
//示例 2:
//输入: -123
//输出: -321
//示例 3:
//输入: 120
//输出: 21
//注意:
//假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为[−231, 231− 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reverse-integer
fun reverse(x: Int): Int {
    var rev = 0
    var y = x
    while (y != 0) {
        val pop = y % 10
        y /= 10
        if ((rev > Int.MAX_VALUE / 10) || (rev == Int.MAX_VALUE / 10 && pop > Int.MAX_VALUE % 10)) return 0
        if ((rev < Int.MIN_VALUE / 10) || (rev == Int.MIN_VALUE / 10 && pop < Int.MIN_VALUE % 10)) return 0
        rev = rev * 10 + pop
    }
    return rev
}

// todo 925. 长按键入（简单）
//你的朋友正在使用键盘输入他的名字name。偶尔，在键入字符c时，按键可能会被长按，而字符可能被输入 1 次或多次。
//你将会检查键盘输入的字符typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回True。
//示例 1：
//输入：name = "alex", typed = "aaleex"
//输出：true
//解释：'alex' 中的 'a' 和 'e' 被长按。
//示例 2：
//输入：name = "saeed", typed = "ssaaedd"
//输出：false
//解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
//示例 3：
//输入：name = "leelee", typed = "lleeelee"
//输出：true
//示例 4：
//输入：name = "laiden", typed = "laiden"
//输出：true
//解释：长按名字中的字符并不是必要的。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/long-pressed-name
fun isLongPressedName(name: String, typed: String): Boolean {
    // 双指针
    var i = 0
    var j = 0
    while (j < typed.length) {
        when {
            i < name.length && name[i] == typed[j] -> {
                i ++
                j ++
            }
            j > 0 && typed[j] == typed[j - 1] -> {
                j ++
            }
            else -> {
                return false
            }
        }
    }
    return i == name.length
}

// todo 763. 划分字母区间（中等）
//字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
//示例 1：
//输入：S = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca", "defegde", "hijhklij"。
//每个字母最多出现在一个片段中。
//像 "ababcbacdaefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。

//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/partition-labels/
fun partitionLabels1(s: String): List<Int> {
    var i = 0
    val array = arrayListOf<Int>()
    while(i < s.length) {
        var index = s.lastIndexOf(s[i]) // 最后一个出现当前字母对下标
        var str = s.slice(i..index)
        var start = 1
        while (start < str.length) {
            index = s.lastIndexOf(s[start + i])
            when {
                (index - i) < str.length -> {
                    start ++
                }
                s.slice(index until s.length).contains(str[start]) -> {
                    str = s.slice(i..s.lastIndexOf(str[start]))
                }
                else -> {
                    start ++
                }
            }
        }
        i += str.length
        array.add(str.length)
    }

    return array
}

fun partitionLabels(s: String): List<Int> {
    val last = arrayOfNulls<Int>(26)
    val length = s.length
    // 把字符串里每个字母最后出现对位置记录到数组
    for (i in 0 until length) {
        last[s[i] - 'a'] = i
    }
    val partition = arrayListOf<Int>()
    var start = 0
    var end = 0
    // 查找片段里每个字母的最后一个下标
    for (i in 0 until length) {
        end = max(end, last[s[i] - 'a']!!)
        // 当找到当最后一个下标等于指针走过的字符串当长度
        if (i == end) {
            partition.add(end - start + 1)
            start = end + 1
        }
    }
    return partition
}

// todo *1024. 视频拼接（中等）
//你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
//视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
//我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
//示例 1：
//输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
//输出：3
//解释：
//我们选中 [0,2], [8,10], [1,9] 这三个片段。
//然后，按下面的方案重制比赛片段：
//将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
//现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
//示例 2：
//输入：clips = [[0,1],[1,2]], T = 5
//输出：-1
//解释：
//我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
//示例 3：
//输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
//输出：3
//解释：
//我们选取片段 [0,4], [4,7] 和 [6,9] 。
//示例 4：
//
//输入：clips = [[0,4],[2,8]], T = 5
//输出：2
//解释：
//注意，你可能录制超过比赛结束时间的视频。
//提示：
//1 <= clips.length <= 100
//0 <= clips[i][0] <= clips[i][1] <= 100
//0 <= T <= 100
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/video-stitching/
fun videoStitching(clips: Array<IntArray>, T: Int): Int {
    val maxn = arrayOfNulls<Int>(T)
    var last = 0
    var ret = 0
    var pre = 0
    for (clip in clips) {
        if (clip[0] < T) {
            maxn[clip[0]] = max(maxn[clip[0]] ?: 0, clip[1]);
        }
    }
    for (i in 0 until T) {
        last = max(last, maxn[i] ?: 0)
        if (i == last) {
            return -1
        }
        if (i == pre) {
            ret++
            pre = last
        }
    }
    return ret
}

// todo 371. 两整数之和（简单）
//不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
//示例 1:
//输入: a = 1, b = 2
//输出: 3
//示例 2:
//输入: a = -2, b = 3
//输出: 1
fun getSum(a: Int, b: Int): Int {
    var bb = b // 5 : 101
    var aa = a // 7 : 111
    while(bb != 0){
        val temp = aa xor bb // 010
        bb = (aa and bb) shl 1 //  1010
        aa = temp // 010
    }
    return aa
}
