package dp;

import java.util.Arrays;

/**
 * @description:
 * @author: lyq
 * @createDate: 9/4/2023
 * @version: 1.0
 */
public class MinimumASCIIRemovalOfTwoStringsAnd {
    int[][] memo;
    public int minimumDeleteSum(String s1, String s2) {
        int m=s1.length();
        int n=s2.length();
        memo=new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints,-1);
        }
        return dp(s1,0,s2,0);
    }

    /**
     * dp函数的定义，返回s1从i开始，s2从j开始进行删除字符的 ASCII 值的最小和
     */
    private int dp(String s1, int i, String s2, int j) {
        int res=0;
        //base case
        if(i==s1.length()){
            //s1结束了 那么就需要将s2中的全部删除了
            for (;j<s2.length();j++) {
                res+=s2.charAt(j);
            }
            return res;
        }

        if(j==s2.length()){
            //s2结束了 那么就需要将s1中的全部删除了
            for (;i<s1.length();i++) {
                res+=s1.charAt(i);
            }
            return res;
        }
        if(memo[i][j]!=-1){
            return memo[i][j];
        }
        if(s1.charAt(i)==s2.charAt(j)){
            memo[i][j]=dp(s1,i+1,s2,j+1);
        }else{
           memo[i][j]=Math.min(
                   //删掉s1中的字符
                   s1.charAt(i)+dp(s1,i+1,s2,j),
                   //删掉s2中的字符
                   s2.charAt(j)+dp(s1,i,s2,j+1));
        }
        return memo[i][j];
    }
}
