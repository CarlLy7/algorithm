package day20240603;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-03 10:36
 * @version: 1.0
 */
public class Solution {
    //    public int[] distributeCandies(int candies, int num_people) {
//        int[] res = new int[num_people];
//        int lunCount = 0;
//        while (candies > 0) {
//            for (int i = 1; i <= num_people; i++) {
//                if (candies <= 0) {
//                    return res;
//                }
//                int curNum = i + (lunCount * num_people);
//                if (candies <= curNum) {
//                    res[i-1] += candies;
//                    return res;
//                }
//                candies -= curNum;
//                res[i-1] += curNum;
//            }
//            lunCount++;
//        }
//        return res;
//    }
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        for (int i = 1; ; i++) {
            if (candies <= 0) {
                return res;
            }
            if (candies <= i) {
                res[(i - 1) % num_people] += candies;
                return res;
            }
            candies -= i;
            res[(i - 1) % num_people] += i;
        }
    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> subsets(int[] nums) {
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        // base case
//        res.add(new ArrayList<>(track));
//        for (int i = start; i < nums.length; i++) {
//            track.addLast(nums[i]);
//            backTrack(nums, i + 1);
//            track.removeLast();
//        }
//    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        backTrack(n, 1, k);
//        return res;
//    }
//
//    private void backTrack(int n, int start, int k) {
//        if (track.size() == k) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        for (int i = start; i <= n; i++) {
//            track.addLast(i);
//            backTrack(n, i + 1, k);
//            track.removeLast();
//        }
//    }
//
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    boolean[] used;
//
//    public List<List<Integer>> permute(int[] nums) {
//        used = new boolean[nums.length];
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        int n = nums.length;
//        if (track.size() == n) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        for (int i = 0; i < n; i++) {
//            if (used[i]) {
//                continue;
//            }
//            used[i] = true;
//            track.addLast(nums[i]);
//            backTrack(nums);
//            used[i] = false;
//            track.removeLast();
//        }
//    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        Arrays.sort(nums);
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        res.add(new ArrayList<>(track));
//        for (int i = start; i < nums.length; i++) {
//            if (i > start && nums[i] == nums[i - 1]) {
//                continue;
//            }
//            track.addLast(nums[i]);
//            backTrack(nums, i + 1);
//            track.removeLast();
//        }
//    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    int trackSum = 0;
//
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        backTrack(candidates, 0, target);
//        return res;
//    }
//
//    private void backTrack(int[] candidates, int start, int target) {
//        if (trackSum == target) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        if (trackSum>target){
//            return;
//        }
//        for (int i = start; i < candidates.length; i++) {
//            if (i > start && candidates[i] == candidates[i - 1]) {
//                continue;
//            }
//            trackSum += candidates[i];
//            track.addLast(candidates[i]);
//            backTrack(candidates, i + 1, target);
//            trackSum -= candidates[i];
//            track.removeLast();
//        }
//    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    boolean[] used;
//
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        used = new boolean[nums.length];
//        Arrays.sort(nums);
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        if (track.size() == nums.length) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]){
//                continue;
//            }
//            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
//                continue;
//            }
//            used[i] = true;
//            track.addLast(nums[i]);
//            backTrack(nums);
//            used[i] = false;
//            track.removeLast();
//        }
//    }

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTrack(candidates, 0, target);
        return res;
    }

    private void backTrack(int[] candidates, int start, int target) {
        if (target == trackSum) {
            res.add(new ArrayList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            trackSum += candidates[i];
            track.addLast(candidates[i]);
            backTrack(candidates, i, target);
            trackSum -= candidates[i];
            track.removeLast();
        }
    }
}
