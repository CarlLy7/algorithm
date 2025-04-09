import sun.font.FontRunIterator;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.09
 * @Since: 1.0
 */

public class day20250409Solution {

    // 53 最大子数组和
    // 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
    // 输出：6
    public int maxSubArray(int[] nums) {
        // dp[i] 以nums[i]结尾的做大的子数组和
        int[] dp = new int[nums.length];
        // base case
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 54 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> ans = new LinkedList<>();
        int hi = 0, low = m - 1, left = 0, right = n - 1;
        while (ans.size() < m * n) {
            // 横向 从左往右
            if (hi <= low) {
                for (int j = left; j <= right; j++) {
                    ans.add(matrix[hi][j]);
                }
                hi++;
            }
            // 纵向 从上往下
            if (left <= right) {
                for (int i = hi; i <= low; i++) {
                    ans.add(matrix[i][right]);
                }
                right--;
            }
            // 横向 从右往左
            if (hi <= low) {
                for (int j = right; j >= left; j--) {
                    ans.add(matrix[low][j]);
                }
                low--;
            }
            // 纵向 从下往上
            if (left <= right) {
                for (int i = low; i >= hi; i--) {
                    ans.add(matrix[i][left]);
                }
                left++;
            }
        }
        return ans;
    }

    // 55 跳跃游戏
    public boolean canJump(int[] nums) {
        int end = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(i + nums[i], farthest);
            // 可能遇到0 卡住跳不动了
            if (farthest <= i) {
                return false;
            }
        }
        return farthest >= nums.length - 1;
    }
}
