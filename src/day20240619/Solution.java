package day20240619;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-19 10:46
 * @version: 1.0
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // dp[i]的含义：nums[0..i]中连续子数组最大和
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            // 不选or选
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        LinkedList<int[]> ans = new LinkedList<>();
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            int[] last = ans.getLast();
            if (cur[0] <= last[1]) {
                last[1] = Math.max(last[1], cur[1]);
            } else {
                ans.addLast(cur);
            }
        }
        return ans.toArray(new int[0][0]);
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    // 翻转指定区间内的数字 [1,2,3]->[3,2,1]
    private void reverse(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
        }
        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }
        int[] res = new int[n];
        res[0] = suffix[1];
        res[n - 1] = prefix[n - 2];
        for (int i = 1; i < n - 1; i++) {
            res[i] = prefix[i - 1] * suffix[i + 1];
        }
        return res;
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
