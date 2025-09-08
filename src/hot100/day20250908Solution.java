package hot100;

import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.08
 * @Since: 1.0
 */

public class day20250908Solution {
    // [739] 每日温度
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            // 维护一个单调递减栈
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    // [55] 跳跃游戏
    public boolean canJump(int[] nums) {
        // 当前可以跳跃到的最远位置
        int forest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            forest = Math.max(forest, i + nums[i]);
            // 可能碰到0，不跳了
            if (forest <= i) {
                return false;
            }
        }
        return forest >= nums.length - 1;
    }

    // [45] 跳跃游戏 II
    public int jump(int[] nums) {
        int res = 0;
        // 当前最远可以跳到哪个索引
        int forest = 0;
        // 当前的终点在哪里
        int end = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            forest = Math.max(forest, i + nums[i]);
            // 如果达到终点了，必须要跳了
            if (end == i) {
                res++;
                end = forest;
            }
        }
        return res;
    }
}
