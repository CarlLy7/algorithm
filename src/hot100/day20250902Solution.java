package hot100;

import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.02
 * @Since: 1.0
 */

public class day20250902Solution {
    // [240] 搜索二维矩阵 II
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i >= 0 && i < m && j >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    // [279] 完全平方数
    public int numSquares(int n) {
        // dp[i]:和为i的完全平方数的最小数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // 可以理解为对应面值的硬币
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    // [283] 移动零
    public void moveZeroes(int[] nums) {
        int p = removeZero(nums, 0);
        for (; p < nums.length; p++) {
            nums[p] = 0;
        }
    }

    /**
     * 从nums中移除n
     * 
     * @param nums
     * @param n
     * @return
     */
    private int removeZero(int[] nums, int n) {
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != n) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }
}
