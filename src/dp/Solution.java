package dp;

import java.util.Arrays;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-02-23 21:25
 * @version: 1.0
 */
public class Solution {
    // 两个字符串的删除操作，其实就是找最长公共子序列
//    public int minDistance(String word1, String word2) {
//        int m=word1.length(),n=word2.length();
//        int longestCommonSubsequence = getLongestCommonSubsequence(word1, word2);
//        return m-longestCommonSubsequence+n-longestCommonSubsequence;
//    }
//
//    int[][] memo;
//    public int getLongestCommonSubsequence(String word1, String word2) {
//        int m=word1.length(),n=word2.length();
//        memo=new int[m][n];
//        for (int[] ints : memo) {
//            Arrays.fill(ints,-1);
//        }
//        return dp(word1,0,word2,0);
//    }
//
//    private int dp(String word1, int i, String word2, int j) {
//        if (i==word1.length() || j==word2.length()){
//            return 0;
//        }
//        if (memo[i][j]!=-1){
//            return memo[i][j];
//        }
//        if (word1.charAt(i)==word2.charAt(j)){
//            memo[i][j]=1+dp(word1,i+1,word2,j+1);
//        }else{
//            memo[i][j]=Math.max(dp(word1,i+1,word2,j),dp(word1,i,word2,j+1));
//        }
//        return memo[i][j];
//    }
//    int[][] memo;
//
//    public int minimumDeleteSum(String s1, String s2) {
//        int m = s1.length(), n = s2.length();
//        memo = new int[m][n];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dp(s1, 0, s2, 0);
//    }
    // dp(s1,i,s2,j)的含义，让s1[i...]和s2[j...]相同所需要删除的最小的ASCII和
//    private int dp(String s1, int i, String s2, int j) {
//        int res=0;
//        if (i==s1.length()){
//            for (; j <s2.length() ; j++) {
//                res+=s2.charAt(j);
//            }
//            return res;
//        }
//        if (j==s2.length()){
//            for(;i<s1.length();i++){
//                res+=s1.charAt(i);
//            }
//            return res;
//        }
//        if (memo[i][j]!=-1){
//            return memo[i][j];
//        }
//        if (s1.charAt(i)==s2.charAt(j)){
//            memo[i][j]=dp(s1,i+1,s2,j+1);
//        }else{
//            memo[i][j]=Math.min(s1.charAt(i)+dp(s1,i+1,s2,j),s2.charAt(j)+dp(s1,i,s2,j+1));
//        }
//        return memo[i][j];
//    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j]=dp[i+1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i][j-1],dp[i+1][j])+1;
                }
            }
        }
        return dp[0][n-1];
    }


}
