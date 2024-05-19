package GreedyAlgorithm;

/**
 * @description: 跳跃游戏 https://leetcode.cn/problems/jump-game/
 * @author: lyq
 * @createDate: 24/4/2023
 * @version: 1.0
 */
public class jumpGame {
    public boolean canJump(int[] nums) {
        int n= nums.length;
        int forest=0;//可以到达的最远距离
        //在这个循环中为什么是到n-2呢，因为最后一个位置如果可以到达的话直接就true了，所以循环中没必要去循环最后一个位置
        for (int i = 0; i < n-1; i++) {
            //选择可以到达的最远的位置
            forest=Math.max(forest,i+nums[i]);
            if(i>=forest){
                //如果此时的指针比最远位置大了，比如遇到了0卡住不能跳了，但是指针还会往后走,直接返回false，跳不到最后
                return false;
            }
        }
        return forest>=n-1;
    }
}
