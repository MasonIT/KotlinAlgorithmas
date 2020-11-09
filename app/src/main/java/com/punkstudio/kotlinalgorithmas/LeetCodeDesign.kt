package com.punkstudio.kotlinalgorithmas

/**
 * Date:2020/10/31-8:34 PM
 * @author Mason
 */


fun main(args: Array<String>) {
    var obj = RandomizedCollection()
//    var param_1 = obj.insert(`val`)
//    var param_2 = obj.remove(`val`)
//    var param_3 = obj.getRandom()
}

// todo 381. O(1) 时间插入、删除和获取随机元素 - 允许重复（困难*）
//设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
//注意: 允许出现重复元素。
//insert(val)：向集合中插入元素 val。
//remove(val)：当 val 存在时，从集合中移除一个 val。
//getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
//示例:
//// 初始化一个空的集合。
//RandomizedCollection collection = new RandomizedCollection();
//// 向集合中插入 1 。返回 true 表示集合不包含 1 。
//collection.insert(1);
//// 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
//collection.insert(1);
//// 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
//collection.insert(2);
//// getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
//collection.getRandom();
//// 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
//collection.remove(1);
//// getRandom 应有相同概率返回 1 和 2 。
//collection.getRandom();
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
class RandomizedCollection(
    private val idx: HashMap<Int, HashSet<Int>> = HashMap(),
    private val nums: MutableList<Int> = mutableListOf()
) {
    fun insert(`val`: Int): Boolean {
        nums.add(`val`)
        val set = idx.getOrDefault(`val`, HashSet())
        set.plus(nums.size - 1)
        idx[`val`] = set;
        return set.size == 1
    }

    fun remove(`val`: Int): Boolean {
        if (!idx.containsKey(`val`)) {
            return false;
        }
        val it = idx[`val`]!!.iterator();
        val i = it.next()
        val lastNum = nums[nums.size] - 1
        nums[i] = lastNum
        idx[`val`]!!.remove(i)
        idx[lastNum]!!.remove(nums.size - 1)
        if (i < nums.size - 1) {
            idx[lastNum]?.add(i)
        }
        if (idx[`val`]?.size == 0) {
            idx.remove(`val`)
        }
        nums.remove(nums.size - 1)
        return true
    }


    fun getRandom(): Int {
        return nums[(Math.random() * nums.size).toInt()];
    }
}
