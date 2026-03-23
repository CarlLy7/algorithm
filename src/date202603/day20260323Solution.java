package date202603;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.23
 * @Since: 1.0
 */

public class day20260323Solution {
    // [1094] 拼车
    public boolean carPooling(int[][] trips, int capacity) {
        // 最多有1000个车站，这里直接构造1001
        int[] nums = new int[1001];
        Difference difference = new Difference(nums);
        for (int[] trip : trips) {
            int val = trip[0];
            int i = trip[1];
            // 因为乘客在trip[2]的时候就下车了，所以在车上应该是trip[2]-1
            int j = trip[2] - 1;
            difference.increment(i, j, val);
        }
        int[] result = difference.result();
        // 必须保证任何时候车上的乘客不能超载
        for (int i = 0; i < result.length; i++) {
            if (result[i] > capacity) {
                return false;
            }
        }
        return true;
    }

    // [1109] 航班预订统计
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        Difference difference = new Difference(nums);
        for (int[] booking : bookings) {
            // 出发点，有一位的位移
            int i = booking[0] - 1;
            // 因为是飞机预定，所以不会下车，有一位的位移
            int j = booking[1] - 1;
            int val = booking[2];
            difference.increment(i, j, val);
        }
        int[] result = difference.result();
        return result;
    }

    /**
     * 差分数组类
     */
    private class Difference {
        // diff[i]:nums[i]-nums[i-1]的差值
        private int[] diff;

        public Difference(int[] nums) {
            this.diff = new int[nums.length];
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /**
         * 给区间[i,j]中元素增加val(val可以为负数)
         * 
         * @param i
         * @param j
         * @param val
         */
        public void increment(int i, int j, int val) {
            diff[i] += val;
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /**
         * 获取原始数组
         * 
         * @return
         */
        public int[] result() {
            int[] res = new int[diff.length];
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }
    }
}
