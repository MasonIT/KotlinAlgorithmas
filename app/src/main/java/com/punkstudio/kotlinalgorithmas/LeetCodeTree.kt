package com.punkstudio.kotlinalgorithmas

import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

/**
 * Date:2020/10/28-9:50 AM
 * @author Mason
 */

fun main(args: Array<String>) {
    val tree1 = TreeNode(1)
    val tree2_1 = TreeNode(1)
    val tree2_2 = TreeNode(0)
    val tree3_1 = TreeNode(7)
    val tree3_2 = TreeNode(-8)
    val tree3_3 = TreeNode(-7)
    val tree3_4 = TreeNode(9)
    tree2_1.left = tree3_1
    tree2_1.right = tree3_2
    tree2_2.left = tree3_3
    tree2_2.right = tree3_4
    tree1.left = tree2_1
    tree1.right = tree2_2
    println(maxLevelSumBFS(tree1))
}
// todo 144. 二叉树的前序遍历 (递归）
//给定一个二叉树，返回它的 前序 遍历。
// 示例:
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

// todo 1161. 最大层内元素和（递归）
//给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
//请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
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
//树中的节点数介于 1 和 10^4 之间
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