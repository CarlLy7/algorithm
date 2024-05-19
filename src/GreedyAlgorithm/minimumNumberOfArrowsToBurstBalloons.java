package GreedyAlgorithm;

import java.util.Arrays;

/**
 * @description: 用最少数量的箭引爆气球 https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
 * @author: lyq
 * @createDate: 23/4/2023
 * @version: 1.0
 */
public class minimumNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        //其实这道题目可以换一种说法，那就是将重叠区间我们射一箭可以将所有的重叠区间射爆，同时注意，如果边界相交也算是重叠
        Arrays.sort(points, ((o1, o2) -> {
            if (o1[1] > o2[1]) {
                return 1;
            }
            if (o1[1] < o2[1]) {
                return -1;
            }
            return 0;
        }));
        int count=1;
        int start_end=points[0][1];
        for (int[] point : points) {
            int start=point[0];
            if(start>start_end){
                count++;
                start_end=point[1];
            }else{

            }
        }

        return count;
    }
}
