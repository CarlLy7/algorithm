package fourClass;

/**
 * @description: 题目链接：https://leetcode.cn/problems/count-of-range-sum/
 * @author: lyq
 * @createDate: 24/2/2023
 * @version: 1.0
 */
public class CountOfRangeSum {
    //    public static int countRangeSum(int[] nums, int lower, int upper) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        //求前缀和
//        long[] sum = new long[nums.length];
//        sum[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            sum[i] = sum[i - 1] + nums[i];
//        }
//        return process(sum, 0, sum.length - 1, lower, upper);
//
//    }
//
//    private static int process(long[] sum, int l, int r, int lower, int upper) {
//        if (l == r) {
//            //如果前缀和中只有一个数字，说明只有一个元素，我们直接进行判断即可
//            return (sum[l] >= lower && sum[r] <= upper) ? 1 : 0;
//        }
//        int mid = l + ((r - l) >> 1);
//        return process(sum, l, mid, lower, upper) + process(sum, mid + 1, r, lower, upper) + merge(sum, l, mid, r, lower, upper);
//
//    }
//
//    //此时注意，左组和右组中的数字都是前缀和
//    private static int merge(long[] sum, int l, int mid, int r, int lower, int upper) {
//        int ans = 0;
//        //windowR和windowL都是左组中滑动的指针
//        int windowL = l;
//        int windowR = l;
//        for (int i = mid + 1; i <= r; i++) {
//            long max = sum[i] - lower;//当前右组中的这个前缀和要是满足在指定区间中的话，左组中的前缀和的最大值
//            long min = sum[i] - upper;//当前右组中的这个前缀和要是满足在指定区间中的话，左组中的前缀和的最小值
//            while (windowR <= mid && sum[windowR] <= max) {//如果左组中的前缀和中一个位置的前缀和小于等于最大值的话右滑
//                windowR++;
//            }
//            while (windowL <= mid && sum[windowL] < min) {//如果左组中的前缀和中一个位置的前缀和大于等于最小值的话右滑
//                windowL++;
//            }
//            ans += windowR - windowL;
//        }
//        //归并
//        long[] temp = new long[r - l + 1];
//        int i = 0;
//        int p1 = l;
//        int p2 = mid + 1;
//        while (p1 <= mid && p2 <= r) {
//            temp[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
//        }
//        while (p1 <= mid) {
//            temp[i++] = sum[p1++];
//        }
//        while (p2 <= r) {
//            temp[i++] = sum[p2++];
//        }
//        for (int j = 0; j < temp.length; j++) {
//            sum[l + j] = temp[j];
//        }
//        return ans;
//    }
//
//    public static void main(String[] args) {
//        int[] test = new int[]{-2, 5, -1};
//        System.out.println(countRangeSum(test, -2, 2));
//    }
    public static int getCountRangeSum(int[] array, int lower, int upper) {
        if (array == null || array.length < 1) {
            return 0;
        }
        //计算前缀和
        long[] sum = new long[array.length];
        sum[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            sum[i] = sum[i - 1] + array[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    private static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l == r) {
            //前缀和数组中只有一个数的时候，说明原来的数组中也只要一个数，直接进行判断然后返回就可以了
            return (sum[l] >= lower && sum[r] <= upper) ? 1 : 0;
        }
        int m = l + ((r - l) >> 1);
        return process(sum, l, m, lower, upper) + process(sum, m + 1, r, lower, upper) + merger(sum, l, m, r, lower, upper);

    }

    private static int merger(long[] sum, int l, int m, int r, int lower, int upper) {
        int ans = 0;
        int windowL = l;
        int windowR = l;
        for (int i = m + 1; i <= r; i++) {
            long max = sum[i] - lower;
            long min = sum[i] - upper;
            while (windowR <= m && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= m && sum[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }
        //归并
        long[] temp = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            temp[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= m) {
            temp[i++] = sum[p1++];
        }
        while (p2 <= r) {
            temp[i++] = sum[p2++];
        }
        for (int j = 0; j < temp.length; j++) {
            sum[l + j] = temp[j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test = new int[]{-2, 5, -1};
        System.out.println(getCountRangeSum(test, -2, 2));
    }


}
