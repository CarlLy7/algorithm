package PermutationCombinationSubset;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 排列（元素无重可复选）
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class ChongFuPaiLie {
    List<List<Integer>> res=new LinkedList<>();
    LinkedList<Integer> track=new LinkedList<>();
    public List<List<Integer>> permuteRepeat(int[] nums){
        backTrack(nums);
        return res;
    }

    private void backTrack(int[] nums) {
        if(track.size()== nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        if(track.size()> nums.length){
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            track.addLast(nums[i]);
            backTrack(nums);
            track.removeLast();
        }
    }
}
