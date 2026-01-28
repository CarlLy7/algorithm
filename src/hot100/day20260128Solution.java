package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2026.01.28
 * @Since: 1.0
 */

public class day20260128Solution {
    // [1658] 将 x 减到 0 的最小操作数
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int need = sum - x;
        int left = 0, right = 0;
        int n = nums.length;
        int window = 0;
        // 窗口的最大长度
        int maxLen = Integer.MIN_VALUE;
        while (right < n) {
            int c = nums[right];
            right++;
            window += c;
            // 收缩条件
            while (window > need && left < right) {
                int d = nums[left];
                left++;
                window -= d;
            }
            if (window == need) {
                maxLen = Math.max(maxLen, right - left);
            }
        }
        return maxLen == Integer.MIN_VALUE ? -1 : n - maxLen;
    }

    // [713] 乘积小于 K 的子数组
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int window = 1;
        int ans = 0;
        int left = 0, right = 0;
        while (right < n) {
            window *= nums[right];
            right++;
            // 收缩条件
            while (left < right && window >= k) {
                window = window / nums[left];
                left++;
            }
            // 收缩完毕之后，肯定是满足条件的了，就可以更新答案了
            ans += right - left;
        }
        return ans;
    }

    // [1004] 最大连续1的个数 III
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int maxLen = Integer.MIN_VALUE;
        int windowZero = 0;
        while (right < n) {
            int c = nums[right];
            right++;
            if (c == 0) {
                windowZero++;
            }
            // 收缩条件
            while (windowZero > k) {
                int d = nums[left];
                left++;
                if (d == 0) {
                    windowZero--;
                }
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }
}
