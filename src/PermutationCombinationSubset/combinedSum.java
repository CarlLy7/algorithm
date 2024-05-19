package PermutationCombinationSubset;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 组合总和 https://leetcode.cn/problems/combination-sum/
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class combinedSum {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    Integer sum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        backTrack(candidates, 0, target);
        return res;
    }

    private void backTrack(int[] candidates, int start, int target) {
        if (sum == target) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            track.addLast(candidates[i]);
            sum+=candidates[i];
            backTrack(candidates, i, target);
            track.removeLast();
            sum-=candidates[i];
        }
    }
}
