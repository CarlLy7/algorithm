package PermutationCombinationSubset;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 子集  https://leetcode.cn/problems/subsets/
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class subset {
    List<List<Integer>> res=new LinkedList<>();
    LinkedList<Integer> track=new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
     backtrack(nums,0);
     return res;
    }

    private void backtrack(int[] nums, int start) {
        res.add(new LinkedList<>(track));
        for (int i = start; i <nums.length ; i++) {
            track.addLast(nums[i]);
            backtrack(nums,i+1);
            track.removeLast();
        }
    }

}
