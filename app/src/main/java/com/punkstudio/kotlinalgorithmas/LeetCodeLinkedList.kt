package com.punkstudio.kotlinalgorithmas

/**
 * Date:2020/10/20-2:50 PM
 * @author Mason
 */




fun main(args: Array<String>) {

//    val node0 = ListNode(0)
//    val node1 = ListNode(0)
//    node0.next = node1
//    println(isPalindrome(node0))

//    val node0 = ListNode(1)
//    val node1 = ListNode(2)
//    val node2 = ListNode(3)
//    val node3 = ListNode(4)
//    val node4 = ListNode(5)
//    node0.next = node1
//    node1.next = node2
//    node2.next = node3
//    node3.next = node4
//    node4.next = null
//    println(oddEvenList(node0))

    println(Math.round(1.5))
    Help.test()
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

// todo 328. 奇偶链表
//给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
//请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
//示例 1:
//输入: 1->2->3->4->5->NULL
//输出: 1->3->5->2->4->NULL
//示例 2:
//输入: 2->1->3->5->6->4->7->NULL
//输出: 2->3->6->7->1->5->4->NULL
//说明:
//应当保持奇数节点和偶数节点的相对顺序。
//链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/odd-even-linked-list
fun oddEvenList(head: ListNode?): ListNode? {
    if (head == null) {
        return head
    }
    val evenHead = head.next
    var even = evenHead
    var odd = head
    while (even?.next != null) {
        odd?.next = even.next
        odd = odd?.next
        even.next = odd?.next
        even = even.next
    }
    odd?.next = evenHead
    return head
}