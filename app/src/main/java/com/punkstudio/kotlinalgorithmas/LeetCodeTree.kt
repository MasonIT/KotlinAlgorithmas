package com.punkstudio.kotlinalgorithmas

import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

/**
 * Date:2020/10/28-9:50 AM
 * @author Mason
 */

fun main(args: Array<String>) {
//    val tree1 = TreeNode(1)
//    val tree2_1 = TreeNode(1)
//    val tree2_2 = TreeNode(0)
//    val tree3_1 = TreeNode(7)
//    val tree3_2 = TreeNode(-8)
//    val tree3_3 = TreeNode(-7)
//    val tree3_4 = TreeNode(9)
//    tree2_1.left = tree3_1
//    tree2_1.right = tree3_2
//    tree2_2.left = tree3_3
//    tree2_2.right = tree3_4
//    tree1.left = tree2_1
//    tree1.right = tree2_2

//    println(maxLevelSumDFS(tree1))

//    println(fibonacci(10))

//    println(sumNumbersBfs(tree1))

    println(sortString("aaaabbbbcccc"))
}
// todo 144. 二叉树的前序遍历 (递归）（中等）
//给定一个二叉树，返回它的前序遍历。
//示例:
//输入: [1,null,2,3]
//1
//\
//2
///
//3
//输出: [1,2,3]
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
fun preorderTraversal(root: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
    preOrder(root, list)
    return list
}
private fun preOrder(root: TreeNode?, list: MutableList<Int>) {
    if (root == null) {
        return
    }
    list.add(root.`val`)
    preOrder(root.left, list)
    preOrder(root.right, list)
}

// todo 1161. 最大层内元素和（递归）（中等）
//给你一个二叉树的根节点root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
//请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中最小 的那个。
//示例 1：
//输入：root = [1,7,0,7,-8,null,null]
//输出：2
//解释：
//第 1 层各元素之和为 1，
//第 2 层各元素之和为 7 + 0 = 7，
//第 3 层各元素之和为 7 + -8 = -1，
//所以我们返回第 2 层的层号，它的层内元素之和最大。
//示例 2：
//输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
//输出：2
//提示：
//树中的节点数介于1和10^4之间
//-10^5 <= node.val <= 10^5
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-level-sum-of-a-binary-tree
// DFS 深度优先遍历
fun maxLevelSumDFS(root: TreeNode?): Int {
    val map = HashMap<Int, Int>()
    inorder(root = root, map = map)
    return map.maxBy { it.value }?.key ?: 1
}
var count = 0

private fun inorder(ret: Int = 1, root: TreeNode?, map: HashMap<Int, Int>) {
    println("maxLevelSum, count=${++count}, number=${root?.`val`}")
    if (root != null) {
        inorder(ret = ret + 1, root = root.left, map = map)
        val v = map[ret] ?: 0
        map[ret] = v + root.`val`
        inorder(ret = ret + 1, root = root.right, map = map)
    }
}
// BFS 广度优先遍历
fun maxLevelSumBFS(root: TreeNode?): Int {
    var maxIndex = 1
    var currIndex = 1
    var currSum = 0
    var maxSum = root?.`val` ?: 0
    val queue = LinkedList<TreeNode>()
    val marker: TreeNode? = null
    queue.addLast(root)
    queue.addLast(marker)
    while (queue.size > 1) {
        val point = queue.removeFirst()
        if (point != null) {
            currSum += point.`val`
            if (point.left != null) queue.addLast(point.left)
            if (point.right != null) queue.addLast(point.right)
        } else {
            if (maxSum < currSum) {
                maxSum = currSum
                maxIndex = currIndex
            }
            currSum = 0
            currIndex ++
            queue.addLast(marker)
        }
    }
    return if (maxSum < currSum) currIndex else maxIndex
}

// todo 129. 求根到叶子节点数字之和（中等）
//给定一个二叉树，它的每个结点都存放一个0-9的数字，每条从根到叶子节点的路径都代表一个数字。
//例如，从根到叶子节点路径 1->2->3 代表数字 123。
//计算从根到叶子节点生成的所有数字之和。
//说明:叶子节点是指没有子节点的节点。
//示例 1:
//输入: [1,2,3]
//1
/// \
//2   3
//输出: 25
//解释:
//从根到叶子节点路径 1->2 代表数字 12.
//从根到叶子节点路径 1->3 代表数字 13.
//因此，数字总和 = 12 + 13 = 25.
//示例 2:
//输入: [4,9,0,5,1]
//4
/// \
//9   0
/// \
//5   1
//输出: 1026
//解释:
//从根到叶子节点路径 4->9->5 代表数字 495.
//从根到叶子节点路径 4->9->1 代表数字 491.
//从根到叶子节点路径 4->0 代表数字 40.
//因此，数字总和 = 495 + 491 + 40 = 1026.
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
// DFS
fun sumNumbers(root: TreeNode?): Int {
    return dfs(root)
}

fun dfs(root: TreeNode?, preSum: Int = 0): Int {
    if (root == null) {
        return 0
    }
    val sum = preSum * 10 + root.`val`
    return if (root.left == null && root.right == null) {
        sum
    } else {
        dfs(root.left, sum) + dfs(root.right, sum)
    }
}
// BFS
fun sumNumbersBfs(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    var sum = 0
    val nodeQueue = LinkedList<TreeNode>()
    val numQueue = LinkedList<Int>()
    nodeQueue.offer(root);
    numQueue.offer(root.`val`)
    while (!nodeQueue.isEmpty()) {
        val node = nodeQueue.poll()
        val num = numQueue.poll()
        val left = node.left
        val right = node.right
        if (left == null && right == null) {
            sum += num
        } else {
            if (left != null) {
                nodeQueue.offer(left)
                numQueue.offer(num * 10 + left.`val`)
            }
            if (right != null) {
                nodeQueue.offer(right)
                numQueue.offer(num * 10 + right.`val`)
            }
        }
    }
    return sum
}


fun fibonacci(n: Int): Int {
    return when (n) {
        0 -> {
            0
        }
        1 -> {
            1
        }
        else -> {
            fibonacci(n - 1) + fibonacci(n - 2)
        }
    }
}

// todo 222. 完全二叉树的节点个数（中等）
//给出一个完全二叉树，求出该树的节点个数。
//说明：
//完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2h个节点。
//示例:
//输入:
//    1
//   / \
//  2   3
// / \  /
//4  5 6
//输出: 6
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
fun countNodes(root: TreeNode?): Int {
    if (root == null) {
        return count
    } else {
        count ++
    }
    countNodes(root.left)
    countNodes(root.right)
    return count
}

// todo 1370. 上升下降字符串（简单）
//给你一个字符串s，请你根据下面的算法重新构造字符串：
//从 s中选出 最小的字符，将它 接在结果字符串的后面。
//从 s剩余字符中选出最小的字符，且该字符比上一个添加的字符大，将它 接在结果字符串后面。
//重复步骤 2 ，直到你没法从 s中选择字符。
//从 s中选出 最大的字符，将它 接在结果字符串的后面。
//从 s剩余字符中选出最大的字符，且该字符比上一个添加的字符小，将它 接在结果字符串后面。
//重复步骤 5，直到你没法从 s中选择字符。
//重复步骤 1 到 6 ，直到 s中所有字符都已经被选过。
//在任何一步中，如果最小或者最大字符不止一个，你可以选择其中任意一个，并将其添加到结果字符串。
//请你返回将s中字符重新排序后的 结果字符串 。
//示例 1：
//输入：s = "aaaabbbbcccc"
//输出："abccbaabccba"
//解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
//第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
//第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
//第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
//第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
//示例 2：
//输入：s = "rat"
//输出："art"
//解释：单词 "rat" 在上述算法重排序以后变成 "art"
//示例 3：
//输入：s = "leetcode"
//输出："cdelotee"
//示例 4：
//输入：s = "ggggggg"
//输出："ggggggg"
//示例 5：
//输入：s = "spo"
//输出："ops"
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/increasing-decreasing-string
fun sortString(s: String): String {
//    val array = s.toCharArray().sortedArray().toMutableList()
//    val list = mutableListOf<Char>()
//    while (array.isNotEmpty()) {
//        list.add(array.removeAt(0))
//        var i = 0
//        while (i < array.size) {
//            if (list.last() < array[i]) {
//                list.add(array.removeAt(i))
//            } else {
//                i ++
//            }
//        }
//        if (array.isNotEmpty()) list.add(array.removeAt(array.size - 1))
//        var j = array.size - 1
//        while (j >= 0) {
//            if (list.last() > array[j]) {
//                list.add(array.removeAt(j))
//            }
//            j --
//        }
//    }
//
//    return String(list.toCharArray())

    val array = IntArray(26) { 0 }
    for (c in s) {
        array[(c - 'a')] ++
    }
    val buffer = StringBuffer()
    while (buffer.length < s.length) {
        for (i in 0 until array.size) {
            if (array[i] > 0) {
                buffer.append((i + 97).toChar())
                array[i] --
            }
        }
        var j = 25
        while (j >= 0) {
            if (array[j] > 0) {
                buffer.append((j + 97).toChar())
                array[j] --
            }
            j --
        }
    }
    return buffer.toString()
}