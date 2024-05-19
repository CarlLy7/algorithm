package dp;

import java.util.Arrays;

/**
 * @description:
 * @author: lyq
 * @createDate: 9/4/2023
 * @version: 1.0
 */
public class LongestCommonSubsequence {
    //下面的算法是自顶向下的递归动态规划
//    int[][] memo;
//    public int longestCommonSubsequence(String text1, String text2) {
//        memo=new int[text1.length()][text2.length()];
//        for (int[] ints : memo) {
//            Arrays.fill(ints,-1);
//        }
//        return dp(text1,0,text2,0);
//    }
//
//    private int dp(String text1, int i, String text2, int j) {
//        //base case
//        if(i==text1.length()|| j==text2.length()){
//            return 0;
//        }
//        if(memo[i][j]!=-1){
//            return memo[i][j];
//        }
//        if(text1.charAt(i)==text2.charAt(j)){
//            memo[i][j]=1+dp(text1,i+1,text2,j+1);
//        }else{
//            memo[i][j]=Math.max(dp(text1,i+1,text2,j),dp(text1,i,text2,j+1));
//        }
//        return memo[i][j];
//    }

    //============================================
    //下面是自底向上的dp数组的动态规划
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i <= text1.length(); i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= text2.length(); j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <=text2.length() ; j++) {
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

}
