package date202602;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.02
 * @Since: 1.0
 */

public class day20260202Solution {
    // [46] 全排列
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        backTrack(nums, track, visited);
        return ans;
    }

    private void backTrack(int[] nums, LinkedList<Integer> track, boolean[] visited) {
        // 结束条件
        if (track.size() == nums.length) {
            ans.add(new ArrayList<>(track));
            return;
        }
        // 做选择
        for (int i = 0; i < nums.length; i++) {
            // 题目中没有重复数字,用来判断当前这个选择是否在路径中了，如果已经在路径中则不选择
            if (visited[i]) {
                continue;
            }
            track.addLast(nums[i]);
            visited[i] = true;
            backTrack(nums, track, visited);
            // 撤销选择
            track.removeLast();
            visited[i] = false;
        }
    }
}
