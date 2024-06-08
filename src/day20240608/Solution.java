package day20240608;

import java.util.Arrays;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-08 10:53
 * @version: 1.0
 */
public class Solution {
    int[][] memo;
    int[] nums;
    public int maxOperations(int[] nums) {
        if (nums.length<2){
            return 0;
        }
        int n=nums.length;
        this.nums=nums;
        this.memo=new int[n][n];
        int res=0;
        for (int[] ints : memo) {
            Arrays.fill(ints,-1);
        }
        res=Math.max(res,dp(0,n-1,nums[0]+nums[1]));
        for (int[] ints : memo) {
            Arrays.fill(ints,-1);
        }
        res=Math.max(res,dp(0,n-1,nums[n-2]+nums[n-1]));
        for (int[] ints : memo) {
            Arrays.fill(ints,-1);
        }
        res=Math.max(res,dp(0,n-1,nums[0]+nums[n-1]));
        return res;
    }

    //dp函数： dp(i,j,target) 数组范围为[i,j]满足target的次数
    private int dp(int i, int j, int target) {
        int res=0;
        if (i>=j){
            return 0;
        }
        if (memo[i][j]!=-1){
            return memo[i][j];
        }
        if (nums[i]+nums[i+1]==target){
            res=Math.max(res,1+dp(i+2,j,target));
        }
        if(nums[i]+nums[j]==target){
            res=Math.max(res,1+dp(i+1,j-1,target));
        }
        if (nums[j-1]+nums[j]==target){
            res=Math.max(res,1+dp(i,j-2,target));
        }
        memo[i][j]=res;
        return res;
    }
}
