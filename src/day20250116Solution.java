import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: carl
 * @date: 2025/1/16
 */

public class day20250116Solution {

    // 46
    public List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used=new boolean[nums.length];
        backTrack(nums);
        return res;
    }

    private void backTrack(int[] nums) {
        if (track.size()==nums.length){
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]){
                continue;
            }
            track.addLast(nums[i]);
            used[i]=true;
            backTrack(nums);
            track.removeLast();
            used[i]=false;
        }
    }

    // 77
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
    // for (int i = start; i <= n; i++) {
    // track.addLast(i);
    // backTrack(n, k, i + 1);
    // track.removeLast();
    // }
    // }

    // 78
    // List<List<Integer>> res=new ArrayList<>();
    // LinkedList<Integer> track=new LinkedList<>();
    // public List<List<Integer>> subsets(int[] nums){
    // backTrack(nums,0);
    // return res;
    // }
    //
    // private void backTrack(int[] nums, int start) {
    // res.add(new ArrayList<>(track));
    // for (int i = start; i < nums.length; i++) {
    // track.addLast(nums[i]);
    // backTrack(nums,i+1);
    // track.removeLast();
    // }
    // }

}
