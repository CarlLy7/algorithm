import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.15
 * @Since: 1.0
 */

public class day20250515Solution {
    // 16 最接近的三数之和
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        // 和与target的误差
        int delta = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            // 三数之和，其中twoSumClosest()函数是求最接近target的两数之和
            int sum = nums[i] + twoSumClosest(nums, i + 1, target - nums[i]);
            // 如果误差变小了，则更新误差
            if (Math.abs(delta) > Math.abs(target - sum)) {
                delta = target - sum;
            }
        }
        return target - delta;
    }

    /**
     * 从nums[start...]中寻找和最接近target的两个数
     * 
     * @param nums
     * @param start
     * @param target
     * @return
     */
    private int twoSumClosest(int[] nums, int start, int target) {
        int delta = Integer.MAX_VALUE;
        int lo = start, hi = nums.length - 1;
        while (lo < hi) {
            // 两数之和
            int sum = nums[lo] + nums[hi];
            // 误差变小了
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

    // 8 字符串转换整数 (atoi)
    public int myAtoi(String s) {
        int n = s.length();
        // 起始索引
        int i = 0;
        // 符号位
        int sign = 1;
        // 先使用long避免int溢出
        long res = 0;
        // 跳过前置空格
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i == n) {
            return 0;
        }
        // 处理符号
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            sign = 1;
            i++;
        }
        if (i == n) {
            return 0;
        }

        // 统计数字位
        while (i < n && '0' <= s.charAt(i) && s.charAt(i) <= '9') {
            res = res * 10 + s.charAt(i) - '0';
            if (res > Integer.MAX_VALUE) {
                break;
            }
            i++;
        }
        // 如果溢出，强转成 int 就会和真实值不同
        if ((int)res != res) {
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return sign * (int)res;
    }

    // 292 Nim 游戏
    public boolean canWinNim(int n) {
        // 谁先进去四的倍数那么就输了
        return n % 4 != 0;
    }
}
