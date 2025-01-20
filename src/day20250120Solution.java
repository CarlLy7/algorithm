import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: carl
 * @date: 2025/1/20
 */

public class day20250120Solution {

    //
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> permuteRepeat(int[] nums) {
        backTrack(nums);
        return res;
    }

    private void backTrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            track.addLast(nums[i]);
            backTrack(nums);
            track.removeLast();
        }
    }

    // 39
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // int trackSum = 0;
    //
    // public List<List<Integer>> combinationSum(int[] candidates, int target) {
    // backTrack(candidates, 0, target);
    // return res;
    // }
    //
    // private void backTrack(int[] candidates, int start, int target) {
    // if (trackSum == target) {
    // res.add(new ArrayList<>(track));
    // return;
    // }
    // if (trackSum > target) {
    // return;
    // }
    // for (int i = start; i < candidates.length; i++) {
    // track.addLast(candidates[i]);
    // trackSum += candidates[i];
    // backTrack(candidates, i, target);
    // trackSum -= candidates[i];
    // track.removeLast();
    // }
    // }

}
