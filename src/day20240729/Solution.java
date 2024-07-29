package day20240729;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-29 16:14
 * @version: 1.0
 */
public class Solution {
    public int calPoints(String[] operations) {
        int res = 0;
        LinkedList<Integer> track = new LinkedList<>();
        for (int i = 0; i < operations.length; i++) {
            if (operations[i].charAt(0) >= '0' && operations[i].charAt(0) <= '9' || operations[i].charAt(0) == '-') {
                track.addLast(Integer.parseInt(operations[i]));
            } else if ("+".equals(operations[i])) {
                int last = track.getLast();
                int last2 = track.get(track.size() - 2);
                track.addLast(last2 + last);
            } else if ("D".equals(operations[i])) {
                track.addLast(track.getLast() * 2);
            } else if ("C".equals(operations[i])) {
                track.removeLast();
            }
        }
        for (int i = 0; i < track.size(); i++) {
            res += track.get(i);
        }
        return res;
    }

    int[] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        memo = new int[n];
        Arrays.fill(memo, -1);
        return dp(s, 0, wordDict);
    }

    // dp(s,0,wordDict)：s[0...]能否由wordDict拼凑而出
    private boolean dp(String s, int start, List<String> wordDict) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != -1) {
            return memo[start] == 1 ? true : false;
        }
        for (String word : wordDict) {
            int len = word.length();
            if (start + len > s.length()) {
                continue;
            }
            String subStr = s.substring(start, start + len);
            if (!subStr.equals(word)) {
                continue;
            }
            if (dp(s, start + len, wordDict)) {
                memo[start] = 1;
                return true;
            }
        }
        memo[start] = 0;
        return false;
    }

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length;
        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    public int uniquePaths(int m, int n) {
        //状态就是位置，选择就是从上面来还是从左边来
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
                dp[i + 1] = 0;
            } else {
                //右括号
                if (!stack.isEmpty()) {
                    int leftIndex = stack.pop();
                    int len = i - leftIndex + 1 + dp[leftIndex];
                    dp[i + 1] = len;
                } else {
                    dp[i + 1] = 0;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
