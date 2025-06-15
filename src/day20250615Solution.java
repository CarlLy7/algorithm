import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-06-15 15:48
 * @Since: 1.0
 */
public class day20250615Solution {

    // [8] 字符串转换整数 (atoi)
    public int myAtoi(String s) {
        int n = s.length();
        int i = 0;
        int sign = 1;
        long res = 0;
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i == n) {
            return 0;
        }
        //记录符号位
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }
        if (i == n) {
            return 0;
        }
        while (i < n && '0' <= s.charAt(i) && s.charAt(i) <= '9') {
            res = res * 10 + s.charAt(i) - '0';
            if (res > Integer.MAX_VALUE) {
                break;
            }
            i++;
        }
        //如果溢出了
        if ((int) res != res) {
            return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }

    // [16] 最接近的三数之和
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        // 三数之和与target的偏差
        int delta = Integer.MAX_VALUE;
        //先将元素进行排序
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            //三数之和
            int sum = nums[i] + twoSum(nums, i + 1, target - nums[i]);
            if (Math.abs(delta) > Math.abs(target - sum)) {
                delta = target - sum;
            }
        }
        return target - delta;
    }

    /**
     * 从nums的start位置开始，找两数和最接近target的
     *
     * @param nums
     * @param start
     * @param target
     * @return
     */
    private int twoSum(int[] nums, int start, int target) {
        int lo = start, hi = nums.length - 1;
        int delta = Integer.MAX_VALUE;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (Math.abs(delta) > Math.abs(target - sum)) {
                delta = target - sum;
            }
            if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return target - delta;
    }

    // [50] Pow(x, n)
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        //因为对于n为负数的情况，我们要变成1/x的正n次方，如果-2^31变成2^31会溢出
        if (n == Integer.MIN_VALUE) {
            return myPow(1 / x, -(n + 1)) / x;
        }
        if (n < 0) {
            return myPow(1 / x, -n);
        }

        //奇数次幂
        if (n % 2 == 1) {
            return myPow(x, n - 1) * x;
        } else {
            double sub = myPow(x, n / 2);
            return sub * sub;
        }
    }
}
