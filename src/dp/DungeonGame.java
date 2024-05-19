package dp;

import java.util.Arrays;

/**
 * @description: 地下城游戏 https://leetcode.cn/problems/dungeon-game/
 * @author: lyq
 * @createDate: 15/4/2023
 * @version: 1.0
 */
public class DungeonGame {
    //因为这是一个二维矩阵并且是一个求最值的问题，所以使用动态规划来解决
    //使用一个二维数组来做备忘录
    int[][] memo;

    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        memo = new int[row][col];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(dungeon, 0, 0);
    }

    //声明一下这个dp函数的定义：从终点到（i，j）位置所需要的最少的生命值为dp(i,j)
    private int dp(int[][] dungeon, int i, int j) {
        int res=0;
        int row=dungeon.length;
        int col=dungeon[0].length;
        //base case:终点位置，如果是正数的话就是1，如果是负数的话，是绝对值+1
        if(i==row-1&&j==col-1){
            return dungeon[i][j]>0?1:-dungeon[i][j]+1;
        }
        if(i==row||j==col){
            //越界了
            return Integer.MAX_VALUE;
        }
        if(memo[i][j]!=-1){
            return memo[i][j];
        }
        res=Math.min(dp(dungeon,i+1,j),dp(dungeon,i,j+1))-dungeon[i][j];
        memo[i][j]=res<=0?1:res;
        return memo[i][j];
    }
}
