package date202602;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.04
 * @Since: 1.0
 */

public class day20260204Solution {
    // [90] 子集 II
    // List<List<Integer>> ans = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // boolean[] used;
    //
    // public List<List<Integer>> subsetsWithDup(int[] nums) {
    // used = new boolean[nums.length];
    // Arrays.sort(nums);
    // backTrack(nums, 0);
    // return ans;
    // }
    //
    // private void backTrack(int[] nums, int start) {
    // ans.add(new ArrayList<>(track));
    // for (int i = start; i < nums.length; i++) {
    // if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
    // continue;
    // }
    // track.addLast(nums[i]);
    // used[i] = true;
    // backTrack(nums, i + 1);
    // track.removeLast();
    // used[i] = false;
    // }
    // }

    // [40] 组合总和 II
    // List<List<Integer>> ans = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // int trackSum = 0;
    // boolean[] used;
    // int target;
    //
    // public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    // this.target = target;
    // used = new boolean[candidates.length];
    // Arrays.sort(candidates);
    // backTrack(candidates, 0);
    // return ans;
    // }
    //
    // private void backTrack(int[] candidates, int start) {
    // if (trackSum == target) {
    // ans.add(new ArrayList<>(track));
    // return;
    // }
    // if (trackSum>target){
    // return;
    // }
    // for (int i = start; i < candidates.length; i++) {
    // if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
    // continue;
    // }
    // trackSum += candidates[i];
    // track.addLast(candidates[i]);
    // used[i] = true;
    // backTrack(candidates, i + 1);
    // trackSum -= candidates[i];
    // track.removeLast();
    // used[i]=false;
    // }
    // }

    // [47] 全排列 II
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(nums);
        return ans;
    }

    private void backTrack(int[] nums) {
        if (track.size() == nums.length) {
            ans.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            used[i] = true;
            backTrack(nums);
            track.removeLast();
            used[i] = false;
        }
    }

}
