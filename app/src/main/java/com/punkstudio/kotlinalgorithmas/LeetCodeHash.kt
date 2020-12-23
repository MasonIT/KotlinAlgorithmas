package com.punkstudio.kotlinalgorithmas

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max

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

// todo 1365. 有多少小于当前数字的数字（简单）
//给你一个数组nums，对于其中每个元素nums[i]，请你统计数组中比它小的所有数字的数目。
//换而言之，对于每个nums[i]你必须计算出有效的j的数量，其中 j 满足j != i 且 nums[j] < nums[i]。
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

// todo 1207. 独一无二的出现次数（简单）
//给你一个整数数组arr，请你帮忙统计数组中每个数的出现次数。
//如果每个数的出现次数都是独一无二的，就返回true；否则返回 false。
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
//1 <= arr.length<= 1000
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

// todo 463. 岛屿的周长（简单）
//给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地0 表示水域。
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

// todo 349. 两个数组的交集（简单）
//给定两个数组，编写一个函数来计算它们的交集。
//示例 1：
//输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
//示例 2：
//输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4]
//说明：
//输出结果中的每个元素一定是唯一的。
//我们可以不考虑输出结果的顺序。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    val set = HashSet<Int>()
    val set1 = nums1.toSet()
    val set2 = nums2.toSet()
    for (num in set1) {
        if (set2.contains(num)) {
            set.add(num)
        }
    }
    return set.toIntArray()
}

// todo 1122. 数组的相对排序（简单）
//给你两个数组，arr1 和arr2，
//arr2中的元素各不相同
//arr2 中的每个元素都出现在arr1中
//对 arr1中的元素进行排序，使 arr1 中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾。
//示例：
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/relative-sort-array
fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
    var upper = 0
    for (i in arr1) {
        upper = max(upper, i)
    }
    val array = Array(upper + 1) { 0 }
    for (i in arr1) {
        ++ array[i]
    }
    val ans = Array(arr1.size) { 0 }
    var index = 0
    for (i in arr2) {
        for (j in 0 until array[i]) {
            ans[index++] = i
        }
        array[i] = 0
    }
    for (i in 0 .. upper) {
        for (j in 0 until array[i]) {
            ans[index++] = i
        }
    }
    return ans.toIntArray()
}

// todo 204. 计数质数（简单）
//统计所有小于非负整数 n 的质数的数量。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-primes
fun countPrimes(n: Int): Int {
    var count = 0
    for (i in 0 until n) {
        if (isPrime(i)) {
            count ++
        }
    }
    return count
}


// todo 49. 字母异位词分组（中等）
//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//示例:
//输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//["ate","eat","tea"],
//["nat","tan"],
//["bat"]
//]
//说明：
//所有输入均为小写字母。
//不考虑答案输出的顺序。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/group-anagrams
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = HashMap<String, MutableList<String>>()

    for (str in strs) {
        val array = str.toCharArray()
        Arrays.sort(array)
        val list = map.getOrDefault(array.contentToString(), ArrayList())
        list.add(str)
        map[array.contentToString()] = list
    }

    return ArrayList(map.values)
}


// todo 290. 单词规律（简单）
//给定一种规律 pattern和一个字符串str，判断 str 是否遵循相同的规律。
//这里的遵循指完全匹配，例如，pattern里的每个字母和字符串str中的每个非空单词之间存在着双向连接的对应规律。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/word-pattern
fun wordPattern(pattern: String, s: String): Boolean {
    val patternArray = pattern.toCharArray()
    val array = s.split(" ").toTypedArray()
    if (patternArray.size != array.size) {
        return false
    }
    val map = HashMap<Char, MutableList<String>>()
    for ((i, v) in patternArray.withIndex()) {
        val list = map.getOrDefault(v, ArrayList())
        list.add(array[i])
        map[v] = list
    }
    val map1 = HashMap<String, String>()
    for (s in array) {
        map1[s] = s
    }
    if (map.size != map1.size) {
        return false
    }

    for (list in map.values) {
        val value = list[0]
        if (list.any { it != value }) {
            return false
        }
    }
    return true
}

// todo 389. 找不同（简单）
//给定两个字符串 s 和 t，它们只包含小写字母。
//字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
//请找出在 t 中被添加的字母。
fun findTheDifference(s: String, t: String): Char {
//    val array = IntArray(26) { 0 }
//    for (c in s.toCharArray()) {
//        array[c - 'a'] ++
//    }
//    for (c in t.toCharArray()) {
//        array[c - 'a'] --
//        if (array[c - 'a'] < 0) {
//            return c
//        }
//    }
//    return ' '

    var sum = 0
    for (c in t.toCharArray()) {
        sum += c.toInt()
    }
    for (c in s.toCharArray()) {
        sum -= c.toInt()
    }

    return sum.toChar()
}

// todo 387. 字符串中的第一个唯一字符（简单）
//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//示例：
//s = "leetcode"
//返回 0
//s = "loveleetcode"
//返回 2
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
fun firstUniqChar(s: String): Int {
    val countArray = IntArray(26) { 0 }
    for (c in s) {
        countArray[c - 'a']++
    }
    for ((i, c) in s.withIndex()) {
        if (countArray[c - 'a'] == 1) {
            return i
        }
    }
    return -1

//    val map = HashMap<Char, Int>()
//    for ((i, c) in s.withIndex()) {
//        if (map.containsKey(c)) {
//            map[c] = -1
//        } else {
//            map[c] = i
//        }
//    }
//    var first = s.length
//    for (position in map.entries) {
//        val value = position.value
//        if (value != -1 && value < first) {
//            first = value
//        }
//    }
//    if (first == s.length) {
//        first = -1
//    }
//    return first
}