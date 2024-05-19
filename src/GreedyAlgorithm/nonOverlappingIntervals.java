package GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: 无重叠区间 https://leetcode.cn/problems/non-overlapping-intervals/
 * @author: lyq
 * @createDate: 23/4/2023
 * @version: 1.0
 */
public class nonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        //根据每个区间的end进行一个升序排列，我们要保证后面的序列的start是大于等于这个区间的结尾才能说明这两个区间是没有交集的
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[1] > o2[1]) {
                return 1;
            }
            if (o1[1] < o2[1]) {
                return -1;
            }
            return 0;
        });
        //count来记录没有交集的区间，最后的结果就是intervals元素的个数减去这个没有交集的区间个数就好了
        int count = 1;
        //开始的end肯定是end最小的那个
        int start_end = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start >= start_end) {
                //如果后面的区间的start是比此时的start_end大或者等于的时候，说明两个区间也是没有交集的
                count++;
                //更新start_end的大小
                start_end = interval[1];
            } else {
                //如果两个区间有交集的话，什么都不做
            }
        }
        return intervals.length - count;
    }
}
