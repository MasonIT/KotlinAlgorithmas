package com.punkstudio.kotlinalgorithmas

/**
 * Date:2020/10/28-9:50 AM
 * @author Mason
 */


// todo 144. 二叉树的前序遍历
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