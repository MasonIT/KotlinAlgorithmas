package com.punkstudio.kotlinalgorithmas

import java.util.*

/**
 * Date:2020/11/23-1:58 PM
 *
 * @author Mason
 */
internal class Solution {
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
}