package day20240712;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-12 12:17
 * @version: 1.0
 */
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        MedianFinder medianFinder = new MedianFinder();
        for (int i = 0; i < nums1.length; i++) {
            medianFinder.addNum(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            medianFinder.addNum(nums2[i]);
        }
        return medianFinder.find();
    }

    class MedianFinder {
        PriorityQueue<Integer> small;
        PriorityQueue<Integer> large;

        public MedianFinder() {
            this.small = new PriorityQueue<>((a, b) -> {
                return b - a;
            });
            this.large = new PriorityQueue<>();
        }

        public void addNum(int val) {
            if (small.size() >= large.size()) {
                small.offer(val);
                large.offer(small.poll());
            } else {
                large.offer(val);
                small.offer(large.poll());
            }
        }

        public double find() {
            if (small.size() > large.size()) {
                return small.peek();
            } else if (small.size() < large.size()) {
                return large.peek();
            } else {
                return (large.peek() + small.peek()) / 2.0;
            }
        }
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0, farest = 0;
        int res = 0;
        // i和end标记可以选择跳跃的步数，farest标记可以跳跃的最远距离
        for (int i = 0; i < n - 1; i++) {
            farest = Math.max(farest, nums[i] + i);
            //已经到达最大可以选择的范围了
            if (i == end) {
                res++;
                end = farest;
            }
        }
        return res;
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farest = 0;
        for (int i = 0; i < n - 1; i++) {
            farest = Math.max(farest, nums[i] + i);
            if (farest <= i) {
                return false;
            }
        }
        return farest >= n - 1;
    }

    public int climbStairs(int n) {
        //状态：当前的楼层  选择：上一层还是二层
        //dp[i]表示爬到i层的时候有多少种方法
        int[] dp = new int[n + 1];
        //base case
        if (n <= 2) {
            return n;
        }
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) {
            return res;
        }
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> preRow = res.get(res.size() - 1);
            res.add(generateNextRow(preRow));
        }
        return res;
    }

    private List<Integer> generateNextRow(List<Integer> preRow) {
        List<Integer> curRow = new ArrayList<>();
        curRow.add(1);
        for (int i = 0; i < preRow.size() - 1; i++) {
            curRow.add(preRow.get(i) + preRow.get(i + 1));
        }
        curRow.add(1);
        return curRow;
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    int[] memo;

    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, -666);
        //从coins中凑amount需要多少硬币
        return dp(coins, amount);
    }

    private int dp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != -666) {
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subRes = dp(coins, amount - coins[i]);
            if (subRes == -1) {
                continue;
            }
            res = Math.min(res, subRes + 1);
        }
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }
}
