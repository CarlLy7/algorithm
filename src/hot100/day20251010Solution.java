package hot100;

import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.10
 * @Since: 1.0
 */

public class day20251010Solution {
    // [740] 删除并获得点数
    // int[] memo;
    // public int deleteAndEarn(int[] nums) {
    // int[] points = new int[10001];
    // for (int num : nums) {
    // points[num] += num;
    // }
    // int n = nums.length;
    // memo = new int[points.length];
    // Arrays.fill(memo, -1);
    // return dp(points, 0);
    // }
    //
    // private int dp(int[] nums, int start) {
    // if (start >= nums.length) {
    // return 0;
    // }
    // if (memo[start] != -1) {
    // return memo[start];
    // }
    // int res = Math.max(dp(nums, start + 1), dp(nums, start + 2) + nums[start]);
    // memo[start] = res;
    // return res;
    // }

    // [2140] 解决智力问题
    // long[] memo;
    //
    // public long mostPoints(int[][] questions) {
    // int n = questions.length;
    // memo = new long[n];
    // Arrays.fill(memo, -1);
    // return dp(questions, 0, n - 1);
    // }
    //
    // private long dp(int[][] questions, int start, int end) {
    // if (start > end) {
    // return 0;
    // }
    // if (memo[start] != -1) {
    // return memo[start];
    // }
    // int[] cur = questions[start];
    // long res = Math.max(dp(questions, start + 1, end), cur[0] + dp(questions, start + cur[1]+1, end));
    // memo[start] = res;
    // return res;
    // }

    // [2320] 统计放置房子的方式数
    int[] memo;
    int mod = 1000000007;

    public int countHousePlacements(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        int res = dp(0, n);
        return (int)((long) res * res % mod);
    }

    /**
     * 从第start块地板到最后可以防止的房子方案
     * 
     * @param start
     * @return
     */
    private int dp(int start, int n) {
        if (start >= n) {
            return 1;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        int res = (dp(start + 1, n) + dp(start + 2, n)) % mod;
        memo[start] = res;
        return res;
    }
}
