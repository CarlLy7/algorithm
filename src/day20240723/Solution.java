package day20240723;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-23 09:41
 * @version: 1.0
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        //dp[i]:以nums[i]结尾的最长递增子序列的长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

//    int[][] memo;
//
//    public int minDistance(String word1, String word2) {
//        int m = word1.length(), n = word2.length();
//        memo = new int[m][n];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dp(word1, m - 1, word2, n - 1);
//    }
//
//    // word1[0...i]和word2[0...j]相同的时候操作的最小步骤
//    private int dp(String word1, int i, String word2, int j) {
//        if (i == -1) {
//            return j + 1;
//        }
//        if (j == -1) {
//            return i + 1;
//        }
//        if (memo[i][j] != -1) {
//            return memo[i][j];
//        }
//        if (word1.charAt(i) == word2.charAt(j)) {
//            memo[i][j] = dp(word1, i - 1, word2, j - 1);
//        } else {
//            memo[i][j] = min(dp(word1, i - 1, word2, j) + 1, dp(word1, i, word2, j - 1) + 1, dp(word1, i - 1, word2, j - 1) + 1);
//        }
//        return memo[i][j];
//    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    //冒泡排序
    public void sortColors(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i - 1; j++) {
                if (nums[i] < nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    //    int[][] memo;
//
//    public int longestCommonSubsequence(String text1, String text2) {
//        int m = text1.length(), n = text2.length();
//        memo = new int[m][n];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dp(text1, 0, text2, 0);
//    }
//
//    private int dp(String text1, int i, String text2, int j) {
//        if (i == text1.length() || j == text2.length()) {
//            return 0;
//        }
//        if (memo[i][j] != -1) {
//            return memo[i][j];
//        }
//        if (text1.charAt(i) == text2.charAt(j)) {
//            memo[i][j] = 1 + dp(text1, i + 1, text2, j + 1);
//        } else {
//            memo[i][j] = Math.max(dp(text1, i + 1, text2, j), dp(text1, i, text2, j + 1));
//        }
//        return memo[i][j];
//    }
//
//    int[][] memo;
//
//    public int minDistance(String word1, String word2) {
//        int m = word1.length(), n = word2.length();
//        memo = new int[m][n];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return word1.length() - dp(word1, 0, word2, 0) + word2.length() - dp(word1, 0, word2, 0);
//    }

    int[][] memo;

    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        memo = new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(s1, 0, s2, 0);
    }

    private int dp(String s1, int i, String s2, int j) {
        int res = 0;
        if (i == s1.length()) {
            for (; j < s2.length(); j++) {
                res += s2.charAt(j);
            }
            return res;
        }
        if (j == s2.length()) {
            for (; i < s1.length(); i++) {
                res += s1.charAt(i);
            }
            return res;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp(s1, i + 1, s2, j + 1);
        } else {
            memo[i][j] = Math.min(s1.charAt(i) + dp(s1, i + 1, s2, j), s2.charAt(j) + dp(s1, i, s2, j + 1));
        }
        return memo[i][j];
    }

    public int majorityElement(int[] nums) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                res = nums[i];
                count = 1;
            } else if (nums[i] == res) {
                count++;
            } else {
                count--;
            }
        }
        return res;
    }

    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        // 记录每个字母的最大下标
        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            // 边界为最大那个
            end = Math.max(end, last[s.charAt(i) - 'a']);
            // 达到最后边界的时候
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }
}