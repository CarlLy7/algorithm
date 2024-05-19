package dp;

/**
 * @description: 最长回文子序列
 * @author: lyq
 * @createDate: 10/4/2023
 * @version: 1.0
 */
public class LongestPalindromeSubsequence {
    public int longestPalindromeSubseq(String s) {
        int n=s.length();
        //定义一个二维的dp数组，但是这个二维的dp数组表示的是一个字符串中的数据
        //dp数组的定义为，字符串s中【i..j】的最长回文子序列的长度为dp[i][j]
        int[][] dp=new int[n][n];
        //base case:如果只有一个字符的话，最长回文子序列长度肯定为1了
        for (int i = 0; i <n; i++) {
            dp[i][i]=1;
        }
        //如果i>j了，肯定是不符合要求的，最长回文子序列长度为0
        for (int i = n-1; i >=0 ; i--) {
            for (int j = i+1; j <n ; j++) {
                //如果此时两个字符相等，肯定是可以加入回文子序列中的，所以长度+2
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else{
                    //如果此时两个位置的字符不相等，就选择一个大的回文子序列长度
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
