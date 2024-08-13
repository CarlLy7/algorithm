package day20240813;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-13 10:20
 * @version: 1.0
 */
public class Solution {
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> path = new LinkedList<>();
//
//    public List<List<Integer>> subsets(int[] nums) {
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        res.add(new ArrayList<>(path));
//        for (int i = start; i < nums.length; i++) {
//            path.addLast(nums[i]);
//            backTrack(nums, i + 1);
//            path.removeLast();
//        }
//    }

    //    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> path = new LinkedList<>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        backTrack(n, k, 1);
//        return res;
//    }
//
//    private void backTrack(int n, int k, int start) {
//        // base case:凑够了，添加到结果集，返回
//        if (k == 0) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//        for (int i = start; i <= n; i++) {
//            path.addLast(i);
//            backTrack(n, k - 1, i + 1);
//            path.removeLast();
//        }
//    }
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> path = new LinkedList<>();
//    boolean[] used;
//
//    public List<List<Integer>> permute(int[] nums) {
//        int n = nums.length;
//        used = new boolean[n];
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        if (path.size() == nums.length) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) {
//                continue;
//            }
//            used[i] = true;
//            path.addLast(nums[i]);
//            backTrack(nums);
//            used[i] = false;
//            path.removeLast();
//        }
//    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> path = new LinkedList<>();
//    boolean[] used;
//
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        int n = nums.length;
//        used = new boolean[n];
//        Arrays.sort(nums);
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        res.add(new ArrayList<>(path));
//        for (int i = start; i < nums.length; i++) {
//            if (i > start && nums[i] == nums[i - 1] && !used[i - 1]) {
//                continue;
//            }
//            if (used[i]) {
//                continue;
//            }
//            path.addLast(nums[i]);
//            used[i] = true;
//            backTrack(nums, i + 1);
//            used[i] = false;
//            path.removeLast();
//        }
//    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> path = new LinkedList<>();
//    int sum = 0;
//
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        backTrack(candidates, 0, target);
//        return res;
//    }
//
//    private void backTrack(int[] candidates, int start, int target) {
//        if (sum == target) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//        if (sum > target) {
//            return;
//        }
//        for (int i = start; i < candidates.length; i++) {
//            if (i > start && candidates[i] == candidates[i - 1]) {
//                continue;
//            }
//            sum += candidates[i];
//            path.addLast(candidates[i]);
//            backTrack(candidates, i + 1, target);
//            sum -= candidates[i];
//            path.removeLast();
//        }
//    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> path = new LinkedList<>();
//    boolean[] used;
//
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        Arrays.sort(nums);
//        used = new boolean[nums.length];
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        if (path.size() == nums.length) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) {
//                continue;
//            }
//            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
//                continue;
//            }
//            used[i] = true;
//            path.addLast(nums[i]);
//            backTrack(nums);
//            used[i] = false;
//            path.removeLast();
//        }
//    }

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTrack(candidates, 0, target);
        return res;
    }

    private void backTrack(int[] candidates, int start, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            sum += candidates[i];
            path.addLast(candidates[i]);
            backTrack(candidates, i, target);
            sum -= candidates[i];
            path.removeLast();
        }
    }
}
