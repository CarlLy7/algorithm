package array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 拼车 https://leetcode.cn/problems/car-pooling/
 * @author: lyq
 * @createDate: 28/4/2023
 * @version: 1.0
 */
public class carPooling {
    //定义的一个差分数组类，这个类里面主要包括两个主要的方法，一个是更新数组中一个范围的数，另一个方法是返回更新后的数组的结果
    class Difference {
        //差分数组
        private int[] diff;

        public Difference(int[] nums) {
            int n = nums.length;
            if (n <= 0) {
                return;
            }
            //构造差分数组
            diff = new int[n];
            diff[0] = nums[0];
            for (int i = 1; i < n; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        //在i-j这个范围内，对数据进行val更新操作，val可以是负值
        public void incr(int i, int j, int val) {
            diff[i] += val;
            //如果j+1超过了数组的范围说明我们是对i到最后的位置进行了更新，所以j后面的就不需要再减val了
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        //返回更新后的数组
        public int[] result() {
            int[] res = new int[diff.length];
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }
    }

    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001];
        Difference difference = new Difference(nums);
        for (int[] trip : trips) {
            int i = trip[1];
            //这里为什么减一，因为到了j位置的时候旅客就下车了
            int j = trip[2] - 1;
            //上车的人数
            int val = trip[0];
            difference.incr(i, j, val);
        }
        int[] result = difference.result();
        for (int i = 0; i < result.length; i++) {
            //我们必须保证在开车的整个过程中，车上的人数不能超过最大载客量也就是capacity
            if (result[i] > capacity) {
                return false;
            }
        }
        return true;
    }
}
