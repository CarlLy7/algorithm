package dp;

import java.util.Arrays;

/**
 * @description: 不同的子序列 https://leetcode.cn/problems/distinct-subsequences/
 * @author: lyq
 * @createDate: 2/4/2023
 * @version: 1.0
 */
public class DifferentSubsequences {
    //使用备忘录进行消除重叠子问题
    int[][] memo;
    public int numDistinct(String s, String t) {
        memo=new int[s.length()][t.length()];
        for (int[] ints : memo) {
            Arrays.fill(ints,-1);
        }
        //从前往后进行一个动态规划
        int res=dp(s,0,t,0);
        return res;
    }

    private int dp(String s, int i, String t, int j) {
        int res=0;
        //base case
        //1、如果t已经遍历到最后了，就该返回了
        if(j==t.length()){
            return 1;
        }
        //2、如果s中剩下的长度小于t中剩下的长度了，说明没有匹配的了，返回0
        if(s.length()-i<t.length()-j){
            return 0;
        }
        if(memo[i][j]!=-1){
            return memo[i][j];
        }
        //如果当前位置的两个字符匹配了，有两种可能的结果，让这个字符进行匹配，然后递归去找后面的字符，第二种可能就是不让这个字符匹配，给后面的字符留下匹配的机会
        if(s.charAt(i)==t.charAt(j)){
            res+=dp(s,i+1,t,j+1)+dp(s,i+1,t,j);
        }else{
            //如果没有匹配的话，两个字符串都后移一位进行递归处理
            res+=dp(s,i+1,t,j);
        }
        memo[i][j]=res;
        return res;
    }
}
