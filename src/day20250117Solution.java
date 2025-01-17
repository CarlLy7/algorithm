import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: carl
 * @date: 2025/1/17
 */

public class day20250117Solution {

    // 47
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(nums);
        return res;
    }

    private void backTrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (used[i]) {
                continue;
            }
            track.addLast(nums[i]);
            used[i] = true;
            backTrack(nums);
            track.removeLast();
            used[i] = false;
        }
    }

    // 40
    // List<List<Integer>> res=new ArrayList<>();
    // LinkedList<Integer> track=new LinkedList<>();
    // int trackSum=0;
    // public List<List<Integer>> combinationSum2(int[] candidates,int target){
    // Arrays.sort(candidates);
    // backTrack(candidates,0,target);
    // return res;
    // }
    //
    // private void backTrack(int[] candidates, int start, int target) {
    // if (trackSum==target){
    // res.add(new ArrayList<>(track));
    // return;
    // }
    // if (trackSum>target){
    // return;
    // }
    // for (int i = start; i < candidates.length; i++) {
    // if (i>start && candidates[i]==candidates[i-1]){
    // continue;
    // }
    // track.addLast(candidates[i]);
    // trackSum+=candidates[i];
    // backTrack(candidates,i+1,target);
    // track.removeLast();
    // trackSum-=candidates[i];
    // }
    // }

    // 90
    // List<List<Integer>> res=new ArrayList<>();
    // LinkedList<Integer> track=new LinkedList<>();
    // boolean[] used;
    // public List<List<Integer>> subsetsWithDup(int[] nums){
    // used=new boolean[nums.length];
    // Arrays.sort(nums);
    // backTrack(nums,0);
    // return res;
    // }
    //
    // private void backTrack(int[] nums, int start) {
    // res.add(new ArrayList<>(track));
    // for (int i = start; i < nums.length; i++) {
    // if (i>start && nums[i]==nums[i-1]){
    // continue;
    // }
    // track.addLast(nums[i]);
    // backTrack(nums,i+1);
    // track.removeLast();
    // }
    // }
}
