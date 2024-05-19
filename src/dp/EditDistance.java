package dp;

import java.util.Arrays;

/**
 * @description: 编辑距离 https://leetcode.cn/problems/edit-distance/
 * @author: lyq
 * @createDate: 1/4/2023
 * @version: 1.0
 */
public class EditDistance {
//    //自顶向下的递归解法
//    //因为有重叠子问题，所以使用备忘录进行优化
//    int[][] memo;
//
//    public int minDistance(String word1, String word2) {
//        int s1 = word1.length();
//        int s2 = word2.length();
//        memo = new int[s1][s2];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dp(word1, s1 - 1, word2, s2 - 1);
//    }
//
//    private int dp(String word1, int i, String word2, int j) {
//        //base case
//        //如果有一个字符串已经为空了，那个我们这个字符串应该移动另外一个字符串剩下的字符个数
//        if (j == -1) {
//            return i + 1;
//        }
//        if (i == -1) {
//            return j + 1;
//        }
//        if (memo[i][j] != -1) {
//            return memo[i][j];
//        }
//        //如果i和j这个位置的字符一样的话，直接跳过
//        if (word1.charAt(i) == word2.charAt(j)) {
//            memo[i][j] = dp(word1, i - 1, word2, j - 1);
//        } else {
//            //如果当前的两个字符不相等的话，无非就是三种操作，插入、删除、替换
//            memo[i][j] = min(dp(word1, i, word2, j - 1) + 1,
//                    dp(word1, i - 1, word2, j) + 1,
//                    dp(word1, i - 1, word2, j - 1) + 1);
//        }
//        return memo[i][j];
//    }
//
//    private int min(int a, int b, int c) {
//        return Math.min(a, Math.min(b, c));
//    }


    //===================================================
    //自底向上的迭代解法
    public int minDistance(String word1, String word2) {
        int s1=word1.length();
        int s2=word2.length();
        //这里详细说一下为什么dp数组这么建造，因为我们的数组下标不可能到-1,所以我们不能和自顶向下的递归解法一下[s1][s2]，这里我们就要整体右移一位，用0的位置表示base case
        int[][]dp=new int[s1+1][s2+1];
        //base case
        //这个问题的base case就是如果word1没有结束，但是word2结束了，那么word1的操作就是删除掉自己的剩余的字符
        //                    如果word2没有结束，但是word1结束了，那么word1的操作就是将word2剩余的字符插入
        for (int i = 1; i <=s1 ; i++) {
            dp[i][0]=i;
        }
        for (int j = 1; j <=s2 ; j++) {
            dp[0][j]=j;
        }
        //从上到下进行迭代
        for (int i = 1; i <=s1 ; i++) {
            for (int j = 1; j <= s2; j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    //如果两个字符相等的话，不操作，往前跳一个
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    //需要进行操作了，无非就是插入，删除，替换三种操作
                    dp[i][j]=min(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+1);
                }
            }
        }
        return dp[s1][s2];

    }

    private int min(int a, int b, int c) {
        return Math.min(a,Math.min(b,c));
    }
}
