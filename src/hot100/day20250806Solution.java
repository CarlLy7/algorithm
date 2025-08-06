package hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.06
 * @Since: 1.0
 */

public class day20250806Solution {
    // [39] 组合总和
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        backTrack(candidates, 0, target, 0);
        return res;
    }

    /**
     * 回溯算法解决组合问题
     * 
     * @param candidates
     * @param start
     * @param target
     * @param sum
     */
    private void backTrack(int[] candidates, int start, int target, int sum) {
        // base case
        if (sum == target) {
            res.add(new ArrayList<>(track));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 做选择
            sum += candidates[i];
            track.addLast(candidates[i]);
            backTrack(candidates, i, target, sum);
            sum -= candidates[i];
            track.removeLast();
        }
    }

    // [42] 接雨水
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        int left = height[0];
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        int right = height[n - 1];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], left);
            left = Math.max(height[i], left);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(right, height[i]);
            right = Math.max(right, height[i]);
        }
        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    // [45] 跳跃游戏 II
    public int jump(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 当前跳跃的边界
        int end = 0;
        // 当前可以跳跃的最远距离
        int farest = 0;
        for (int i = 0; i < n - 1; i++) {
            farest = Math.max(nums[i] + i, farest);
            // 如果当前达到了跳跃边界，就需要跳跃一次了
            if (end == i) {
                res++;
                // 更新跳跃边界
                end = farest;
            }
        }
        return res;
    }
}
