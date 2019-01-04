package com.punkstudio.kotlinalgorithmas

/**
 * Date:2018/12/28-16:48
 * @author Mason
 */

fun getArray() : IntArray {
    return intArrayOf(3,44,38,5,47,15,36,26,27,2,46,4,19,50,48)
}

fun insertionSort() : IntArray {
    val array = getArray()
    val len = array.size
    for (i in 1 until len) {
        var j = i - 1
        val key = array[j]
        while (j >= 0 && array[j] > key) {
            array[j + 1] = array[j]
            --j
        }
        array[j + 1] = key
    }
    return array
}

fun main(args: Array<out String>) {

}