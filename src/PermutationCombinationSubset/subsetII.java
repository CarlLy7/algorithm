package PermutationCombinationSubset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 子集II https://leetcode.cn/problems/subsets-ii/
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class subsetII {
    List<List<Integer>> res=new LinkedList<>();
    LinkedList<Integer> track=new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backTrack(nums,0);
        return res;
    }

    private void backTrack(int[] nums, int start) {
        res.add(new LinkedList<>(track));
        for (int i = start; i <nums.length ; i++) {
           if(i>start && nums[i]==nums[i-1]){
               continue;
           }
            track.addLast(nums[i]);
            backTrack(nums,i+1);
            track.removeLast();
        }
    }
}
