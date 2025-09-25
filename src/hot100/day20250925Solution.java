package hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.25
 * @Since: 1.0
 */

public class day20250925Solution {
    // [78] 子集
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    //
    // public List<List<Integer>> subsets(int[] nums) {
    // backTrack(nums, 0);
    // return res;
    // }
    //
    // private void backTrack(int[] nums, int start) {
    // res.add(new ArrayList<>(track));
    // for (int i = start; i < nums.length; i++) {
    // track.addLast(nums[i]);
    // backTrack(nums, i + 1);
    // track.removeLast();
    // }
    // }

    // [90] 子集 II
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // boolean[] used;
    //
    // public List<List<Integer>> subsetsWithDup(int[] nums) {
    // Arrays.sort(nums);
    // used = new boolean[nums.length];
    // backTrack(nums, 0);
    // return res;
    // }
    //
    // private void backTrack(int[] nums, int start) {
    // res.add(new ArrayList<>(track));
    // for (int i = start; i < nums.length; i++) {
    // // 如果是相同的元素，并且前一个元素还没有使用，那么当前元素不可以使用
    // if (i > start && nums[i] == nums[i - 1] && !used[i - 1]) {
    // continue;
    // }
    // used[i] = true;
    // track.addLast(nums[i]);
    // backTrack(nums, i + 1);
    // track.removeLast();
    // used[i] = false;
    // }
    // }

    // [77] 组合
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    //
    // public List<List<Integer>> combine(int n, int k) {
    // backTrack(n, k, 1);
    // return res;
    // }
    //
    // private void backTrack(int n, int k, int start) {
    // if (track.size() == k) {
    // res.add(new ArrayList<>(track));
    // return;
    // }
    // if (track.size() > k) {
    // return;
    // }
    // for (int i = start; i <= n; i++) {
    // track.addLast(i);
    // backTrack(n, k, i + 1);
    // track.removeLast();
    // }
    // }

    // [39] 组合总和
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // int trackSum = 0;
    //
    // public List<List<Integer>> combinationSum(int[] candidates, int target) {
    // backTrack(candidates, target, 0);
    // return res;
    // }
    //
    // private void backTrack(int[] candidates, int target, int start) {
    // if (trackSum == target) {
    // res.add(new ArrayList<>(track));
    // return;
    // }
    // if (trackSum > target) {
    // return;
    // }
    // for (int i = start; i < candidates.length; i++) {
    // trackSum += candidates[i];
    // track.addLast(candidates[i]);
    // backTrack(candidates, target, i);
    // trackSum -= candidates[i];
    // track.removeLast();
    // }
    // }

    // [40] 组合总和 II
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // int trackSum = 0;
    //
    // public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    // Arrays.sort(candidates);
    // backTrack(candidates, target, 0);
    // return res;
    // }
    //
    // private void backTrack(int[] candidates, int target, int start) {
    // if (trackSum == target) {
    // res.add(new ArrayList<>(track));
    // return;
    // }
    // if (trackSum > target) {
    // return;
    // }
    // for (int i = start; i < candidates.length; i++) {
    // if (i > start && candidates[i] == candidates[i - 1]) {
    // continue;
    // }
    // track.addLast(candidates[i]);
    // trackSum += candidates[i];
    // backTrack(candidates, target, i + 1);
    // trackSum -= candidates[i];
    // track.removeLast();
    // }
    // }

    // [216] 组合总和 III
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrack(k, n, 1);
        return res;
    }

    private void backTrack(int k, int n, int start) {
        if (trackSum == n && track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }
        if (trackSum > n || track.size() > k) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            trackSum += i;
            track.addLast(i);
            backTrack(k, n, i + 1);
            trackSum -= i;
            track.removeLast();
        }
    }

    // [46] 全排列
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // boolean[] used;
    //
    // public List<List<Integer>> permute(int[] nums) {
    // int n = nums.length;
    // used = new boolean[n];
    // backTrack(nums);
    // return res;
    // }
    //
    // private void backTrack(int[] nums) {
    // if (track.size() == nums.length) {
    // res.add(new ArrayList<>(track));
    // return;
    // }
    // if (track.size() > nums.length) {
    // return;
    // }
    // for (int i = 0; i < nums.length; i++) {
    // // 如果这个元素已经使用过了
    // if (used[i]) {
    // continue;
    // }
    // used[i] = true;
    // track.addLast(nums[i]);
    // backTrack(nums);
    // used[i] = false;
    // track.removeLast();
    // }
    // }

    // [47] 全排列 II
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // boolean[] used;
    //
    // public List<List<Integer>> permuteUnique(int[] nums) {
    // int n = nums.length;
    // used = new boolean[n];
    // Arrays.sort(nums);
    // backTrack(nums);
    // return res;
    // }
    //
    // private void backTrack(int[] nums) {
    // int n = nums.length;
    // if (track.size() == n) {
    // res.add(new ArrayList<>(track));
    // return;
    // }
    // if (track.size() > n) {
    // return;
    // }
    // for (int i = 0; i < n; i++) {
    // if (used[i]) {
    // continue;
    // }
    // // 前一个相同元素还没使用，那么这个就不能使用
    // if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
    // continue;
    // }
    // track.addLast(nums[i]);
    // used[i] = true;
    // backTrack(nums);
    // track.removeLast();
    // used[i] = false;
    // }
    // }
}
