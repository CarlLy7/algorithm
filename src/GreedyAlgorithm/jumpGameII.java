package GreedyAlgorithm;

/**
 * @description: 跳跃游戏II https://leetcode.cn/problems/jump-game-ii/
 * @author: lyq
 * @createDate: 24/4/2023
 * @version: 1.0
 */
public class jumpGameII {
    public int jump(int[] nums) {
        int n= nums.length;
        //end代表当前这个位置可以跳跃到的最远的位置的下标
        //forest是可以跳跃到的最远的距离
        int end=0,forest=0;
        //res记录跳跃的次数
        int res=0;
        //依然是跳跃到最后一个位置就结束了
        for (int i = 0; i < n-1; i++) {
            //forest是当前这个下标作为开始起点，可以跳跃到的最远的位置
            forest=Math.max(forest,nums[i]+i);
            //当i往后移动到了当前这个位置可以跳跃到的最远的位置的时候肯定要进行一次选择进行跳跃，所以跳跃次数加一，
            // 然后下次进行跳跃选择的边界是可以当前这次跳跃可以到达的最远位置
            if (i==end){//这里i==end的时候说明我们的i后移已经到了可以跳跃的最远位置，我们必须做出一次跳跃选择了
                res++;
                end=forest;
            }
        }
        return res;
    }
}
