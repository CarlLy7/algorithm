package PermutationCombinationSubset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 组合总和II https://leetcode.cn/problems/combination-sum-ii/
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class combinedSumII {
    List<List<Integer>> res=new LinkedList<>();
    LinkedList<Integer> track=new LinkedList<>();
    Integer sum=0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length==0){
            return res;
        }
        Arrays.sort(candidates);
        backTrack(candidates,0,target);
        return res;
    }

    private void backTrack(int[] candidates, int start, int target) {
        if(sum==target){
            res.add(new LinkedList<>(track));
            return;
        }
        if(sum>target){
            return;
        }
        for (int i = start; i <candidates.length ; i++) {
            if(i>start && candidates[i]==candidates[i-1]){
                continue;
            }
            track.addLast(candidates[i]);
            sum+=candidates[i];
            backTrack(candidates,i+1,target);
            track.removeLast();
            sum-=candidates[i];
        }
    }
}
