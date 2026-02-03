package date202602;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.03
 * @Since: 1.0
 */

public class day20260203Solution {
    // [78] 子集
    // List<List<Integer>> ans = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    //
    // public List<List<Integer>> subsets(int[] nums) {
    // backTrack(nums, 0);
    // return ans;
    // }
    //
    // private void backTrack(int[] nums, int start) {
    // // 结束条件
    // ans.add(new ArrayList<>(track));
    // for (int i = start; i < nums.length; i++) {
    // track.addLast(nums[i]);
    // backTrack(nums, i + 1);
    // track.removeLast();
    // }
    // }

    // [77] 组合
    // List<List<Integer>> ans = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // int n;
    // int k;
    // public List<List<Integer>> combine(int n, int k){
    // this.n=n;
    // this.k=k;
    // backTrack(1);
    // return ans;
    // }
    //
    // private void backTrack(int start) {
    // //结束条件
    // if (track.size()==k){
    // ans.add(new ArrayList<>(track));
    // return;
    // }
    // for (int i = start; i <=n ; i++) {
    // track.addLast(i);
    // backTrack(i+1);
    // track.removeLast();
    // }
    // }

    // [39] 组合总和
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int target;
    int trackSum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.target = target;
        backTrack(candidates, 0);
        return ans;
    }

    private void backTrack(int[] candidates, int start) {
        // 结束条件
        if (trackSum == target) {
            ans.add(new ArrayList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            track.addLast(candidates[i]);
            trackSum += candidates[i];
            backTrack(candidates, i);
            track.removeLast();
            trackSum -= candidates[i];
        }
    }

}
