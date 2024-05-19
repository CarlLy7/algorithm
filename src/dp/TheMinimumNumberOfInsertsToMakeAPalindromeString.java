package dp;

/**
 * @description: 让字符串成为回文串的最少插入次数
 * @author: lyq
 * @createDate: 10/4/2023
 * @version: 1.0
 */
public class TheMinimumNumberOfInsertsToMakeAPalindromeString {
    public int minInsertions(String s) {
        int n = s.length();
        //定义一个二维的dp数组，数组的定义为 s中【i..j】变成回文串要最少插入的次数
        int[][] dp = new int[n][n];
        //base case: 当i=j也就是只有一个字符的时候，不用插入因为已经是回文串了
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        //当i>j的时候是非法的场景，所以默认为0就可以了
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    //如果此时的两个字符相等，就不需要进行插入了，就是[i+1,j-1]的插入的个数
                    dp[i][j]=dp[i+1][j-1];
                }else{
                    //如果不相等，选择dp[i][j-1]和dp[i+1][j]中插入少的那个，但是最后还要在+1，因为我们还要和剩下的那个字符进行对称插入匹配成回文串
                    dp[i][j]=Math.min(dp[i][j-1],dp[i+1][j])+1;
                }
            }
        }
        return dp[0][n-1];
    }

}
