package com.punkstudio.kotlinalgorithmas

/**
 * Date:2020/10/20-2:50 PM
 * @author Mason
 */




fun main(args: Array<String>) {
    val node0 = ListNode(0)
    val node1 = ListNode(0)
    node0.next = node1
    println(isPalindrome(node0))
}
// todo 143. 重排链表（中等）
//给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
//你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//示例 1:
//给定链表 1->2->3->4, 重新排列为 1->4->2->3.
//示例 2:
//给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.


fun reorderList(head: ListNode?) {
    if (head == null) {
        return
    }
    val list = ArrayList<ListNode>()
    var node = head
    while (node != null) {
        list.add(node)
        node = node.next
    }
    var i = 0
    var j = list.size - 1
    while (i < j) {
        list[i].next = list[j]
        i++
        if (i == j) {
            break
        }
        list[j].next = list[i]
        j--
    }
    list[i].next = null
}


// todo 234. 回文链表（简单）
//请判断一个链表是否为回文链表。
//示例 1:
//输入: 1->2
//输出: false
//示例 2:
//输入: 1->2->2->1
//输出: true
//进阶：
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
fun isPalindrome(head: ListNode?): Boolean {
    if (head == null) {
        return false
    }
    val list = ArrayList<ListNode>()
    var node = head
    while (node != null) {
        list.add(node)
        node = node.next
    }
    val len = list.size
    if (len < 1) {
        return true
    }
    for (i in 0 until len / 2) {
        if (list[i].`val` != list[len - i - 1].`val`) {
            return false
        }
    }
    return true
}